Requirements
============

**Highlighted changes:**

- All requirements have been reviewed and rewritten where appropriate
- IDs have changed due to recategorisation of requirements
- Improved introduction and document formatting
- More details are in the updates report

Introduction
-------------

At our first meeting we read the brief, and with that in mind, we played
cluedo to get a feel for a general detective game. This sparked
discussion as to what works in a game, and it gave us ideas for how our
game should work. We decided the most effective way of thinking about
the game was like “guess who”. Using the brief and our new ideas, we
produced a list of features we’d like to include in the game, and then
prioritised those.

After the initial meeting, we produced a number of user scenarios [1],
and used our list of features to help produce a draft of
the requirements. From these we identified ambiguous points and produced
questions we needed to get answers for. We met multiple times with the
customer throughout the  design process, to present our requirements and
ask questions we had. Using their feedback, we made any necessary
changes.

Following the response we got after the Assessment 1 feedback was
released, we decided it was best to rewrite our requirements. This lead
to substantial improvements in the clarity and categorisation of our
requirements, and this has benefitted us as it made the requirements
easier to test against when it came to implementing the game.

When designing the requirements, we took these points into account:

-  The requirements should be categorised as:

   -  Functional requirements - these define what the system should do
   -  Nonfunctional requirements - the define the behaviour of the system

-  The requirements should be achievable within the time allocated of
   this project
-  The requirements should consider the hardware in which the game
   should run
-  The requirements should meet all points included in the brief
-  We produced a survey asking about input methods and accessibility
   [2]. We got a sample of our target market to respond. From this we
   found:

-  The preferred way of interaction with the game was keyboard and
   mouse.
-  There were no results from colourblind people, however we still felt
   it was important that our game was accessible, so have included
   accessibility features as a “could” requirement. Colour blindness is
   a condition apparent in 1 in 12 men and 1 in 200 women [3] so we feel
   that it is a requirement that some people would benefit from.

The requirements are laid out in tables based on the IEEE standard for
system requirements, as we felt this was a good standard to adhere to.
The tables are split according to type (functional or non-functional),
and category. Some requirements have associated risks, these are
referenced in the table below, and are defined in the risks document .

Each requirement is given a unique identifier to make it easy to locate,
and for traceability. The identifiers are made up of three numbers,
using the following system:

-  The first number is category, this represents the functional area of
   the requirement
-  The second number represents how important the requirement is:

   -  1=Must implement
   -  2=Should implement
   -  3=Could implement

-  The third number is the position in the list, used to ensure the
   identifier is unique.


Functional Requirements
------------------------

Game
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.1.1          | To start the   | The game has a | The game       | 21-24          |
|                | game there     | working clear  | starts         |                |
|                | must be a main | menu           | immediately    |                |
|                | menu           |                | when its       |                |
|                |                |                | opened         |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.1.2          | The game must  | The game can   | The game is    |                |
|                | be fully       | be interacted  | controlled by  |                |
|                | controlled by  | with using     | an Xbox        |                |
|                | a mouse and    | only a mouse   | controller.    |                |
|                | keyboard       | and keyboard   |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.1.3          | Game must have | Each gameplay  | Game only has  | 21-24          |
|                | different      | is different   | 2 plot lines.  |                |
|                | 'plotlines'    | in some way    |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.2.1          | There should   | There is the   | The game       | 21-24          |
|                | be a way of    | option to      | cannot be      |                |
|                | suspending or  | pause the game | paused         |                |
|                | pausing the    | which opens a  |                |                |
|                | game           | pause menu     |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.3.1          | Could be       | The game can   | Game is only   |                |
|                | controlled by  | be interacted  | controllable   |                |
|                | a gamepad      | with using a   | by keyboard    |                |
|                |                | gamepad        | and mouse.     |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.3.2          | Could have a   | Music will be  | There is no    | 21-24          |
|                | sound track    | played when    | sound track.   |                |
|                |                | the game is    |                |                |
|                |                | running        |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.3.3          | If game has a  | There is a     | The soundtrack |                |
|                | soundtrack, it | player         | would always   |                |
|                | must have an   | accessible way | be on.         |                |
|                | option to turn | to turn the    |                |                |
|                | the sound      | sound track    |                |                |
|                | track off      | off within the |                |                |
|                |                | game           |                |                |
+----------------+----------------+----------------+----------------+----------------+

