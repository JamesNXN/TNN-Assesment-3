Requirements
============

Introduction
-------------

First the team read the brief and associated documentation. Then we got
together and discussed our ideas, coming up with a rough draft of basic
and requirements. From these we discussed the more ambiguous points,
such as what sort of view the player will use, and where the murder will
take place. Then we picked out the most ambiguous parts that prevented
us from continuing with basic designs and discussed them with our
customer. After two interviews with our customer we finalised the
requirements.

At the first group meeting we played cluedo to get a feel for a general
detective game. This sparked great discussion, and allowed for great
strides to be made on requirements very early on, by giving us an idea
of what works in a game. This also allowed us to discuss ideas that
would be interesting but more difficult to implement, which we left as
possible expansion if overall time costs do not overrun. We also
produced a number of user scenarios[1] which we used to help guide our
thinking when producing requirements.

After discussing our ideas on clues for a bit, as a group we decided
that the most effective way of deducing the murderer was to attempt a
‘guess who’ style. This way the player can cross off certain NPCs upon
finding clues that confirm their alibis. This should allow the player to
feel as though they are actually solving the crime, rather than just
witnessing the solving.

The requirements are sorted into two tables, functional and
non-functional. Functional requirements are things that the system does,
so mainly requirements for when the game is running. Non-functional
requirements are things that the system is, so mainly qualities the game
must have, or objects that are hard coded.

The tables below are numbered using the following system:

*  The first number is category (The bit in bold e.g. NPCs)
*  The second number represents ‘must(1), should(2), could(3)’ (the
   priority of achieving the requirement)
*  The third number is the requirements position in the list (to make it
   easier to find and refer to)

The requirements are laid out in tables based on the IEEE standard for
system requirements for ease of access. By giving each requirement a row
and a number all information is easy to find quickly in future. Any and
all associated risks are discussed in the risks document and are
referenced in the tables.

Some requirements are based on considerations of environmental
assumptions of the game. Requirements based on hardware have all taken
into account the systems that the game will run on. We produced a survey
[2] which we got a small number of our target market to answer. From
this we found that the preferred way of interaction with the game was
keyboard and mouse. Although our survey produced no results of
colourblind people, it is a condition apparent in 1 in 12 men and 1 in
200 women [3] so we still feel that it is a requirement that some people
would benefit from, hence why it is listed as ‘could’.


Functional Requirements
------------------------

NPCs
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.1.1          | The murderer   | Upon loading   | N/A -          |                |
|                | and victim     | the game at    | necessary      |                |
|                | must be        | least 2 times  | requirement.   |                |
|                | randomly       | either or both |                |                |
|                | selected each  | the victim and |                |                |
|                | time the game  | murderer will  |                |                |
|                | begins from    | change.        |                |                |
|                | two sub-lists  |                |                |                |
|                | of murderers   |                |                |                |
|                | and victims.   |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.1.2          | The NPCs must  | Upon loading   | NPCs will      | 21             |
|                | spawn randomly | of the game    | spawn in       |                |
|                | into rooms so  | the NPCs will  | different      |                |
|                | that each room | be spread      | rooms.         |                |
|                | has at least   | around the map |                |                |
|                | one NPC at the | so that there  |                |                |
|                | start of the   | is no room     |                |                |
|                | game.          | without an     |                |                |
|                |                | NPC.           |                |                |
+----------------+----------------+----------------+----------------+----------------+

MAP
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.1.1          | Rooms on the   | Walk through   | All of the map | 21             |
|                | viewable map   | map and check  | will be        |                |
|                | must be        | that rooms are | visible from   |                |
|                | revealed only  | only revealed  | the start.     |                |
|                | once the       | once visited.  |                |                |
|                | player has     |                |                |                |
|                | visited the    |                |                |                |
|                | respective     |                |                |                |
|                | rooms.         |                |                |                |
+----------------+----------------+----------------+----------------+----------------+

