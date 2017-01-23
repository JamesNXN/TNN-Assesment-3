Risk Assessment and Mitigation
==================================

**Highlighted changes:**

- Each risk now has a responsible risk owner
- Improved description as to how we came up with the risks and improved formatting
- More details are in the updates report

Introduction
-------------
Risk management is an important part of any project, we must prepare for
what could happen during the course of the project in order to be able
to quickly recover and stay on track. The risks which are shown below
take into account the scale of the project and aim to cover only risks
which are realistic within this context.

To determine risks we brainstormed various scenarios - such as a
teammate being ill for more than a few weeks. From these scenarios, we
collected possible risks, and worked out the likelihood of them
occurring. To determine the risk we discussed how it would impact the
project, focussing on how many knock-on effects the issue would cause.

The risks are presented in a tabular format, with the following columns:

-  **Risk ID** - this allows for traceability across the project
-  **Description** - describes what the risk is for
-  **Likelihood** - each risk has an estimated likelihood on a scale

   -  **High** =good chance this risk will occur, about 75% chance
   -  **Medium** =equal chance of risk occurring or not, about 50% chance
   -  **Low** =not likely to occur, about 25% chance, however some risks may be lower

-  **Impact** - this describes the impact the risk would have to progress in
   the project
-  **Severity** - shows the severity of the impact on the project on a scale

   -  **High** =a major setback which could affect the whole project
   -  **Medium** =could add up to a week of extra work and may threaten a deadline
   -  **Low** =may mean a few extra hours of work, but nothing major

-  **Mitigation** - describes how how we will aim to avoid such a risk and
   deal with it
-  **Owner** - describes the owner of the problem, where the owner is the
   person most likely to be responsible for the issue.

The overall table is split into sections which group together similar
risk such as software risks. Each section is then ordered by severity,
highest first. Equal severity is ordered by likelihood.



This table will be regularly consulted in an attempt to monitor the
risks and try to ensure they do not occur and catch them early if they
are occurring.

Due to the size of the team we feel that these risks are appropriate and
accurate.

Table of risks
-----------------
Software risks
~~~~~~~~~~~~~~~~~~

