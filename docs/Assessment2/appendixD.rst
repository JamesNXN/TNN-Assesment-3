D: Acceptance Tests
==============================

Below is a table of the acceptance tests included within this project.

These tests need to be manually run using a copy of the game executable.
They involve following a series of steps, verifying each of the
assertions in the steps is true, and that the relevant GUI exists to
allow the steps to be carried out. If all of the steps can be carried
out, and their assertions are true, the test passes. If not, the test
fails.

The acceptance tests are associated with an appropriate requirement to
allow for traceability, and the tests aim to check that the code works
for any associated requirements. Not all requirements have associated
tests, and vice versa - this is because some requirements cannot be
explicitly unit tested, and some tests do not link up directly to a
requirement, but are still needed to ensure the code functions as
intended.

There is a criticality measure against each test, for both acceptance
and unit tests - this is to represent how important the test is to the
overall function of the code. Criticality is on a scale - high
criticality means that if that test fails, the project will not function
at all; low criticality means that if the test fails, the project will
still mostly function as intended.

Test Listing
-------------
+----------------+----------------+----------------+----------------+----------------+
| ID             | Test Steps     | Req ID         | Criticality    | Result         |
+----------------+----------------+----------------+----------------+----------------+
| 2.01           | Run game       | 1.1.1          | Low            | Passed         |
|                | executable.    |                |                |                |
|                | Main menu is   |                |                |                |
|                | shown.         |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.02           | Run game       | 1.1.2          | High           | Passed         |
|                | executable     |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu Player    |                |                |                |
|                | can move       |                |                |                |
|                | around using   |                |                |                |
|                | arrow keys and |                |                |                |
|                | “WASD” keys    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.03           | Run game       | 1.1.3          | Medium         | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Play     |                |                |                |
|                | game to        |                |                |                |
|                | completion.    |                |                |                |
|                | Repeat at      |                |                |                |
|                | least 3 times. |                |                |                |
|                | Ensure the     |                |                |                |
|                | combination of |                |                |                |
|                | selected       |                |                |                |
|                | killer,        |                |                |                |
|                | victim, murder |                |                |                |
|                | location and   |                |                |                |
|                | murder weapon  |                |                |                |
|                | differ each    |                |                |                |
|                | time.          |                |                |                |
|                | Note: This     |                |                |                |
|                | failed as      |                |                |                |
|                | murder weapon  |                |                |                |
|                | clue is not    |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.04           | Run game       | 1.2.1          | Low            | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Click    |                |                |                |
|                | the ‘pause’    |                |                |                |
|                | button Pause   |                |                |                |
|                | menu is shown  |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.05           | Run game       | 1.3.1          | Low            | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Player   |                |                |                |
|                | should move    |                |                |                |
|                | around using   |                |                |                |
|                | the gamepad    |                |                |                |
|                | Player can     |                |                |                |
|                | interact with  |                |                |                |
|                | Items and NPCs |                |                |                |
|                | using the      |                |                |                |
|                | gamepad        |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.06           | Run game       | 1.3.2          | Low            | Failed         |
|                | executable.    |                |                |                |
|                | Ensuring       |                |                |                |
|                | speakers are   |                |                |                |
|                | turned on, the |                |                |                |
|                | game plays     |                |                |                |
|                | music.         |                |                |                |
|                | Note: the      |                |                |                |
|                | music is not   |                |                |                |
|                | currently      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.07           | Run game       | 1.3.3          | Low            | Failed         |
|                | executable.    |                |                |                |
|                | Click on       |                |                |                |
|                | ‘options’      |                |                |                |
|                | button.        |                |                |                |
|                | Uncheck the    |                |                |                |
|                | ‘music’        |                |                |                |
|                | checkbox.      |                |                |                |
|                | Music stops    |                |                |                |
|                | playing.       |                |                |                |
|                | Note: the      |                |                |                |
|                | music is not   |                |                |                |
|                | currently      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.08           | Run game       | 2.1.2          | Medium         | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Interact |                |                |                |
|                | with an NPC.   |                |                |                |
|                | The ‘accuse’   |                |                |                |
|                | button is not  |                |                |                |
|                | visible.       |                |                |                |
|                | Collect 4      |                |                |                |
|                | clues.         |                |                |                |
|                | Interact with  |                |                |                |
|                | an NPC. The    |                |                |                |
|                | ‘accuse’       |                |                |                |
|                | button is now  |                |                |                |
|                | visible.       |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.09           | Run game       | 2.1.3          | Low            | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Player   |                |                |                |
|                | starts in a    |                |                |                |
|                | location.      |                |                |                |
|                | Close game.    |                |                |                |
|                | Run game       |                |                |                |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Player   |                |                |                |
|                | starts in same |                |                |                |
|                | location       |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.10           | Run game       | 2.1.4          | High           | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Navigate |                |                |                |
|                | the player to  |                |                |                |
|                | a doorway      |                |                |                |
|                | (marked by a   |                |                |                |
|                | carpet). Move  |                |                |                |
|                | through the    |                |                |                |
|                | doorway.       |                |                |                |
|                | Player is be   |                |                |                |
|                | in a different |                |                |                |
|                | room.          |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.11           | Run game       | 2.2.1          | Medium         | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu.          |                |                |                |
|                | Personality    |                |                |                |
|                | meter displays |                |                |                |
|                | the current    |                |                |                |
|                | personality    |                |                |                |
|                | level.         |                |                |                |
|                | Note: the      |                |                |                |
|                | personality    |                |                |                |
|                | meter is not   |                |                |                |
|                | currently      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.12           | Run game       | 3.1.2          | High           | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Navigate |                |                |                |
|                | through all    |                |                |                |
|                | rooms of the   |                |                |                |
|                | map. There     |                |                |                |
|                | are 10 NPCs    |                |                |                |
|                | around the     |                |                |                |
|                | map.           |                |                |                |
|                | Note: this     |                |                |                |
|                | failed as for  |                |                |                |
|                | Assessment 2   |                |                |                |
|                | only 6 NPCs    |                |                |                |
|                | have been      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.13           | Run game       | 4.1.1          | High           | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Navigate |                |                |                |
|                | through all    |                |                |                |
|                | rooms of the   |                |                |                |
|                | map. There are |                |                |                |
|                | 10 distinct    |                |                |                |
|                | rooms that the |                |                |                |
|                | player has     |                |                |                |
|                | traveled       |                |                |                |
|                | through.       |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.14           | Run game       | 5.1.1          | High           | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Navigate |                |                |                |
|                | through all    |                |                |                |
|                | rooms of the   |                |                |                |
|                | map. The       |                |                |                |
|                | player can     |                |                |                |
|                | find at least  |                |                |                |
|                | one clue in    |                |                |                |
|                | each room.     |                |                |                |
|                | Note: this     |                |                |                |
|                | failed as for  |                |                |                |
|                | Assessment 2   |                |                |                |
|                | at most one    |                |                |                |
|                | clue is in     |                |                |                |
|                | each room      |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.15           | Run game       | 5.1.2          | High           | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Find a   |                |                |                |
|                | clue. The      |                |                |                |
|                | player can     |                |                |                |
|                | interact with  |                |                |                |
|                | the clue.      |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.16           | Run game       | 5.2.1          | Medium         | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Click    |                |                |                |
|                | the            |                |                |                |
|                | ‘inventory’    |                |                |                |
|                | button.        |                |                |                |
|                | Inventory      |                |                |                |
|                | screen is      |                |                |                |
|                | shown.         |                |                |                |
|                | Note: the      |                |                |                |
|                | inventory is   |                |                |                |
|                | not currently  |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.17           | Run game       | 6.1.1          | Low            | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Score is |                |                |                |
|                | displayed in   |                |                |                |
|                | the status     |                |                |                |
|                | bar.           |                |                |                |
|                | Note: the      |                |                |                |
|                | score is not   |                |                |                |
|                | currently      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.18           | Run game       | 7.1.1          | High           | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Navigate |                |                |                |
|                | the player to  |                |                |                |
|                | an NPC. The    |                |                |                |
|                | player should  |                |                |                |
|                | be able to     |                |                |                |
|                | talk to the    |                |                |                |
|                | NPC.           |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.19           | Run game       | 7.1.2          | High           | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Interact |                |                |                |
|                | with an NPC.   |                |                |                |
|                | Select         |                |                |                |
|                | ‘Question’     |                |                |                |
|                | button. The    |                |                |                |
|                | player can     |                |                |                |
|                | question the   |                |                |                |
|                | NPC.           |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.20           | Run game       | 7.1.3          | Medium         | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Interact |                |                |                |
|                | with an NPC.   |                |                |                |
|                | Select         |                |                |                |
|                | ‘Ignore’       |                |                |                |
|                | button. The    |                |                |                |
|                | player can     |                |                |                |
|                | ignore the     |                |                |                |
|                | NPC.           |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.21           | Run game       | 7.1.4          | High           | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Pick up  |                |                |                |
|                | at least 4     |                |                |                |
|                | clues Interact |                |                |                |
|                | with an NPC.   |                |                |                |
|                | Select         |                |                |                |
|                | ‘Accuse’       |                |                |                |
|                | button. The    |                |                |                |
|                | player can     |                |                |                |
|                | accuse the     |                |                |                |
|                | NPC.           |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.22           | Run game       | 7.1.5          | High           | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Find a   |                |                |                |
|                | clue Interact  |                |                |                |
|                | with an NPC.   |                |                |                |
|                | Select         |                |                |                |
|                | ‘Question’     |                |                |                |
|                | button. The    |                |                |                |
|                | player should  |                |                |                |
|                | be able to ask |                |                |                |
|                | the NPC        |                |                |                |
|                | various        |                |                |                |
|                | questions.     |                |                |                |
+----------------+----------------+----------------+----------------+----------------+