Dialogue/Interaction
~~~~~~~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 3.1.1          | The player     | Ensure while   | N/A -          |                |
|                | must always    | game is        | necessary      |                |
|                | get three      | running the    | requirement.   |                |
|                | choices of     | player can use |                |                |
|                | interaction    | any of these   |                |                |
|                | with NPCs -    | interactions   |                |                |
|                | Question,      | with NPCs.     |                |                |
|                | Accuse and     |                |                |                |
|                | Ignore.        |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 3.1.2          | All dialogue   | Not every NPC  | Each NPC has   | 3              |
|                | must change    | will respond   | set lines of   |                |
|                | based on the   | in the same    | dialogue.      |                |
|                | characteristic | way to the     |                |                |
|                | s              | same question, |                |                |
|                | of the player  | the options    |                |                |
|                | NPC and NPC.   | the player has |                |                |
|                |                | to interact    |                |                |
|                |                | with the       |                |                |
|                |                | player changes |                |                |
|                |                | throughout the |                |                |
|                |                | game.          |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 3.1.3          | The game       | Check          | N/A -          |                |
|                | should have a  | functionality  | otherwise the  |                |
|                | method, for    | as described   | player cannot  |                |
|                | example an     | throughout a   | review         |                |
|                | interface to   | play-through.  | information    |                |
|                | allow the      | Ensure that    | collected.     |                |
|                | player to view | there is a     |                |                |
|                | the map, and   | clear way of   |                |                |
|                | some form a    | displaying and |                |                |
|                | information on | closing        |                |                |
|                | NPCs, clues    | interfaces.    |                |                |
|                | found and      |                |                |                |
|                | other points   |                |                |                |
|                | of interest.   |                |                |                |
+----------------+----------------+----------------+----------------+----------------+

Clues
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 4.1.1          | Clues must     | Ensure that    | N/A - clues    |                |
|                | help with the  | all clues look | are necessary. |                |
|                | elimination    | meaningful,    |                |                |
|                | process, but   | but some have  |                |                |
|                | some will      | false          |                |                |
|                | point to more  | assumptions    |                |                |
|                | than one NPC.  | associated     |                |                |
|                |                | with them.     |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 4.1.2          | The murder     | Make sure the  | Murder weapon  | 21             |
|                | weapon must be | player cannot  | does not have  |                |
|                | found before   | accuse NPCs    | to be found    |                |
|                | the player is  | before they    | before the     |                |
|                | able to accuse | have found the | player is able |                |
|                | any NPCs.      | murder weapon. | to accuse      |                |
|                |                |                | NPCs.          |                |
+----------------+----------------+----------------+----------------+----------------+

Score
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.1.1          | The score must | If the player  | There will be  | 21             |
|                | be raised when | finds a clue,  | no scoring.    |                |
|                | a clue is      | the score is   |                |                |
|                | found.         | raised.        |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.1.2          | The score must | The score      | There will be  | 21             |
|                | be lowered for | changes as     | no scoring.    |                |
|                | each wrong     | required.      |                |                |
|                | accusation and |                |                |                |
|                | each question  |                |                |                |
|                | asked.         |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.3.1          | The player     | Check that     | There will be  | 21             |
|                | could start    | score reduces  | no scoring.    |                |
|                | with a         | as time is     |                |                |
|                | predetermined  | spent playing  |                |                |
|                | score which is | the game.      |                |                |
|                | reduced for    |                |                |                |
|                | each  second   |                |                |                |
|                | played.        |                |                |                |
+----------------+----------------+----------------+----------------+----------------+

Other/System
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.3.2          | IF (dependant  | Check that     | The game will  | 21             |
|                | on 6.3.1) the  | sound turns    | have no        |                |
|                | game has a     | on/off when    | soundtrack.    |                |
|                | soundtrack it  | appropriate    |                |                |
|                | must have a    | option chosen. |                |                |
|                | ‘sound on/off’ |                |                |                |
|                | option.        |                |                |                |
+----------------+----------------+----------------+----------------+----------------+

Non-Functional Requirements
----------------------------