+------------+------------+------------+------------+------------+------------+------------+
| ID         | Description| Likelihood | Impact     | Severity   | Mitigation | Owner      |
+------------+------------+------------+------------+------------+------------+------------+
| 1          | Our game   | Medium     | No one     | High       | Improve    | Coding     |
|            | may be     |            | will want  |            | efficiency | Team       |
|            | slow or    |            | to play    |            | of the     |            |
|            | unresponsi |            | the game.  |            | game       |            |
|            | ve.        |            |            |            | wherever   |            |
|            |            |            |            |            | possible   |            |
|            |            |            |            |            | and        |            |
|            |            |            |            |            | regularly  |            |
|            |            |            |            |            | check      |            |
|            |            |            |            |            | performance|            |
+------------+------------+------------+------------+------------+------------+------------+
| 2          | Software   | Medium     | We would   | Medium     | Test the   | Software   |
|            | library    |            | struggle   |            | elements   | Library    |
|            | doesn’t    |            | to         |            | of the     | Owner      |
|            | work or    |            | implement  |            | library    |            |
|            | lacks a    |            | the        |            | you plan   |            |
|            | feature    |            | feature we |            | to use     |            |
|            | e.g. has a |            | want to    |            | beforehand |            |
|            | bug that   |            | add. We    |            | .          |            |
|            | stops the  |            | would also |            | Also, make |            |
|            | game from  |            | use up     |            | sure the   |            |
|            | working,   |            | lots of    |            | library    |            |
|            | or is      |            | time       |            | has an     |            |
|            | missing a  |            | trying to  |            | active     |            |
|            | feature    |            | solve the  |            | community  |            |
|            | required   |            | issue.     |            | surrounding|            |
|            | for the    |            |            |            | it and     |            |
|            | game to    |            |            |            | that bugs  |            |
|            | work.      |            |            |            | are fixed  |            |
|            |            |            |            |            | quickly.   |            |
|            |            |            |            |            | If it was  |            |
|            |            |            |            |            | early      |            |
|            |            |            |            |            | stages we  |            |
|            |            |            |            |            | could get  |            |
|            |            |            |            |            | a new      |            |
|            |            |            |            |            | library    |            |
|            |            |            |            |            | but this   |            |
|            |            |            |            |            | would      |            |
|            |            |            |            |            | require us |            |
|            |            |            |            |            | to rewrite |            |
|            |            |            |            |            | our code   |            |
|            |            |            |            |            | to work    |            |
|            |            |            |            |            | with the   |            |
|            |            |            |            |            | new        |            |
|            |            |            |            |            | library.   |            |
+------------+------------+------------+------------+------------+------------+------------+
| 3          | Code is    | Low        | Could      | Medium     | Use        | Coding     |
|            | hard to    |            | cause bugs |            | meaningful | Team       |
|            | understand |            | and makes  |            | variable   |            |
|            | and seems  |            | bug fixing |            | names and  |            |
|            | too        |            | harder.    |            | plenty of  |            |
|            | complex.   |            | Slows down |            | comments,  |            |
|            |            |            | the        |            | both       |            |
|            |            |            | productivi |            | in code    |            |
|            |            |            | ty         |            | and in     |            |
|            |            |            | of the     |            | commit     |            |
|            |            |            | group.     |            | messages.  |            |
|            |            |            |            |            | Make sure  |            |
|            |            |            |            |            | code is    |            |
|            |            |            |            |            | reviewed   |            |
|            |            |            |            |            | by the     |            |
|            |            |            |            |            | majority   |            |
|            |            |            |            |            | of members |            |
|            |            |            |            |            | before it  |            |
|            |            |            |            |            | gets       |            |
|            |            |            |            |            | merged     |            |
|            |            |            |            |            | into the   |            |
|            |            |            |            |            | repository |            |
|            |            |            |            |            | .          |            |
+------------+------------+------------+------------+------------+------------+------------+
| 4          | Conflicts  |  High      | May need   | Low        | Make sure  | Coding     |
|            | in git.    |            | to move    |            | people     | Team       |
|            | Different  |            | code       |            | work on    |            |
|            | members    |            | around and |            | separate   |            |
|            | changing   |            | even       |            | elements   |            |
|            | the same   |            | rewrite.   |            | by         |            |
|            | code.      |            |            |            | assigning  |            |
|            |            |            |            |            | them to    |            |
|            |            |            |            |            | different  |            |
|            |            |            |            |            | tasks and  |            |
|            |            |            |            |            | if not     |            |
|            |            |            |            |            | then make  |            |
|            |            |            |            |            | use of     |            |
|            |            |            |            |            | Gits       |            |
|            |            |            |            |            | tools.     |            |
+------------+------------+------------+------------+------------+------------+------------+
| 5          | Our own    | High       | Will need  | Low        | This is a  | Coding     |
|            | software   |            | to bug     |            | normal     | Team/Desig |
|            | doesn’t    |            | fix. Loss  |            | part of    | n          |
|            | work as    |            | of time    |            | software   | Team/Proje |
|            | intended.  |            | and        |            | developmen | ct         |
|            |            |            | potentiall |            | t.         | Manager    |
|            |            |            | y          |            | We all     |            |
|            |            |            | productivi |            | make       |            |
|            |            |            | ty         |            | mistakes.  |            |
|            |            |            | if that    |            | However,   |            |
|            |            |            | function   |            | before     |            |
|            |            |            | or feature |            | code is    |            |
|            |            |            | is the     |            | approved   |            |
|            |            |            | bottleneck |            | by the     |            |
|            |            |            | of the     |            | group we   |            |
|            |            |            | game.      |            | will use   |            |
|            |            |            |            |            | unit       |            |
|            |            |            |            |            | testing    |            |
|            |            |            |            |            | that will  |            |
|            |            |            |            |            | test key   |            |
|            |            |            |            |            | functions  |            |
|            |            |            |            |            | of the     |            |
|            |            |            |            |            | game as we |            |
|            |            |            |            |            | develop    |            |
|            |            |            |            |            | them       |            |
|            |            |            |            |            | meaning    |            |
|            |            |            |            |            | that       |            |
|            |            |            |            |            | should a   |            |
|            |            |            |            |            | function   |            |
|            |            |            |            |            | break we   |            |
|            |            |            |            |            | will know  |            |
|            |            |            |            |            | about it   |            |
|            |            |            |            |            | before     |            |
|            |            |            |            |            | it’s       |            |
|            |            |            |            |            | merged.    |            |
+------------+------------+------------+------------+------------+------------+------------+