+----------------+----------------+----------------+----------------+----------------+
| 2.23           | Run game       | 1.1.4          | Medium         | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Game     |                |                |                |
|                | runs at over   |                |                |                |
|                | 30 frames per  |                |                |                |
|                | second.        |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.24           | In Windows 10  | 1.1.5          | High           | Passed         |
|                | Game           |                |                |                |
|                | executable run |                |                |                |
|                | s.             |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.25           | In macOS Game  | 1.2.2          | High           | Passed         |
|                | executable     |                |                |                |
|                | runs.          |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.26           | In Mobile Game | 1.3.4          | Low            | Failed         |
|                | executable     |                |                |                |
|                | runs.          |                |                |                |
|                | Note: a mobile |                |                |                |
|                | version is not |                |                |                |
|                | currently      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.27           | Run game       | 3.1.5          | Low            | Passed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Interact |                |                |                |
|                | with an NPC    |                |                |                |
|                | Ask the NPC a  |                |                |                |
|                | nice question  |                |                |                |
|                | Ask the NPC an |                |                |                |
|                | aggressive     |                |                |                |
|                | question       |                |                |                |
|                | Ensure that    |                |                |                |
|                | the two        |                |                |                |
|                | responses from |                |                |                |
|                | the NPC        |                |                |                |
|                | include one    |                |                |                |
|                | helpful        |                |                |                |
|                | response, and  |                |                |                |
|                | one unhelpful  |                |                |                |
|                | response       |                |                |                |
|                |                |                |                |                |