NPCs
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.1.3          | The game must  | The game       | N/A -          |                |
|                | have a cast of | contains 10    | necessary      |                |
|                | 10 NPCs        | NPCs.          | requirement.   |                |
|                | (Non-Player    |                |                |                |
|                | NPCs).         |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.1.4          | The murderer   | Game functions | The murderer   | 21             |
|                | must have a    | as described.  | will not have  |                |
|                | motive that    |                | a clear        |                |
|                | becomes clear  |                | motive.        |                |
|                | at some point  |                |                |                |
|                | in the game,   |                |                |                |
|                | not            |                |                |                |
|                | necessarily    |                |                |                |
|                | before they    |                |                |                |
|                | are accused.   |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 1.1.5          | There must be  | The narrator   | There will be  | 3              |
|                | a narrator who | talks to the   | no narrator.   |                |
|                | acts as the    | player.        |                |                |
|                | tutorial and   |                |                |                |
|                | further help   |                |                |                |
|                | during         |                |                |                |
|                | gameplay.      |                |                |                |
+----------------+----------------+----------------+----------------+----------------+

MAP
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.1.2          | The game must  | The game will  | N/A -          |                |
|                | contain a      | have 10 rooms  | necessary      |                |
|                | game-map of 10 | that are       | requirement.   |                |
|                | separate       | accessible to  |                |                |
|                | rooms, spread  | the player.    |                |                |
|                | across the     |                |                |                |
|                | setting of     |                |                |                |
|                | ‘The Ron Cooke |                |                |                |
|                | Hub’.          |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.2.1          | The room of    | Upon loading   | The crime      | 21             |
|                | the crime      | the game at    | scene is       |                |
|                | scene/murder   | least 2 times  | always in the  |                |
|                | room must be   | the murder     | same place.    |                |
|                | chosen         | room will      |                |                |
|                | randomly each  | change.        |                |                |
|                | time the game  |                |                |                |
|                | begins.        |                |                |                |
+----------------+----------------+----------------+----------------+----------------+

Dialogue/Interaction
~~~~~~~~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 3.1.4          | The game must  | Play through   | N/A -          |                |
|                | have multiple  | the game       | necessary      |                |
|                | ‘plot lines’ . | multiple       | requirement.   |                |
|                |                | times,         |                |                |
|                |                | checking that  |                |                |
|                |                | the plot lines |                |                |
|                |                | differ each    |                |                |
|                |                | time.          |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 3.2.1          | Some plotlines | Play through   | The game line  | 3              |
|                | could be more  | the game and   | has similar    |                |
|                | intricate than | determine that | plot lines.    |                |
|                | others.        | some plotlines |                |                |
|                |                | are more       |                |                |
|                |                | complicated .  |                |                |
+----------------+----------------+----------------+----------------+----------------+

Clues
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 4.1.3          | There must be  | Make sure that | N/A -          |                |
|                | at least one   | clues spawn in | necessary      |                |
|                | clue to find   | each room.     | requirement.   |                |
|                | in each room   |                |                |                |
|                |  on the map.   |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 4.2.1          | Some           | Check that     | There are no   | 21             |
|                | ‘constant’     | consistent     | ‘constant’     |                |
|                | clues should   | clues spawn in | clues.         |                |
|                | be available,  | the correct    |                |                |
|                | for example    | place on at    |                |                |
|                | the guest sign | least 2        |                |                |
|                | in book in the | separate       |                |                |
|                | central part   | occasions .    |                |                |
|                | of the map.    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 4.2.2          | Some rooms     | Check that at  | There is only  | 21             |
|                | should have    | least one room | one clue per   |                |
|                | more than one  | has at least   | room.          |                |
|                | clue e..g note | one clue in.   |                |                |
|                | left by        |                |                |                |
|                | victim/murder  |                |                |                |
|                | weapon.        |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 4.3.1          | The player     | Check the      | All clues will | 3              |
|                | could be able  | player can     | be meaningful. |                |
|                | to interact    | interact with  |                |                |
|                | with or pick   | some item and  |                |                |
|                | up some items  | it not be      |                |                |
|                | which are not  | listed as a    |                |                |
|                | clues.         | clue.          |                |                |
+----------------+----------------+----------------+----------------+----------------+