Hardware risks
~~~~~~~~~~~~~~~~~~
+------------+------------+------------+------------+------------+------------+------------+
| ID         | Description| Likelihood | Impact     | Severity   | Mitigation | Owner      |
+------------+------------+------------+------------+------------+------------+------------+
| 6          | Personal   | Low        | Could lose | Low        | Ensure     | Final User |
|            | computer   |            | work and   |            | work is    |            |
|            | breaks     |            | be unable  |            | saved      |            |
|            | long term  |            | to work.   |            | online to  |            |
|            | or is      |            |            |            | google     |            |
|            | lost.      |            |            |            | drive      |            |
|            |            |            |            |            |  cloud     |            |
|            |            |            |            |            | service    |            |
|            |            |            |            |            | and that   |            |
|            |            |            |            |            | code is    |            |
|            |            |            |            |            | stored on  |            |
|            |            |            |            |            | github.    |            |
|            |            |            |            |            | Department |            |
|            |            |            |            |            | PC’s       |            |
|            |            |            |            |            | should be  |            |
|            |            |            |            |            | accessible |            |
|            |            |            |            |            | most days  |            |
|            |            |            |            |            | and have   |            |
|            |            |            |            |            | all the    |            |
|            |            |            |            |            | tools we   |            |
|            |            |            |            |            | need.      |            |
+------------+------------+------------+------------+------------+------------+------------+
| 7          | Personal   | Medium     | Potentiall | Low        | Save       | Final User |
|            | computer   |            | y          |            | regularly, |            |
|            | crashes    |            | will have  |            | google     |            |
|            | while      |            | lose work, |            | docs[2]    |            |
|            | working.   |            | meaning    |            | will do    |            |
|            |            |            | you lose   |            | this for   |            |
|            |            |            | time doing |            | us.        |            |
|            |            |            | it again.  |            | Regularly  |            |
|            |            |            |            |            | commit     |            |
|            |            |            |            |            | code to    |            |
|            |            |            |            |            | personal   |            |
|            |            |            |            |            | branches   |            |
|            |            |            |            |            | so that it |            |
|            |            |            |            |            | stored     |            |
|            |            |            |            |            | elsewhere  |            |
|            |            |            |            |            | other than |            |
|            |            |            |            |            | your PC .  |            |
+------------+------------+------------+------------+------------+------------+------------+