Player
~~~~~~~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.1.1          | The player     | The players    | The player     | 21-24          |
|                | must have a    | personality    | personality    |                |
|                | personality    | changes in the | does not       |                |
|                | that is        | game           | change.        |                |
|                | customisable   |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.1.2          | Must not be    | The player     | Can accuse an  | 21-24          |
|                | able to accuse | cannot see the | NPC without    |                |
|                | a NPC unless   | option to      | evidence.      |                |
|                | enough         | accuse an NPC  |                |                |
|                | evidence has   | unless they    |                |                |
|                | been found     | have           |                |                |
|                |                | interacted     |                |                |
|                |                | with enough    |                |                |
|                |                | clues          |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.1.3          | The player     | When the game  | Player can     |                |
|                | must start in  | starts, the    | start in any   |                |
|                | a central room | player should  | room.          |                |
|                | at the start   | be in the      |                |                |
|                | of every game  | centre of the  |                |                |
|                |                | "Ron Cooke     |                |                |
|                |                | Hub"           |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.1.4          | The player     | The player can | The player     | 21-24          |
|                | must be able   | move           | progresses     |                |
|                | to navigate    | throughout the | through rooms  |                |
|                | between rooms  | map and        | automatically. |                |
|                | on the map     | transition to  |                |                |
|                |                | other rooms    |                |                |
|                |                | when desired   |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.2.1          | The player     | The game       | The Player     | 21-24          |
|                | should be able | should present | will not be    |                |
|                | to see their   | the player     | able to see    |                |
|                | current        | with an GUI    | their current  |                |
|                | personality    | element        | personality    |                |
|                | level          | showing their  | level          |                |
|                |                | personality    |                |                |
|                |                | level          |                |                |
+----------------+----------------+----------------+----------------+----------------+

NPC
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 3.1.1          | Each killer    | The killers    | All killer     | 21-24          |
|                | must have a    | all have       | have the same  |                |
|                | motive         | motives        | motive.        |                |
+----------------+----------------+----------------+----------------+----------------+
| 3.1.2          | There must be  | The player     | There are less | 21-24          |
|                | 10 non         | should be able | than 10 NPC’s. |                |
|                | playable       | to locate 10   |                |                |
|                | characters.    | NPCs in rooms  |                |                |
|                |                | during the     |                |                |
|                |                | game           |                |                |
+----------------+----------------+----------------+----------------+----------------+

Map
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 4.1.1          | The game must  | The player     | The game has   | 21-24          |
|                | have a map     | should be able | less than 10   |                |
|                | containing 10  | to visit 10    | rooms.         |                |
|                | separate rooms | different      |                |                |
|                |                | rooms in the   |                |                |
|                |                | game           |                |                |
+----------------+----------------+----------------+----------------+----------------+

Clue
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.1.1          | There must be  | The player can | Some rooms may | 21-24          |
|                | at least one   | navigate to    | have no clues, |                |
|                | clue in each   | every room and | some may have  |                |
|                | room of the    | be able to     | multiple       |                |
|                | map            | locate a clue  |                |                |
|                |                |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.1.2          | The player     | The player     | The player     | 21-24          |
|                | must be able   | should be able | gets the clue  |                |
|                | to interact    | to interact    | without        |                |
|                | with a clue    | with a clue    | interaction.   |                |
|                |                | once it has    |                |                |
|                |                | been located   |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.2.1          | There should   | The player can | Clues are      | 21-24          |
|                | be an          | see an         | stored         |                |
|                | inventory      | inventory in   | internally but |                |
|                | where clues    | the GUI that   | the player     |                |
|                | can be placed  | allows         | will not be    |                |
|                | by a player    | visibility of  | able to see    |                |
|                | for future     | collected      | them           |                |
|                | reference      | clues          |                |                |
+----------------+----------------+----------------+----------------+----------------+

Score
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.1.1          | There must be  | The player     | There will be  | 21-24          |
|                | a score shown  | must see a     | no scoring.    |                |
|                | to players in  | score          |                |                |
|                | the game       | displayed in   |                |                |
|                |                | the GUI        |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.2.1          | There could be | There could be | There will be  | 21-24          |
|                | an online      | a scoreboard   | a local list   |                |
|                | scoreboard to  | in the GUI     | of high        |                |
|                | keep high      | that presents  | scores, or no  |                |
|                | scores         | the all time   | scoring        |                |
|                |                | high scores    |                |                |
+----------------+----------------+----------------+----------------+----------------+

