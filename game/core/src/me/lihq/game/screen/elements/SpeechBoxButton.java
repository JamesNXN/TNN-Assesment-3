package me.lihq.game.screen.elements;

/**
 * A button to use with the SpeechBox element
 * Use in an ArrayList, and pass into SpeechBox
 */
public class SpeechBoxButton {

    public String text;
    public EventHandler eventHandler;

    /**
     * Constructor for SpeechBoxButton
     * @param buttonText String to display on button
     * @param eventHandlerVal On click event handler - use a Lambda function (Java8 only)
     */
    public SpeechBoxButton(String buttonText, EventHandler eventHandlerVal) {
        text = buttonText;
        eventHandler = eventHandlerVal;
    }

    /**
     * Event handler interface
     * Used for defining the click event handler on a SpeechBoxButton
     *
     * Initialising an event handler:
     *   SpeechBoxButton.EventHandler eventHandler = (String name) -> {
     *      System.out.println(name + " was pressed");
     *   };
     *
     *  Usage:
     *    Used in SpeechBox class on button click
     *    SpeechBoxButton.eventHandler.trigger();
     */
    public interface EventHandler {
        void trigger(String buttonText);
    }
}