Risks with people
~~~~~~~~~~~~~~~~~~
+------------+------------+------------+------------+------------+------------+------------+
| ID         | Description| Likelihood | Impact     | Severity   | Mitigation | Owner      |
+------------+------------+------------+------------+------------+------------+------------+
| 8          | A team     | Low        | They may   | High       | As above   | Project    |
|            | member     |            | have only  |            | store      | Team       |
|            | leaves the |            | access to  |            | online but |            |
|            | module or  |            | their      |            | also try   |            |
|            | even the   |            | work, also |            | to keep    |            |
|            | course.    |            | the rest   |            | each other |            |
|            |            |            | of the     |            | motivated  |            |
|            |            |            | team will  |            | to avoid   |            |
|            |            |            | have more  |            | this.      |            |
|            |            |            | to do.     |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 9          | A team     | High       | They might | Medium     | Hard to    | Project    |
|            | member is  |            | have been  |            | avoid, but | Team       |
|            | ill/away   |            | skilled in |            | we should  |            |
|            | for a week |            | a certain  |            | store work |            |
|            | or two.    |            | area that  |            | online     |            |
|            |            |            | no other   |            | where      |            |
|            |            |            | member can |            | everyone   |            |
|            |            |            | do well.If |            | can        |            |
|            |            |            | they have  |            | access.    |            |
|            |            |            | the only   |            |            |            |
|            |            |            | access to  |            | If we work |            |
|            |            |            | work may   |            | in pairs   |            |
|            |            |            | get behind |            | to         |            |
|            |            |            | from it.   |            | complete   |            |
|            |            |            |            |            | tasks then |            |
|            |            |            |            |            | there will |            |
|            |            |            |            |            | be less of |            |
|            |            |            |            |            | a chance   |            |
|            |            |            |            |            | of having  |            |
|            |            |            |            |            | one person |            |
|            |            |            |            |            | who knows  |            |
|            |            |            |            |            | the most   |            |
|            |            |            |            |            | about one  |            |
|            |            |            |            |            | area.      |            |
+------------+------------+------------+------------+------------+------------+------------+
| 10         | Arguments  | Medium     | Disrupts   | Medium     | Try to     | Project    |
|            | within the |            | the work   |            | avoid      | Manager    |
|            | team.      |            | of the     |            | conflict   |            |
|            |            |            | team and   |            | but if     |            |
|            |            |            | prevents   |            | necessary  |            |
|            |            |            | us moving  |            | have       |            |
|            |            |            | forwards.  |            | proper     |            |
|            |            |            | Also,      |            | debates    |            |
|            |            |            | unpleasant |            | perhaps    |            |
|            |            |            | for the    |            | using a    |            |
|            |            |            | team as a  |            | mediator,  |            |
|            |            |            | whole.     |            | do not     |            |
|            |            |            |            |            | keep       |            |
|            |            |            |            |            | issues     |            |
|            |            |            |            |            | hidden.    |            |
+------------+------------+------------+------------+------------+------------+------------+
| 11         | Lack of    | Medium     | Tasks may  | Medium     | Keep       | Project    |
|            | communicat |            | be done    |            | strong     | Manager    |
|            | ion.       |            | twice or   |            | communicat |            |
|            |            |            | not done   |            | ion        |            |
|            |            |            | at all.    |            | using the  |            |
|            |            |            |            |            | tools we   |            |
|            |            |            |            |            | plan to    |            |
|            |            |            |            |            | use.       |            |
+------------+------------+------------+------------+------------+------------+------------+
| 12         | A team     | Medium     | Could      | Low        | Don’t give | Project    |
|            | member     |            | disrupt    |            | members    | Team/Manag |
|            | does not   |            | other      |            | too much   | er         |
|            | do their   |            | members    |            | work or    |            |
|            | work.      |            | work and   |            | work they  |            |
|            |            |            | could make |            | cannot do, |            |
|            |            |            | the other  |            | ensure     |            |
|            |            |            | team       |            | that the   |            |
|            |            |            | members    |            | team       |            |
|            |            |            | annoyed.   |            | communicat |            |
|            |            |            |            |            | es         |            |
|            |            |            |            |            | well and   |            |
|            |            |            |            |            | regularly  |            |
|            |            |            |            |            | meets up   |            |
|            |            |            |            |            | to discuss |            |
|            |            |            |            |            | how the    |            |
|            |            |            |            |            | work is    |            |
|            |            |            |            |            | going.     |            |
+------------+------------+------------+------------+------------+------------+------------+

Risks with tools
~~~~~~~~~~~~~~~~~~
+------------+------------+------------+------------+------------+------------+------------+
| ID         | Description| Likelihood | Impact     | Severity   | Mitigation | Owner      |
+------------+------------+------------+------------+------------+------------+------------+
| 13         | Google     | Low        | Could      | Medium     | Store work | Google     |
|            | drive      |            | lose/lose  |            | locally ,  |            |
|            | servers    |            | access to  |            | and on     |            |
|            | stop       |            | work that  |            | other      |            |
|            | working.   |            | is stored  |            | services.  |            |
|            |            |            | there.     |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 14         | Central    | Low        | Temporaril | Low        | Keep up to | Git/Coding |
|            | git        |            | y          |            | date local | Team       |
|            | repository |            | lose       |            | copies so  |            |
|            | [1]        |            | access to  |            | can be     |            |
|            | is lost in |            | it.        |            | easily     |            |
|            | some way.  |            |            |            | restored.  |            |
|            |            |            |            |            | We could   |            |
|            |            |            |            |            | host our   |            |
|            |            |            |            |            | own local  |            |
|            |            |            |            |            | copy       |            |
|            |            |            |            |            | should     |            |
|            |            |            |            |            | github go  |            |
|            |            |            |            |            | down.      |            |
+------------+------------+------------+------------+------------+------------+------------+
| 15         | Website    | Low        | Users lose | Medium     | The        | Website    |
|            | hosting    |            | access to  |            | website    | Hosting    |
|            | fails.     |            | the        |            | files are  | Owner      |
|            |            |            | website.   |            | stored on  |            |
|            |            |            |            |            | github and |            |
|            |            |            |            |            | every team |            |
|            |            |            |            |            | member has |            |
|            |            |            |            |            | a local    |            |
|            |            |            |            |            | copy of    |            |
|            |            |            |            |            | the        |            |
|            |            |            |            |            | repository |            |
|            |            |            |            |            | on their   |            |
|            |            |            |            |            | computer   |            |
|            |            |            |            |            | so we      |            |
|            |            |            |            |            | could      |            |
|            |            |            |            |            | bring the  |            |
|            |            |            |            |            | site back  |            |
|            |            |            |            |            | up on a    |            |
|            |            |            |            |            | different  |            |
|            |            |            |            |            | server.    |            |
|            |            |            |            |            | The site   |            |
|            |            |            |            |            | is also    |            |
|            |            |            |            |            | protected  |            |
|            |            |            |            |            | by         |            |
|            |            |            |            |            | cloud-flar |            |
|            |            |            |            |            | e[3]       |            |
|            |            |            |            |            | who will   |            |
|            |            |            |            |            | provide a  |            |
|            |            |            |            |            | cached     |            |
|            |            |            |            |            | version of |            |
|            |            |            |            |            | the site   |            |
|            |            |            |            |            | if our     |            |
|            |            |            |            |            | host were  |            |
|            |            |            |            |            | to go      |            |
|            |            |            |            |            | down.      |            |
+------------+------------+------------+------------+------------+------------+------------+