Dialogue
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 7.1.1          | The player     | A player can   | The player     | 21-24          |
|                | must be able   | go up to an    | cannot         |                |
|                | to interact    | NPC and talk   | interact with  |                |
|                | with an NPC    | to them        | NPC’s.         |                |
+----------------+----------------+----------------+----------------+----------------+
| 7.1.2          | The player     | When a player  | The player     | 21-24          |
|                | must have the  | talks to an    | cannot         |                |
|                | option of      | NPC, they      | question an    |                |
|                | questioning an | should have    | NPC.           |                |
|                | NPC            | the option to  |                |                |
|                |                | question them  |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 7.1.3          | The player     | When a player  | The player     | 21-24          |
|                | must have the  | talks to an    | cannot ignore  |                |
|                | option of      | NPC, they      | an NPC.        |                |
|                | ignoring an    | should have    |                |                |
|                | NPC            | the option to  |                |                |
|                |                | ignore them    |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 7.1.4          | The player     | When a player  | The player     | 21-24          |
|                | must have the  | talks to an    | cannot accuse  |                |
|                | option of      | NPC, they      | an NPC.        |                |
|                | accusing an    | should have    |                |                |
|                | NPC            | the option to  |                |                |
|                |                | accuse them if |                |                |
|                |                | they have      |                |                |
|                |                | found enough   |                |                |
|                |                | clues to       |                |                |
|                |                | accuse the NPC |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 7.1.5          | The player     | When a player  | The player     | 21-24          |
|                | must choose    | talks to an    | only has one   |                |
|                | from a set of  | NPC, and       |                |                |
|                | questions when | chooses to     |                |                |
|                | interacting    | question them, |                |                |
|                | with an NPC    | they can       |                |                |
|                | that reflects  | choose from    |                |                |
|                | different      | multiple       |                |                |
|                | personalities  | speeches with  |                |                |
|                |                | different      |                |                |
|                |                | personality    |                |                |
|                |                | levels. Eg.    |                |                |
|                |                | Aggressive     |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 7.1.6          | Each NPC must  | When an NPC    | All NPC’s      | 21-24          |
|                | respond        | responds to a  | respond in the |                |
|                | differently to | player after   | same way.      |                |
|                | questions from | being          |                |                |
|                | a Player       | questioned,    |                |                |
|                | depending on   | their response |                |                |
|                | both NPC's and | must be        |                |                |
|                | Player's       | determined by  |                |                |
|                | personality    | their          |                |                |
|                | and            | characteristics|                |                |
|                | characteristics| and the        |                |                |
|                |                | player's       |                |                |
|                |                | personality    |                |                |
+----------------+----------------+----------------+----------------+----------------+

Nonfunctional Requirements
---------------------------
Game
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.1.4          | Must run on    | An executable  | The game will  | 7              |
|                | the university | is provided    | not run on     |                |
|                | computers      | the runs on    | university     |                |
|                |                | the computers  | computers.     |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.1.5          | Must run on    | An executable  | The game will  | 7              |
|                | Windows 10     | is provided    | not run on     |                |
|                |                | that runs on   | windows 10.    |                |
|                |                | windows 10     |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.2.2          | Should run on  | An executable  | There will not | 7              |
|                | MacOS          | is provided    | be an          |                |
|                |                | that runs on   | executable     |                |
|                |                | MacOS          | that runs on   |                |
|                |                |                | MacOS          |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.3.4          | Could run on a | An executable  | The game will  |                |
|                | mobile         | is provided to | only run on    |                |
|                | platform       | run on mobile  | desktop        |                |
|                |                | platforms      | operating      |                |
|                |                |                | systems        |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.3.5          | Could have a   | The colours in | There will not | 21-24          |
|                | colour blind   | the game can   | be a colour    |                |
|                | mode           | be configured  | blind mode     |                |
|                |                | by the user to |                |                |
|                |                | be more        |                |                |
|                |                | accessible     |                |                |
+----------------+----------------+----------------+----------------+----------------+

NPC
~~~~~~~~~~~~~~

+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 3.1.5          | Each NPC must  | The NPC will   | All NPC’s have | 21-24          |
|                | have a         | respond best   | the same       |                |
|                | personality    | to different   | personality.   |                |
|                | that affects   | types of       |                |                |
|                | and is         | question. For  |                |                |
|                | affected by    | example, an    |                |                |
|                | game play.     | aggressive NPC |                |                |
|                |                | will respond   |                |                |
|                |                | best when      |                |                |
|                |                | questioned     |                |                |
|                |                | nicely.        |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 3.1.6          | The killer and | When the game  | The killer and | 21-24          |
|                | victim must be | starts, the    | victim is the  |                |
|                | randomly       | victim and the | same every     |                |
|                | selected each  | killer has     | time.          |                |
|                | time the game  | been selected  |                |                |
|                | begins from    | at random.     |                |                |
|                | two sub-lists  |                |                |                |
|                | of killers and |                |                |                |
|                | victims.       |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 3.1.7          | Each NPC must  | All NPCs       | Each NPC is    | 21-24          |
|                | be randomly    | should be      | always in the  |                |
|                | assigned to a  | situated       | same room.     |                |
|                | room at the    | within a       |                |                |
|                | start of the   | different room |                |                |
|                | game           | at the start   |                |                |
|                |                | of the game.   |                |                |
+----------------+----------------+----------------+----------------+----------------+