+----------------+----------------+----------------+----------------+----------------+
| 2.28           | Run game       | 5.1.3          | Medium         | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Interact |                |                |                |
|                | with an NPC.   |                |                |                |
|                | ‘Accuse’       |                |                |                |
|                | button is not  |                |                |                |
|                | visible. Find  |                |                |                |
|                | and interact   |                |                |                |
|                | with the       |                |                |                |
|                | murder weapon. |                |                |                |
|                | Interact with  |                |                |                |
|                | an NPC.        |                |                |                |
|                | ‘Accuse’       |                |                |                |
|                | button is now  |                |                |                |
|                | visible.       |                |                |                |
|                | Note: the      |                |                |                |
|                | murder weapon  |                |                |                |
|                | clue is not    |                |                |                |
|                | currently      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.29           | Run game       | 5.2.2          | High           | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Find a   |                |                |                |
|                | clue. Interact |                |                |                |
|                | with clue.     |                |                |                |
|                | Click          |                |                |                |
|                | ‘Inventory’.   |                |                |                |
|                | The clue       |                |                |                |
|                | appears in the |                |                |                |
|                | Inventory.     |                |                |                |
|                | Note: the      |                |                |                |
|                | inventory is   |                |                |                |
|                | not currently  |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.30           | Run game       | 6.1.2          | Medium         | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Confirm  |                |                |                |
|                | the score      |                |                |                |
|                | decreases as   |                |                |                |
|                | time passes.   |                |                |                |
|                | Note: the      |                |                |                |
|                | score is not   |                |                |                |
|                | currently      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.31           | Run game       | 6.1.3          | Medium         | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Make     |                |                |                |
|                | note of        |                |                |                |
|                | current score. |                |                |                |
|                | Interact with  |                |                |                |
|                | an NPC. Accuse |                |                |                |
|                | the NPC.       |                |                |                |
|                | Confirm the    |                |                |                |
|                | score has      |                |                |                |
|                | reduced.       |                |                |                |
|                | Note: the      |                |                |                |
|                | score is not   |                |                |                |
|                | currently      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.32           | Run game       | 6.1.4          | Medium         | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Make     |                |                |                |
|                | note of        |                |                |                |
|                | current score. |                |                |                |
|                | Interact with  |                |                |                |
|                | an NPC.        |                |                |                |
|                | Question the   |                |                |                |
|                | NPC. Confirm   |                |                |                |
|                | the score has  |                |                |                |
|                | reduced.       |                |                |                |
|                | Note: the      |                |                |                |
|                | score is not   |                |                |                |
|                | currently      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.33           | Run game       | 7.1.7          | Medium         | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Ensuring |                |                |                |
|                | personality    |                |                |                |
|                | score is not   |                |                |                |
|                | an extreme     |                |                |                |
|                | value, note    |                |                |                |
|                | down           |                |                |                |
|                | personality    |                |                |                |
|                | meter reading. |                |                |                |
|                | Interact with  |                |                |                |
|                | an NPC.        |                |                |                |
|                | Question the   |                |                |                |
|                | NPC in a       |                |                |                |
|                | non-neutral    |                |                |                |
|                | way. If the    |                |                |                |
|                | question was   |                |                |                |
|                | positive, the  |                |                |                |
|                | personality    |                |                |                |
|                | meter is now   |                |                |                |
|                | higher. If the |                |                |                |
|                | question was   |                |                |                |
|                | negative, the  |                |                |                |
|                | personality    |                |                |                |
|                | meter is now   |                |                |                |
|                | lower.         |                |                |                |
|                | Note: the      |                |                |                |
|                | personality    |                |                |                |
|                | meter is not   |                |                |                |
|                | currently      |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.34           | Run game       | 7.1.8          | Low            | Failed         |
|                | executable.    |                |                |                |
|                | Start game     |                |                |                |
|                | using main     |                |                |                |
|                | menu. Interact |                |                |                |
|                | with an NPC.   |                |                |                |
|                | Accuse the     |                |                |                |
|                | NPC. Interact  |                |                |                |
|                | with the NPC   |                |                |                |
|                | again. The NPC |                |                |                |
|                | ‘refuses’ to   |                |                |                |
|                | interact.      |                |                |                |
|                | Note: this is  |                |                |                |
|                | not currently  |                |                |                |
|                | implemented    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