Requirements risks
~~~~~~~~~~~~~~~~~~~~~~~~
+------------+------------+------------+------------+------------+------------+------------+
| ID         | Description| Likelihood | Impact     | Severity   | Mitigation | Owner      |
+------------+------------+------------+------------+------------+------------+------------+
| 16         | Not        | Low        | We let the | High       | Make sure  | Requiremen |
|            | including  |            | customer   |            | key        | ts         |
|            | a          |            | down and   |            | requiremen | Team       |
|            | requiremen |            | have       |            | ts         |            |
|            | t          |            | failed     |            | are        |            |
|            | which is   |            | them.      |            | elicited   |            |
|            | required   |            |            |            | from the   |            |
|            | by the     |            |            |            | customer   |            |
|            | customer.  |            |            |            | so they    |            |
|            |            |            |            |            | get what   |            |
|            |            |            |            |            | they want. |            |
+------------+------------+------------+------------+------------+------------+------------+
| 17         | A          | High       | May need   | Medium     | Our        | Requiremen |
|            | requiremen |            | to rewrite |            | software   | ts         |
|            | t          |            | code or    |            | architectu | Team       |
|            | could      |            | add extra  |            | re         |            |
|            | change/ be |            | code to    |            | must be    |            |
|            | added.     |            | account    |            | flexible   |            |
|            |            |            | for it.    |            | and able   |            |
|            |            |            | Extra time |            | to be      |            |
|            |            |            | will be    |            | changed    |            |
|            |            |            | needed.    |            | easily.    |            |
+------------+------------+------------+------------+------------+------------+------------+
| 18         | Stating a  | High       | Let down   | Medium     | Be         | Requiremen |
|            | requiremen |            | the        |            | sensible   | ts         |
|            | t          |            | customer   |            | when       | Team/Codin |
|            | that we    |            | and also   |            | deciding   | g          |
|            | cannot     |            | waste      |            | requiremen | Team       |
|            | actually   |            | time.      |            | ts,        |            |
|            | achieve.   |            |            |            | be sure    |            |
|            |            |            |            |            | you can    |            |
|            |            |            |            |            | achieve    |            |
|            |            |            |            |            | them.      |            |
+------------+------------+------------+------------+------------+------------+------------+
| 19         | Ambiguity  | Medium     | May end up | Medium     | Ensure     | Requiremen |
|            | in         |            | making     |            | requiremen | ts         |
|            | requireme  |            | something  |            | ts         | Team       |
|            | nts.       |            | which is   |            | are clear  |            |
|            |            |            | not what   |            | and check  |            |
|            |            |            | was        |            | any        |            |
|            |            |            | originally |            | ambiguitie |            |
|            |            |            | intended.  |            | s          |            |
|            |            |            |            |            | with the   |            |
|            |            |            |            |            | customer.  |            |
+------------+------------+------------+------------+------------+------------+------------+
| 20         | Choosing   | Medium     | Waste time | Low        | Ensure you | Requiremen |
|            | requiremen |            | which      |            | know which | ts         |
|            | ts         |            | could be   |            | requiremen | Team       |
|            | that the   |            | spent on   |            | ts         |            |
|            | customer   |            | requiremen |            | the        |            |
|            | doesn't    |            | ts         |            | customer   |            |
|            | really     |            | they did   |            | really     |            |
|            | want.      |            | want.      |            | wants and  |            |
|            |            |            |            |            | which can  |            |
|            |            |            |            |            | be         |            |
|            |            |            |            |            | ignored.   |            |
+------------+------------+------------+------------+------------+------------+------------+