Map
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 4.1.3          | The room where | One random     | The murder     | 21-24          |
|                | the murder     | room should be | room is always |                |
|                | occurred must  | the selected   | the same.      |                |
|                | be randomly    | murder         |                |                |
|                | selected at    | location at    |                |                |
|                | the start of   | the start of   |                |                |
|                | every game     | every game     |                |                |
+----------------+----------------+----------------+----------------+----------------+

Clues
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.1.3          | The murder     | The player     | Can accuse     | 21-24          |
|                | weapon clue    | cannot accuse  | without the    |                |
|                | must be found  | an NPC until   | murder weapon. |                |
|                | before the     | they've        |                |                |
|                | player can     | located the    |                |                |
|                | accuse any     | murder weapon  |                |                |
|                | NPCs           | clue           |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.1.4          | Most clues     | A clue will    | All clues help | 21-24          |
|                | should help    | narrow down    | identify the   |                |
|                | with           | the number of  | killer         |                |
|                | identifying    | suspects left  |                |                |
|                | the killer     | to be the      |                |                |
|                |                | killer         |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.1.5          | At the start   | There must be  | Clues always   | 21-24          |
|                | of the game,   | at least one   | in same        |                |
|                | clues must be  | clue in every  | location.      |                |
|                | randomly       | room of the    |                |                |
|                | assigned to    | map at the     |                |                |
|                | each room in   | start of the   |                |                |
|                | the map        | game           |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.2.2          | Clues could be | The player can | Clues will be  | 21-24          |
|                | picked up by a | interact with  | stored         |                |
|                | player and     | a clue and     | internally,    |                |
|                | placed in an   | place it in    | but my not be  |                |
|                | inventory      | their          | seen by the    |                |
|                |                | inventory for  | player         |                |
|                |                | future         |                |                |
|                |                | reference      |                |                |
+----------------+----------------+----------------+----------------+----------------+

Score
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.1.2          | The player's   | The score must | There will be  | 21-24          |
|                | score must     | change         | no scoring.    |                |
|                | take into      | depending on   |                |                |
|                | account the    | how long the   |                |                |
|                | time taken     | game has       |                |                |
|                |                | lasted         |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.1.3          | The player's   | The score must | There will be  | 21-24          |
|                | score must     | change         | no scoring.    |                |
|                | take into      | depending on   |                |                |
|                | account the    | how many       |                |                |
|                | number of      | accusations    |                |                |
|                | wrong          | the player has |                |                |
|                | accusations    | made           |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.1.4          | The player's   | The score must | There will be  | 21-24          |
|                | score must     | change         | no scoring.    |                |
|                | take into      | depending on   |                |                |
|                | account the    | how many       |                |                |
|                | number of      | questions the  |                |                |
|                | questions      | player has     |                |                |
|                | asked          | asked          |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.1.5          | The player's   | The score must | There will be  | 21-24          |
|                | score must     | change         | no scoring.    |                |
|                | take into      | depending on   |                |                |
|                | account the    | how many clues |                |                |
|                | number of      | have been      |                |                |
|                | clues found    | found by the   |                |                |
|                |                | player         |                |                |
+----------------+----------------+----------------+----------------+----------------+

Dialogue
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| ID             | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 7.1.7          | The type of    | When a player  | The type of    | 21-24          |
|                | question asked | chooses a      | question asked |                |
|                | to an NPC by a | speech to say  | affects        |                |
|                | player must    | to an NPC,     | nothing.       |                |
|                | determine the  | their          |                |                |
|                | player's       | personality    |                |                |
|                | personality    | level is       |                |                |
|                |                | affected by    |                |                |
|                |                | their choice   |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 7.1.8          | If an NPC is   | When a player  | The NPC does   | 21-24          |
|                | accused and    | interacts with | not mind being |                |
|                | isn't the      | a previously   | falsely        |                |
|                | killer then    | accused NPC    | accused.       |                |
|                | the NPC must   | they shouldn't |                |                |
|                | refuse to      | get a response |                |                |
|                | interact for   |                |                |                |
|                | the rest of    |                |                |                |
|                | the game       |                |                |                |
+----------------+----------------+----------------+----------------+----------------+

Bibliography
--------------

[1] Appendix A [online] docs.lihq.me/en/latest/AppendixA [Created 21/11/16]

[2] Appendix C [online] docs.lihq.me/en/latest/AppendixC [Created 21/11/16]

[3] Colour Blind awareness [online]
http://www.colourblindawareness.org/colour-blindness/, [Accessed
3/11/16]
