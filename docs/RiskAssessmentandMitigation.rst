Risk Assessment and Mitigation
==============================
Introduction
------------
Risk management is an important part of any project, we must prepare for
what could happen during the course of the project in order to be able
to quickly recover and stay on track. The risks which are shown below
are relevant to the particular SEPR context and take into account the
scale of the project and aim to cover only risks which are realistic
within this context.  The risks are here presented in a tabular format.
This table is split into 6 columns, the first column gives the risk an
ID number for easy reference if a risk does happen and we need to
resolve it. The second column describes the risk itself. The third
column gives an estimated likelihood of the risk occurring, this is done
using the following :

High :=High likelihood, there is a good chance that
this risk will    occur . Roughly a 75% chance.

Medium :=Medium likelihood, there is an equal chance of the
risk occurring or not occurring. Roughly a 50% chance.

Low :=Low likelihood, this risk is not likely to
occur. Roughly a 25% chance although some extreme risks could be a lot
lower.


The fourth column describes the impact the risk would have on progress in the
project. The fifth column shows the severity of the impact using this:

Low :=Low severity, may mean a few hours extra work
but nothing major.

Medium :=Medium severity, could add up to a week of
extra work and may threaten a deadline.

High :=High severity , a major set back which could
affect the whole project.

The sixth and final column details how we will aim
to avoid such a risk and deal with it. The overall table is split into
sections which group together similar risk such as software risks . Each
section is then ordered by severity, highest first. Equal severity is
ordered by likelihood. This table will be regularly consulted in an
attempt to monitor the risks and try to ensure they do not occur and
catch them early if they are occurring.

Table of risks
-------------
Software risks
~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| Risk ID      | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 1            | Software     | Medium       | Will need to | Medium       | Test the     |
|              | library      |              | rewrite      |              | elements of  |
|              | doesn�t      |              | code.        |              | the library  |
|              | work.        |              |              |              | you plan to  |
|              |              |              |              |              | use          |
|              |              |              |              |              | beforehand.  |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 2            | Code is      | Low          | Could cause  | Medium       |   Use        |
|              | hard to      |              | bugs and     |              |   meaningful |
|              | understand   |              | makes bug    |              |   variable   |
|              | by           |              | fixing       |              |   names and  |
|              | others.      |              | harder.      |              |   plenty of  |
|              |              |              |              |              |   comments,  |
|              |              |              |              |              |   both       |
|              |              |              |              |              |   in code    |
|              |              |              |              |              |   and in     |
|              |              |              |              |              |   commit     |
|              |              |              |              |              |   messages.  |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 3            | Conflicts in |  High        | May need to  | Low          | Make sure    |
|              | git.         |              | move code    |              | people work  |
|              | Different    |              | around and   |              | on separate  |
|              | members      |              | even         |              | elements and |
|              | changing the |              | rewrite.     |              | if not then  |
|              | same code.   |              |              |              | make use of  |
|              |              |              |              |              | Gits tools . |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 4            | Our own      | High         | Will need to | Low          | This is a    |
|              | software     |              | bug fix.     |              | normal part  |
|              | doesn't work |              |              |              | of software  |
|              | as intended. |              |              |              | development. |
|              |              |              |              |              | We all make  |
|              |              |              |              |              | mistakes.    |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 5            |   Software   | Medium       |   May need   | Low          |   Check      |
|              |   library    |              |   to rewrite |              |   support    |
|              |   has no     |              |   code if    |              |   forums and |
|              |   support.   |              |   cannot     |              |   check they |
|              |              |              |   figure it  |              |   have been  |
|              |              |              |   out.       |              |   actively   |
|              |              |              |              |              |   used       |
|              |              |              |              |              |   recently.  |
+--------------+--------------+--------------+--------------+--------------+--------------+