Estimation risks
~~~~~~~~~~~~~~~~~~
+------------+------------+------------+------------+------------+------------+------------+
| ID         | Description| Likelihood | Impact     | Severity   | Mitigation | Owner      |
+------------+------------+------------+------------+------------+------------+------------+
| 21         | Expect the | Medium     | Work is    | Medium     | Give tasks | Project    |
|            | team or a  |            | not done   |            | that       | Manager    |
|            | team       |            | or is done |            | people can |            |
|            | member can |            | to an      |            | do and if  |            |
|            | do more    |            | insufficie |            | they can’t |            |
|            | than they  |            | nt         |            | then help  |            |
|            | actually   |            | standard.  |            | them. When |            |
|            | can.       |            |            |            | working on |            |
|            |            |            |            |            | difficult  |            |
|            |            |            |            |            | tasks work |            |
|            |            |            |            |            | in pairs   |            |
|            |            |            |            |            | to         |            |
|            |            |            |            |            | complete   |            |
|            |            |            |            |            | the task   |            |
|            |            |            |            |            | meaning    |            |
|            |            |            |            |            | individual |            |
|            |            |            |            |            | team       |            |
|            |            |            |            |            | members    |            |
|            |            |            |            |            | don’t feel |            |
|            |            |            |            |            | as         |            |
|            |            |            |            |            | overwhelme |            |
|            |            |            |            |            | d          |            |
|            |            |            |            |            | by the     |            |
|            |            |            |            |            | task       |            |
+------------+------------+------------+------------+------------+------------+------------+
| 22         | We may     | Medium     | Work ends  | Medium     | Set        | Project    |
|            | underestim |            | up taking  |            | realistic  | Manager    |
|            | ate        |            | longer     |            | timings to |            |
|            | how long   |            | than       |            | do work    |            |
|            | it will    |            | expected   |            | and be     |            |
|            | take to do |            | or not     |            | realistic  |            |
|            | some work. |            | done to    |            | on how     |            |
|            |            |            | the        |            | long a     |            |
|            |            |            | standard   |            | task will  |            |
|            |            |            | it could   |            | take.      |            |
|            |            |            | be done.   |            | Account    |            |
|            |            |            | This could |            | for        |            |
|            |            |            | cause      |            | unforeseen |            |
|            |            |            | other      |            | delays in  |            |
|            |            |            | areas of   |            | our plan   |            |
|            |            |            | the        |            | adding     |            |
|            |            |            | project to |            | time where |            |
|            |            |            | suffer     |            | we can     |            |
|            |            |            |            |            | catch up.  |            |
+------------+------------+------------+------------+------------+------------+------------+
| 23         | Be too     | Medium     | We end up  | Low        | Push our   | Project    |
|            | pessimisti |            | with a     |            | limits but | Manager    |
|            | c          |            | product    |            | also stay  |            |
|            | about what |            | which is   |            | realistic  |            |
|            | we can     |            | not as     |            | and within |            |
|            | achieve.   |            | good as it |            | the        |            |
|            |            |            | could have |            | requiremen |            |
|            |            |            | possibly   |            | ts.        |            |
|            |            |            | been.      |            | If we have |            |
|            |            |            |            |            | extra time |            |
|            |            |            |            |            | then we    |            |
|            |            |            |            |            | can use it |            |
|            |            |            |            |            | to enhance |            |
|            |            |            |            |            | the        |            |
|            |            |            |            |            | product.   |            |
+------------+------------+------------+------------+------------+------------+------------+
| 24         | Distribute | Low        | Team       | Low        | Distribute | Project    |
|            | tasks      |            | over/under |            | tasks      | Manager    |
|            | incorrectl |            | worked.    |            | appropriat |            |
|            | y.         |            |            |            | ely        |            |
|            |            |            |            |            | and tell   |            |
|            |            |            |            |            | others if  |            |
|            |            |            |            |            | feel       |            |
|            |            |            |            |            | over/under |            |
|            |            |            |            |            | worked.    |            |
+------------+------------+------------+------------+------------+------------+------------+

Bibliography
--------------

[1] GitHub [online] Available https://github.com [Accessed 01/11/2016]

[2] Google Drive [online] Available https://www.google.com/drive/ [Accessed 01/11/2016]

[3] Cloud Flare [online] Available https://www.cloudflare.com/ [Accessed 01/11/2016]
