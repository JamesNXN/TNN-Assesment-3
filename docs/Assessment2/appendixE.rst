E: Unit Tests
========================

Below is a table of the unit tests included within this project.

The unit tests are associated with an appropriate requirement to allow
for traceability, and the tests aim to check that the code works for any
associated requirements. Not all requirements have associated tests, and
vice versa - this is because some requirements cannot be explicitly unit
tested, and some tests do not link up directly to a requirement, but are
still needed to ensure the code functions as intended.

There is a criticality measure against each test, for both acceptance
and unit tests - this is to represent how important the test is to the
overall function of the code. Criticality is on a scale - high
criticality means that if that test fails, the project will not function
at all; low criticality means that if the test fails, the project will
still mostly function as intended.

+------------+------------+------------+------------+------------+------------+------------+
| ID         | Test Name  | Purpose    | Criticalit | Class      | Req ID     | Result     |
|            |            |            | y          |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.01       | testDescri | Verifies   | Medium     | Clue       | 5.1.2      | passed     |
|            | ption      | clue       |            |            |            |            |
|            |            | descriptio |            |            |            |            |
|            |            | n          |            |            |            |            |
|            |            | is         |            |            |            |            |
|            |            | returned   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.02       | testEquali | Verifies   | Medium     | Clue       | 5.2.1      | passed     |
|            | ty         | clues can  |            |            |            |            |
|            |            | be         |            |            |            |            |
|            |            | compared   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
|            |            | for        |            |            |            |            |
|            |            | equality   |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.03       | testName   | Verifies   | High       | Clue       | 5.1.2      | passed     |
|            |            | clue name  |            |            |            |            |
|            |            | is         |            |            |            |            |
|            |            | returned   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.04       | testTileCo | Verifies   | High       | Clue       | 5.1.1      | passed     |
|            | ordinates  | clue has   |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | correct    |            |            |            |            |
|            |            | coordinate |            |            |            |            |
|            |            | for the    |            |            |            |            |
|            |            | map        |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.05       | testPerson | Verifies   | Medium     | NPC        | 3.1.5      | passed     |
|            | ality      | the NPC    |            |            |            |            |
|            |            | personalit |            |            |            |            |
|            |            | y          |            |            |            |            |
|            |            | is         |            |            |            |            |
|            |            | returned   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.06       | testGetName| Verifies   | Low        | NPC        | 3.1.2      | passed     |
|            |            | NPC name   |            |            |            |            |
|            |            | is         |            |            |            |            |
|            |            | returned   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.07       | doesPlayer | Verifies   | High       | Player     | 2.1.4      | passed     |
|            | Move       | the player |            |            |            |            |
|            |            | move       |            |            |            |            |
|            |            | function   |            |            |            |            |
|            |            | works      |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.08       | testPlayer | Verifies   | Medium     | Player     | 2.1.1      | passed     |
|            | Personality| the player |            |            |            |            |
|            |            | personality|            |            |            |            |
|            |            | is set and |            |            |            |            |
|            |            | get        |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.09       | testPlayer | Verifies   | Low        | Player     | _          | passed     |
|            | Name       | the player |            |            |            |            |
|            |            | name is    |            |            |            |            |
|            |            | returned   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.10       | testAddTra | Verifies   | High       | Room       | 4.1.1      | passed     |
|            | nsition    | transitions|            |            |            |            |
|            |            | between    |            |            |            |            |
|            |            | rooms can  |            |            |            |            |
|            |            | be added   |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.11       | testGetTra | Verifies   | High       | Room       | 4.1.1      | passed     |
|            | nsition    | transitions|            |            |            |            |
|            |            | between    |            |            |            |            |
|            |            | rooms can  |            |            |            |            |
|            |            | be         |            |            |            |            |
|            |            | performed  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.12       | testMatRot | Verifies   | Medium     | Room       | _          | passed     |
|            | ation      | direction  |            |            |            |            |
|            |            | of room    |            |            |            |            |
|            |            | transition |            |            |            |            |
|            |            | is         |            |            |            |            |
|            |            | correctly  |            |            |            |            |
|            |            | returned   |            |            |            |            |
|            |            |            |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.13       | testTrigge | Verifies   | High       | Room       | _          | passed     |
|            | r          | function   |            |            |            |            |
|            |            | to check   |            |            |            |            |
|            |            | if a tile  |            |            |            |            |
|            |            | is a       |            |            |            |            |
|            |            | trigger to |            |            |            |            |
|            |            | perform an |            |            |            |            |
|            |            | action     |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.14       | testWalkab | Verifies   | Low        | Room       | _          | passed     |
|            | le         | that a     |            |            |            |            |
|            |            | tile       |            |            |            |            |
|            |            | returns    |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | correct    |            |            |            |            |
|            |            | property   |            |            |            |            |
|            |            | for        |            |            |            |            |
|            |            | walkabilit |            |            |            |            |
|            |            | y          |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