Hardware risks
~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| Risk ID      | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 6            |   Personal   | Low          | Could lose   | Medium       |   Ensure     |
|              |   computer   |              | work and be  |              |   work is    |
|              |   breaks     |              | unable to    |              |   saved      |
|              |   long       |              | work.        |              |   online to  |
|              |   term or    |              |              |              |   google     |
|              |   is lost.   |              |              |              |   drive      |
|              |              |              |              |              |   cloud      |
|              |              |              |              |              |   service.   |
|              |              |              |              |              |   Also can   |
|              |              |              |              |              |   use        |
|              |              |              |              |              |   department |
|              |              |              |              |              |   PCs.       |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 7            | Personal     | Medium       | Lose work,   | Low          | Save         |
|              | computer     |              | meaning you  |              | regularly ,  |
|              | crashes      |              | lose time    |              | google docs  |
|              | while        |              | doing it     |              | will do this |
|              | working.     |              | again.       |              | for us.      |
+--------------+--------------+--------------+--------------+--------------+--------------+

Risks with people
~~~~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| Risk ID      | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 8            | A team       | Low          | They may     | High         | As above     |
|              | member       |              | have only    |              | store online |
|              | leaves the   |              | access to    |              | but also try |
|              | module or    |              | their work,  |              | to keep each |
|              | even the     |              | also the     |              | other        |
|              | course.      |              | rest of the  |              | motivated to |
|              |              |              | team will    |              | avoid this.  |
|              |              |              | have more to |              |              |
|              |              |              | do.          |              |              |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 9            | A team       | High         | If they have | Medium       | Hard to      |
|              | member is    |              | the only     |              | avoid , not  |
|              | ill/away for |              | access to    |              | normally     |
|              | a week or    |              | work may get |              | intentional  |
|              | two.         |              | behind from  |              | but we       |
|              |              |              | it.          |              | should store |
|              |              |              |              |              | work online  |
|              |              |              |              |              | where        |
|              |              |              |              |              | everyone can |
|              |              |              |              |              | access and   |
|              |              |              |              |              | work on it.  |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 10           | Arguments    | Medium       | Disrupts the | Medium       | Try to avoid |
|              | within the   |              | work of the  |              | conflict but |
|              | team.        |              | team and     |              | if necessary |
|              |              |              | prevents us  |              | have proper  |
|              |              |              | moving       |              | debates      |
|              |              |              | forwards.    |              | perhaps      |
|              |              |              | Also         |              | using a      |
|              |              |              | unpleasant   |              | mediator, do |
|              |              |              | for the team |              | not keep     |
|              |              |              | as a whole.  |              | issues       |
|              |              |              |              |              | hidden.      |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 11           | Lack of      | Medium       | Tasks may be | Medium       | Keep strong  |
|              | communicatio |              | done twice   |              | communicatio |
|              | n.           |              | or not done  |              | n            |
|              |              |              | at all.      |              | using the    |
|              |              |              |              |              | tools we     |
|              |              |              |              |              | plan to use. |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 12           | A team       | Medium       | Could        | Low          | Don't give   |
|              | member does  |              | disrupt      |              | members too  |
|              | not do their |              | other        |              | much work or |
|              | work.        |              | members work |              | work they    |
|              |              |              | and could    |              | cannot do.   |
|              |              |              | make the     |              |              |
|              |              |              | other team   |              |              |
|              |              |              | members      |              |              |
|              |              |              | annoyed.     |              |              |
+--------------+--------------+--------------+--------------+--------------+--------------+

Risks with tools
~~~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| Risk ID      | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 13           | Google drive | Low          | Could        | Medium       | Store work   |
|              | servers stop |              | lose/lose    |              | locally ,    |
|              | working.     |              | access to    |              | and on other |
|              |              |              | work that is |              | services.    |
|              |              |              | stored       |              |              |
|              |              |              | there.       |              |              |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 14           | Central git  | Low          | Lose access  | Medium       | Keep local   |
|              | repository   |              | to old       |              | copies of    |
|              | is lost in   |              | versions of  |              | old          |
|              | some way.    |              | the code.    |              | versions,    |
|              |              |              |              |              | git having   |
|              |              |              |              |              | local        |
|              |              |              |              |              | repositories |
|              |              |              |              |              | also helps.  |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 15           | Website      | Low          | Lose access  | Medium       | Could save   |
|              | hosting      |              | to the       |              | an offline   |
|              | fails.       |              | website.     |              | version of   |
|              |              |              |              |              | the website  |
|              |              |              |              |              | coding.      |
+--------------+--------------+--------------+--------------+--------------+--------------+

