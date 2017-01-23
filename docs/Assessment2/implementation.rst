Implementation Report
=====================

The requirements we set out for ourselves, along with the requirements
for assessment 2, unfortunately, haven’t all been implemented.

Assessment 2 specific requirements The GUI:

The GUI for the game is partially implemented, in our overall
requirements we specify several GUI related requirements.

[1.1.1] “To start the game there must be a main menu”. This has been
implemented and allows the user to start the game via the click of a
Start Game button. When the button is pressed it moves to the
NavigationScreen [2], which is the main screen for the game.

[1.1.2] “The game must be fully controlled by a mouse and keyboard”.
This requirement has been fully met all the buttons on the game are
clickable by the mouse and the player is controlled by the keyboard. To
manage and prioritize the inputs we are using a InputMultiplexer [1]

[5.2.1] “There should be an inventory where clues can be placed by a
player for future reference”. This hasn’t been implemented fully, at
this stage we felt there were other GUI elements that needed to be
implemented before we implemented the GUI for this, the code that stores
clues when the player has picked them up does work, so it’s just the GUI
representation that is missing.

At most 6 detectives:

This has been fully implemented with 6 detectives being randomly
distributed about the map each game instance. In regards to our overall
requirements [3.1.2], “There must be at least 10 non playable
characters”. 6/10 non playable characters have been implemented and the
code will allow for more without any modification other than the
construct of the additional NPC’s [2].

Question or accuse (but not ignore):

Both question and accuse have been partially been implemented the
related overall requirements below explain this in more detail.

[7.1.2] “The player must have the option of questioning an NPC”. A
Player [2] can question a NPC [2] about a clue that they have found.

[7.1.6] “Each NPC must respond differently to questions based on the
personality of the NPC and the personality level of the player”. This
was partially implemented with a lot of the groundwork in place for
future development currently the player can select how they want to ask
the question, the NPC [2] will then only respond with a helpful answer
if you ask it in a way that matches their personality.

[2.1.2] ”Must not be able to accuse a NPC unless enough evidence has
been found”, this has been partially implemented. Since the Player class
keeps track of the evidence or clues found so far, it checks to see if
the player has 4 or more clues if it doesn’t the the option to accuse is
not shown. However, [5.1.3] “The murder weapon clue must be found before
the player can accuse any NPCs” has not been implemented as not all
clues had been finalised at this stage.

At most one clue per room:

This requirement has been fully met, no more than one clue has been
place in any given room. This has been completed by randomly placing the
clues around the rooms. Not placing a clue in a room that already has a
clue unless there are no empty rooms left.

[5.1.1] “There must be at least one clue in each room of the map”. This
has been partially implemented as there is one clue in 7/10 rooms
meeting the requirements of assessment 2

Bibliography
---------

[1]
https://github.com/libgdx/libgdx/wiki/Event-handling#inputmultiplexer

[2] http://lihq.me/docs/JavaDocs