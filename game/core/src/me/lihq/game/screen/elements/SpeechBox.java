package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Settings;

import java.util.ArrayList;

import static java.lang.Thread.sleep;


/**
 * SpeechBox class
 * Used for rendering box containing text and buttons on screen
 * Note: add to InputMultiplexer if using with other UI elements.
 */
public class SpeechBox extends Table
{
    public boolean isPressed;
    /**
     * The constant color variables for all SpeechBox's
     */
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color BORDER_COLOUR = Color.RED;
    private static final Color TEXT_COLOUR = Color.LIGHT_GRAY;

    /**
     * Layout constants
     */
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int PADDING = 8;
    private static final int BORDER_WIDTH = 2;
    private static final int Y_OFFSET = StatusBar.HEIGHT;
    private static final int TABLE_WIDTH = WIDTH - (2 * BORDER_WIDTH);
    private static final int TEXT_ROW_HEIGHT = 30;
    private static final int BUTTON_ROW_HEIGHT = 40;
    private static final int TABLE_HEIGHT = (PADDING * 4) + TEXT_ROW_HEIGHT + BUTTON_ROW_HEIGHT;
    private static final int HEIGHT = TABLE_HEIGHT + (2 * BORDER_WIDTH);

    /**
     * The name of the person talking, if any
     */
    private String person;

    /**
     * This is the text component to display.
     */
    private String textContent;

    /**
     * List of buttons to be displayed on the SpeechBox
     */
    private ArrayList<SpeechBoxButton> buttons;
    //Styles
    private Skin buttonSkin;
    private Skin labelSkin;
    private Skin personLabelSkin;

    public boolean haveButtons(){
        return !buttons.isEmpty();
    }

    /**
     * The constructor for the SpeechBox
     */
    public SpeechBox(String content, ArrayList<SpeechBoxButton> buttonList)
    {

        textContent = content;
        buttons = buttonList;
        setupStage();
    }

    /**
     * The constructor for the SpeechBox with personName
     */
    public SpeechBox(String personName, String speechText, ArrayList<SpeechBoxButton> buttonList)
    {
        person = personName;
        textContent = speechText;
        buttons = buttonList;
        setupStage();
    }

    /**
     * The constructor for the SpeechBox without buttons
     */
    public SpeechBox(String content)
    {
        textContent = content;
        buttons = new ArrayList<>();
       // if(isPressed){
            setupStage();//}
    }

    /**
     * The constructor for the SpeechBox without buttons with personName
     */
    public SpeechBox(String personName, String speechText)
    {
        person = personName;
        textContent = speechText;
        buttons = new ArrayList<>();
        //if(isPressed){
            setupStage();//}
    }

    /**
     * Sets up the SpeechBox stage ready for rendering
     * The stage is a Scene2D class that deals with putting UI controls on the screen
     */
    private void setupStage()
    {
        initSkins();

        setTransform(true);

        setBounds(0, Y_OFFSET, WIDTH, HEIGHT);
        setBackground(UIHelpers.getBackgroundDrawable(BORDER_COLOUR, WIDTH, HEIGHT));

        //Init table containing contents of speech box
        Table table = new Table();

        table.setSize(TABLE_WIDTH, TABLE_HEIGHT);
        table.setBackground(UIHelpers.getBackgroundDrawable(BACKGROUND_COLOR, TABLE_WIDTH, TABLE_HEIGHT));
        fillTableContent(table);

        //Add table to container contents, and add padding
        pad(BORDER_WIDTH);
    }


    /**
     * Add relevant SpeechBox UI controls to table element
     *
     * @param table Table to add controls to
     */
    private void fillTableContent(Table table)
    {

        //Calculate constants for use later
        int buttonCount = 0;
        try {
            buttonCount = buttons.size();
        } catch (Exception noButtons) {
            buttonCount = 0;
        }


        //Calculate number of columns for label row to span
        int labelColSpan = buttonCount;
        if (buttonCount == 0) labelColSpan = 1;


        //Initialize text row
        table.row().height(TEXT_ROW_HEIGHT);
        if (person == null) {

            //Display only textContent
            Label contentLabel = new Label(textContent, labelSkin);
            table.add(contentLabel).colspan(labelColSpan).pad(-PADDING, PADDING, 0, PADDING).top().left();

        } else {

            //Display both person and textContent
            HorizontalGroup textGroup = new HorizontalGroup();

            Label personLabel = new Label(person + ": ", personLabelSkin);
            textGroup.addActor(personLabel);

            Label contentLabel = new Label(textContent, labelSkin);
            textGroup.addActor(contentLabel);

            table.add(textGroup).colspan(labelColSpan).pad(-PADDING, PADDING / 2, 0, PADDING / 2).fill();

        }


        //Initialize button row
        if (buttonCount > 0) {
            table.row().height(BUTTON_ROW_HEIGHT);

            int buttonWidth = ((TABLE_WIDTH - (2 * PADDING)) / buttonCount) - (PADDING);
            for (int i = 0; i < buttonCount; i++) {

                final SpeechBoxButton button = buttons.get(i); //find button in array

                //Create button, and add listener for click event
                TextButton buttonElement = new TextButton(button.text, buttonSkin);
                buttonElement.addListener(new ClickListener()
                {
                    @Override
                    public void clicked(InputEvent event, float x, float y)
                    {
                        //Trigger click event handler for current button (see button definition)
                        button.eventHandler.trigger(button.result);
                    }
                });

                //Add button to table, with appropriate spacing
                table.add(buttonElement).width(buttonWidth).pad(PADDING, PADDING / 2, 0, PADDING / 2);

            }
        }


        //Pack table
        table.pack();
    }

    /**
     * Sets up skin variables used for defining UI control styles
     */
    private void initSkins()
    {
        initButtonSkin();
        initLabelSkin();
        initPersonLabelSkin();
    }

    /**
     * Sets up the skin for buttons on the speech box
     */
    private void initButtonSkin()
    {
        //Create a roomTagFont
        BitmapFont font = new BitmapFont();
        font.setColor(TEXT_COLOUR);
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        buttonSkin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = buttonSkin.newDrawable("background", Color.BLACK);
        textButtonStyle.checked = buttonSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.over = buttonSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", textButtonStyle);

    }

    /**
     * Sets up the skin for buttons on the speech box
     */

    private void initLabelSkin()
    {
        labelSkin = new Skin();

        Label.LabelStyle fontStyle = new Label.LabelStyle();
        BitmapFont font = new BitmapFont();
        fontStyle.font = font;
        fontStyle.fontColor = TEXT_COLOUR;

        labelSkin.add("default", fontStyle);
    }

    /**
     * Sets up the skin for buttons on the speech box
     */
    private void initPersonLabelSkin()
    {
        personLabelSkin = new Skin();

        Label.LabelStyle fontStyle = new Label.LabelStyle();
        BitmapFont font = new BitmapFont();
        fontStyle.font = font;
        fontStyle.fontColor = Color.SCARLET;

        personLabelSkin.add("default", fontStyle);
    }
}