Requirements risks
~~~~~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| Risk ID      | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 16           | Not stating  | Low          | We let the   | High         | Make sure    |
|              | a            |              | customer     |              | key          |
|              | requirement  |              | down and     |              | requirements |
|              | which is     |              | have failed  |              | are elicited |
|              | required by  |              | them.        |              | from the     |
|              | the          |              |              |              | customer so  |
|              | customer.    |              |              |              | they get     |
|              |              |              |              |              | what they    |
|              |              |              |              |              | want.        |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 17           | A            | High         | May need to  | Medium       | Our coding   |
|              | requirement  |              | rewrite code |              | solution     |
|              | could        |              | or add extra |              | must be      |
|              | change/ be   |              | code to      |              | flexible and |
|              | added.       |              | account for  |              | able to be   |
|              |              |              | it.          |              | changed      |
|              |              |              |              |              | easily.      |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 18           | Stating a    | High         | Let down the | Medium       | Be sensible  |
|              | requirement  |              | customer and |              | when         |
|              | that we      |              | also waste   |              | deciding     |
|              | cannot       |              | time.        |              | requirements |
|              | actually     |              |              |              | ,            |
|              | achieve.     |              |              |              | be sure you  |
|              |              |              |              |              | can achieve  |
|              |              |              |              |              | them.        |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 19           | Ambiguity in | Medium       | May end up   | Medium       | Ensure       |
|              | requirement  |              | making       |              | requirements |
|              | s.           |              | something    |              | are clear    |
|              |              |              | which is not |              | and check    |
|              |              |              | what was     |              | any          |
|              |              |              | originally   |              | ambiguities  |
|              |              |              | intended.    |              | with the     |
|              |              |              |              |              | customer.    |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 20           | Choosing     | Medium       | Waste time   | Low          | Ensure you   |
|              | requirements |              | which could  |              | know which   |
|              | that the     |              | be spent on  |              | requirements |
|              | customer     |              | requirements |              | the customer |
|              | doesn't      |              | they did     |              | really wants |
|              | really want. |              | want.        |              | and which    |
|              |              |              |              |              | can be       |
|              |              |              |              |              | ignored.     |
+--------------+--------------+--------------+--------------+--------------+--------------+

Estimation risks
~~~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| Risk ID      | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 21           | Expect the   | Medium       | Work is not  | Medium       | Give tasks   |
|              | team or a    |              | done or is   |              | that people  |
|              | team member  |              | done to an   |              | can do and   |
|              | can do more  |              | insufficient |              | if they      |
|              | than they    |              | standard.    |              | can't then   |
|              | actually     |              |              |              | help them.   |
|              | can.         |              |              |              |              |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 22           | We may       | Medium       | Work ends up | Medium       | Set          |
|              | underestimat |              | not getting  |              | realistic    |
|              | e            |              | done or not  |              | timings to   |
|              | how long it  |              | done to the  |              | do work and  |
|              | will take to |              | standard it  |              | be realistic |
|              | do some      |              | could be     |              | on how long  |
|              | work.        |              | done.        |              | a task will  |
|              |              |              |              |              | take.        |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 23           | Be too       | Medium       | We end up    | Low          | Push our     |
|              | pessimistic  |              | with a       |              | limits but   |
|              | about what   |              | product      |              | also stay    |
|              | we can       |              | which is not |              | realistic    |
|              | achieve.     |              | as good as   |              | and within   |
|              |              |              | it could     |              | the          |
|              |              |              | have         |              | requirements |
|              |              |              | possibly     |              | .            |
|              |              |              | been.        |              |              |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 24           | Distribute   | Low          | Team         | Low          | Distribute   |
|              | tasks        |              | over/under   |              | tasks        |
|              | incorrectly. |              | worked.      |              | appropriatel |
|              |              |              |              |              | y            |
|              |              |              |              |              | and tell     |
|              |              |              |              |              | others if    |
|              |              |              |              |              | feel         |
|              |              |              |              |              | over/under   |
|              |              |              |              |              | worked.      |
+--------------+--------------+--------------+--------------+--------------+--------------+