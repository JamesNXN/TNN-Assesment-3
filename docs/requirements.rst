Requirements
============

Introduction
-------------

First the team each read through the brief and associated documentation.
Then we got together and discussed our ideas, coming up with a rough
draft of some basic and some more advanced requirements. From these we
discussed the more ambiguous points, such as what sort of view the
player will use, and where the murder will take place. From these we
picked out the most ambiguous parts that prevented us from continuing
with basic designs and discussed them with our customer. After two
interviews with our customer we finalised the requirements.

At the first group meeting we decided to play cluedo to get a feel for a
general detective game. This sparked great discussion, and allowed for
great strides to be made on requirements very early on, by giving us an
idea of what works in a game. This also allowed us to discuss ideas that
would be interesting but more difficult to implement, which we left as
possible expansion if overall time costs do not overrun.

After discussing our ideas on clues for a bit, as a group we decided
that the most effective way of deducing the murderer was to attempt a
‘guess who’ style. This way the player can cross off certain characters
upon finding clues that confirm their alibis. This should allow the
player to feel as though they are actually solving the crime, rather
than just witnessing the solving.

The tables below are numbered using the following system:

The first number is category (The bit in bold e.g. Characters)

The second number represents ‘must(1), should(2), could(3)’ (the
priority of achieving the

requirement)

The third number is the requirements position in the list (to make it
easier to find and refer to)

The numbering is spread across the two tables.

The requirements are sorted into two tables, functional and
non-functional. Functional requirements are things that the system does,
so mainly requirements for when the game is running. Non-functional
requirements are things that the system is, so mainly qualities the game
must have, or objects that are hard coded.

The requirements are laid out in tables for ease of access. By giving
each requirement a row and a number all information is easy to find
quickly in future. Any and all associated risks are discussed in the
risks document.

Some requirements are based on considerations of environmental
assumptions of the game. for example, a ‘colour-blind’ option is listed
below (6.3.2) due to the possibility that a person wanting to play the
game cannot see some colours. Requirements based on hardware have all
taken into account the systems that the game is designed to run on.

Functional Requirements
------------------------

+--------------------+--------------------+--------------------+--------------------+
| No.                | Requirement        | Success Criteria   | Alternative        |
|                    |                    |                    | Requirements       |
+--------------------+--------------------+--------------------+--------------------+
|                    | Characters         |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 1.1.1              | The murderer and   | Upon loading the   | N/A - necessary    |
|                    | victim must be     | game at least 2    | requirement.       |
|                    | randomly selected  | times either or    |                    |
|                    | each time the game | both the victim    |                    |
|                    | begins from two    | and murderer will  |                    |
|                    | sub-lists of       | change.            |                    |
|                    | murderers and      |                    |                    |
|                    | victims.           |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 1.1.2              | The NPCs must      | Upon loading of    | We will have to    |
|                    | spawn randomly     | the game the NPCs  | rotate where the   |
|                    | into rooms so that | will be spread     | NPCs spawn.        |
|                    | each room has at   | around the map so  |                    |
|                    | least one NPC at   | that there is no   |                    |
|                    | the start of the   | room without an    |                    |
|                    | game.              | NPC.               |                    |
+--------------------+--------------------+--------------------+--------------------+
|                    | MAP                |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 2.1.1              | Rooms on the       | Walk through map   | All of the map     |
|                    | viewable map must  | and check that     | will be visible    |
|                    | be revealed only   | rooms are only     | from the start.    |
|                    | once the player    | revealed once      |                    |
|                    | has visited the    | visited.           |                    |
|                    | respective rooms.  |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
|                    | DIALOGUE/INTERACTI |                    |                    |
|                    | ON                 |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 3.1.1              | The player must    | Ensure while game  | N/A - necessary    |
|                    | always get three   | is running the     | requirement.       |
|                    | choices of         | player can use any |                    |
|                    | interaction with   | of these           |                    |
|                    | NPCs - Question,   | interactions with  |                    |
|                    | Accuse and Ignore. | NPCs.              |                    |
+--------------------+--------------------+--------------------+--------------------+
| 3.1.2              | All dialogue must  | With different     | Each character has |
|                    | change based on    | previous           | set lines of       |
|                    | the                | interactions,      | dialogue.          |
|                    | characteristics of | conversations      |                    |
|                    | the player         | between NPCs and   |                    |
|                    | character and NPC. | the player change. |                    |
+--------------------+--------------------+--------------------+--------------------+
| 3.1.3              | The game should    | Check              | N/A - otherwise    |
|                    | have a method, for | functionality as   | the player cannot  |
|                    | example a button   | described          | review information |
|                    | at the side of the | throughout a       | collected.         |
|                    | game, to allow the | play-through.      |                    |
|                    | player to view the |                    |                    |
|                    | map, character     |                    |                    |
|                    | sheets, clues      |                    |                    |
|                    | found and other    |                    |                    |
|                    | points of          |                    |                    |
|                    | interest.          |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
|                    | Clues              |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 4.1.1              | Clues must help    | Ensure that all    | N/A - clues are    |
|                    | with the           | clues look         | necessary.         |
|                    | elimination        | meaningful, but    |                    |
|                    | process, but some  | some have false    |                    |
|                    | will point to more | assumptions        |                    |
|                    | than one           | associated with    |                    |
|                    | character.         | them.              |                    |
+--------------------+--------------------+--------------------+--------------------+
| 4.1.2              | The murder weapon  | Make sure the      | Murder weapon does |
|                    | must be found      | player cannot      | not have to be     |
|                    | before the player  | accuse NPCs before | found before the   |
|                    | is able to accuse  | they have found    | player is able to  |
|                    | any NPCs.          | the murder weapon. | accuse NPCs.       |
+--------------------+--------------------+--------------------+--------------------+
|                    | Score              |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 5.1.1              | The score must be  | If the player      | There will be no   |
|                    | raised when a clue | finds a clue, the  | scoring.           |
|                    | is found.          | score is raised.   |                    |
+--------------------+--------------------+--------------------+--------------------+
| 5.1.2              | The score must be  | The score changes  | There will be no   |
|                    | lowered for each   | as required.       | scoring.           |
|                    | wrong accusation   |                    |                    |
|                    | and each question  |                    |                    |
|                    | asked.             |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 5.3.1              | The player could   | Check that score   | There will be no   |
|                    | start with a       | reduces as time is | scoring.           |
|                    | predetermined      | spent playing the  |                    |
|                    | score which is     | game.              |                    |
|                    | reduced for        |                    |                    |
|                    | each second        |                    |                    |
|                    | played.            |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
|                    | Other/System       |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 6.3.1.1            | IF the game has a  | Check that sound   | The game will have |
|                    | soundtrack it must | turns on/off when  | no soundtrack.     |
|                    | have a ‘sound      | appropriate option |                    |
|                    | on/off’ option.    | chosen.            |                    |
+--------------------+--------------------+--------------------+--------------------+