Score
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.1.3          | The player     | Play through   | There will be  | 21             |
|                | must be scored | the game at    | no scoring     |                |
|                | on time taken, | least 3 times  | system.        |                |
|                | number of      | to check that  |                |                |
|                | wrong          | scores add up  |                |                |
|                | accusations,   | as expected.   |                |                |
|                | number of      |                |                |                |
|                | questions      |                |                |                |
|                | asked and      |                |                |                |
|                | number of      |                |                |                |
|                | clues found.   |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 5.3.2          | A list of      | Check the      | There will be  | 3              |
|                | high-scores    | server         | a local list   |                |
|                | could be       | contains the   | of high scores |                |
|                | stored on a    | high scores.   | or no list of  |                |
|                | server.        |                | high scores.   |                |
+----------------+----------------+----------------+----------------+----------------+

Other/System
~~~~~~~~~~~~~~
+----------------+----------------+----------------+----------------+----------------+
| No.            | Requirement    | Success        | Alternative    | Risk ID        |
|                |                | Criteria       | Requirements   |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.1.1          | The game will  | Determine the  | N/A -          |                |
|                | be controlled  | game is        | necessary      |                |
|                | by keyboard    | controlled as  | requirement.   |                |
|                | with mouse     | described.     |                |                |
|                | integration.   |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.1.2          | The game must  | Determine the  | N/A -          |                |
|                | play on a      | game runs on   | necessary      |                |
|                | windows based  | the system     | requirement.   |                |
|                | system.        | described.     |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.1.3          | The game must  | Check the game | The game will  | 5              |
|                | be played in a | is viewed as   | be played in   |                |
|                | ‘top down’     | described.     | an improved    |                |
|                | viewpoint,     |                | viewpoint      |                |
|                | where the      |                | based on the   |                |
|                | player is in   |                | reason for     |                |
|                | the centre of  |                | discarding     |                |
|                | the screen and |                | this one.      |                |
|                | the world      |                |                |                |
|                | moves around   |                |                |                |
|                | the player.    |                |                |                |
|                | The viewpoint  |                |                |                |
|                | is fixed zoom. |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.2.1          | The game       | Use frame-rate | N/A -          |                |
|                | should run     | measuring      | necessary      |                |
|                | smoothly on    | software to    | requirement.   |                |
|                | university     | obtain a       |                |                |
|                | computers.     | frame-rate of  |                |                |
|                |                | at-least 30.   |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.3.1          | The game could | Check that     | The game will  | 21             |
|                | have a         | sound plays    | not have a     |                |
|                | soundtrack.    | when game is   | soundtrack.    |                |
|                |                | running.       |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.3.3          | The game could | When           | The game       | 5              |
|                | have a ‘colour | activated, the | textures will  |                |
|                | blind’         | colourblind    | be designed    |                |
|                | setting.       | setting        | with colour    |                |
|                |                | changes all    | blindness in   |                |
|                |                | textures in    | mind.          |                |
|                |                | the game to    |                |                |
|                |                | ones that are  |                |                |
|                |                | easier for a   |                |                |
|                |                | colour-blind   |                |                |
|                |                | person to see. |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.3.4          | The game could | Make sure game | The game will  | 5              |
|                | be cross       | runs on        | not be cross   |                |
|                | compatible on  | alternative    | compatible.    |                |
|                | mobile         | systems.       |                |                |
|                | (android) and  |                |                |                |
|                | Mac.           |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 6.3.5          | The game could | The game is    | The game will  | 21             |
|                | be controlled  | controlled as  | not use a      |                |
|                | by a gamepad.  | described.     | gamepad.       |                |
+----------------+----------------+----------------+----------------+----------------+

Bibliography
--------------

[1] Appendix A [online] docs.lihq.me/en/latest/AppendixA [Created 21/11/16]

[2] Appendix C [online] docs.lihq.me/en/latest/AppendixC [Created 21/11/16]

[3] Colour Blind awareness [online]
http://www.colourblindawareness.org/colour-blindness/, [Accessed
3/11/16]