Non-Functional Requirements

+--------------------+--------------------+--------------------+--------------------+
| No.                | Requirement        | Success Criteria   | Alternative        |
|                    |                    |                    | Requirements       |
+--------------------+--------------------+--------------------+--------------------+
|                    | Characters         |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 1.1.3              | The game must have | The game contains  | N/A - necessary    |
|                    | a cast of 10 NPCs  | 10 NPCs.           | requirement.       |
|                    | (Non-Player        |                    |                    |
|                    | Characters).       |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 1.1.4              | The murderer must  | Game functions as  | The murderer will  |
|                    | have a motive that | described.         | not have a clear   |
|                    | becomes clear at   |                    | motive.            |
|                    | some point in the  |                    |                    |
|                    | game, not          |                    |                    |
|                    | necessarily before |                    |                    |
|                    | they are accused.  |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 1.1.5              | There must be a    | The narrator talks | There will be no   |
|                    | narrator who acts  | to the player.     | narrator.          |
|                    | as the tutorial    |                    |                    |
|                    | and further help   |                    |                    |
|                    | during gameplay.   |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
|                    | MAP                |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 2.1.2              | The game must      | The game will have | N/A - necessary    |
|                    | contain a game-map | 10 rooms.          | requirement.       |
|                    | of 10 separate     |                    |                    |
|                    | rooms, spread      |                    |                    |
|                    | across the setting |                    |                    |
|                    | of ‘The Ron Cooke  |                    |                    |
|                    | Hub’.              |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 2.2.1              | The room of the    | Upon loading the   | The crime scene is |
|                    | crime scene/murder | game at least 2    | always in the same |
|                    | room must be       | times the murder   | place.             |
|                    | chosen randomly    | room will change.  |                    |
|                    | each time the game |                    |                    |
|                    | begins.            |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
|                    | Dialogue/Interacti |                    |                    |
|                    | on                 |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 3.1.4              | The game must have | Play through the   | N/A - necessary    |
|                    | multiple ‘plot     | game multiple      | requirement.       |
|                    | lines’.            | times, checking    |                    |
|                    |                    | that the plot      |                    |
|                    |                    | lines differ each  |                    |
|                    |                    | time.              |                    |
+--------------------+--------------------+--------------------+--------------------+
| 3.2.1              | Some plotlines     | Play through the   | The game line has  |
|                    | could be more      | game and determine | similar plot       |
|                    | intricate than     | that some          | lines.             |
|                    | others.            | plotlines are more |                    |
|                    |                    | complicated.       |                    |
+--------------------+--------------------+--------------------+--------------------+
|                    | Clues              |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 4.1.3              | There must be at   | Make sure that     | N/A - necessary    |
|                    | least one clue to  | clues spawn in     | requirement.       |
|                    | find in each       | each room.         |                    |
|                    | room on the map.   |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 4.2.1              | Some ‘constant’    | Check that         | There are no       |
|                    | clues should be    | consistent clues   | ‘constant’ clues.  |
|                    | available, for     | spawn in the       |                    |
|                    | example the guest  | correct place on   |                    |
|                    | sign in book in    | at least 2         |                    |
|                    | the central part   | separate           |                    |
|                    | of the map.        | occasions.         |                    |
+--------------------+--------------------+--------------------+--------------------+
| 4.2.2              | Some rooms should  | Check that at      | There is only one  |
|                    | have more than one | least one room has | clue per room      |
|                    | clue e..g note     | at least one clue  |                    |
|                    | left by            | in.                |                    |
|                    | victim/murder      |                    |                    |
|                    | weapon.            |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 4.3.1              | The player could   | Check the player   | All clues will be  |
|                    | be able to         | can interact with  | meaningful.        |
|                    | interact with or   | some item and it   |                    |
|                    | pick up some items | not be listed as a |                    |
|                    | which are not      | clue.              |                    |
|                    | clues.             |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
|                    | Score              |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 5.1.3              | The player must be | Play through the   | There will be no   |
|                    | scored on time     | game at least 3    | scoring system.    |
|                    | taken, number of   | times to check     |                    |
|                    | wrong accusations, | that scores add up |                    |
|                    | number of          | as expected.       |                    |
|                    | questions asked    |                    |                    |
|                    | and number of      |                    |                    |
|                    | clues found.       |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 5.3.2              | A list of          | Check the server   | There will be a    |
|                    | high-scores could  | contains the high  | local list of high |
|                    | be stored on a     | scores.            | scores or no list  |
|                    | server.            |                    | of high scores.    |
+--------------------+--------------------+--------------------+--------------------+
|                    | Other/System       |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 6.1.1              | The game will be   | Determine the game | N/A - necessary    |
|                    | controlled by      | is controlled as   | requirement.       |
|                    | \_\_\_XXXXXXXXX\_\ | described.         |                    |
|                    | _.                 |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 6.1.2              | The game must play | Determine the game | N/A - necessary    |
|                    | on a windows based | runs on the system | requirement.       |
|                    | system.            | described.         |                    |
+--------------------+--------------------+--------------------+--------------------+
| 6.1.3              | The game must be   | Check the game is  | The game will be   |
|                    | played in a ‘top   | viewed as          | played in an       |
|                    | down’ viewpoint,   | described.         | improved viewpoint |
|                    | where the player   |                    | based on the       |
|                    | is in the centre   |                    | reason for         |
|                    | of the screen and  |                    | discarding this    |
|                    | the world moves    |                    | one.               |
|                    | around the player. |                    |                    |
|                    | The viewpoint is   |                    |                    |
|                    | fixed zoom.        |                    |                    |
+--------------------+--------------------+--------------------+--------------------+
| 6.2.1              | The game should    | Use frame-rate     | N/A - necessary    |
|                    | run smoothly on    | measuring software | requirement.       |
|                    | university         | to obtain a        |                    |
|                    | computers.         | frame-rate of      |                    |
|                    |                    | at-least 30.       |                    |
+--------------------+--------------------+--------------------+--------------------+
| 6.3.1              | The game could     | Check that sound   | The game will not  |
|                    | have a soundtrack. | plays when game is | have a soundtrack. |
|                    |                    | running.           |                    |
+--------------------+--------------------+--------------------+--------------------+
| 6.3.2              | The game could     | When activated,    | The game textures  |
|                    | have a ‘colour     | the colourblind    | will be designed   |
|                    | blind’ setting.    | setting changes    | with colour        |
|                    |                    | all textures in    | blindness in mind. |
|                    |                    | the game to ones   |                    |
|                    |                    | that are easier    |                    |
|                    |                    | for a colour-blind |                    |
|                    |                    | person to see.     |                    |
+--------------------+--------------------+--------------------+--------------------+
| 6.3.3              | The game could be  | Make sure game     | The game will not  |
|                    | cross compatible   | runs on            | be cross           |
|                    | on mobile          | alternative        | compatible.        |
|                    | (android) and Mac. | systems.           |                    |
+--------------------+--------------------+--------------------+--------------------+