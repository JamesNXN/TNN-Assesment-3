Risk Assessment and Mitigation
==================================
Introduction
-------------
Risk management is an important part of any project, we must prepare for
what could happen during the course of the project in order to be able
to quickly recover and stay on track. The risks which are shown below
are relevant to the particular SEPR context and take into account the
scale of the project and aim to cover only risks which are realistic
within this context.

The risks are here presented in a tabular format. This table is split
into 6 columns; the first column gives the risk an identification number
(ID) for easy reference in our requirements and also if a risk does
happen and we need to resolve it. The second column describes the risk
itself. The third column gives an estimated likelihood of the risk
occurring. To indicate the likelihood of each risk occurring we have use
a high medium and low rating which is then also colour coded:

Likelihood
~~~~~~~~~~~
Low likelihood = This risk is not likely to occur. Roughly a 25% chance
although some extreme risks could be a lot lower.

Medium likelihood = There is an equal chance of the risk occurring or not
occurring. Roughly a 50% chance.

High likelihood = There is a good chance that this risk will occur .
Roughly a 75% chance.

The fourth column describes the impact the risk
would have on progress in the project. The fifth column shows the
severity of the impact using this colour coordination:

Severity
~~~~~~~~~
Low severity = May mean a few hours extra work but nothing major.

Medium severity = Could add up to a week of extra work and may threaten a
deadline.

High severity = A major set back which could affect the whole project.

The sixth and final column details how we will aim
to avoid such a risk and deal with it.

The overall table is split into sections which group together similar
risk such as software risks. Each section is then ordered by severity,
highest first. Equal severity is ordered by likelihood. This table will
be regularly consulted in an attempt to monitor the risks and try to
ensure they do not occur and catch them early if they are occurring.

Table of risks
-----------------
Software risks
~~~~~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| ID           | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 1            | Our game may | Medium       | No one will  | High         | Improve      |
|              | be slow or   |              | want to play |              | efficiency   |
|              | unresponsive |              | the game.    |              | of the game  |
|              | .            |              |              |              | wherever     |
|              |              |              |              |              | possible and |
|              |              |              |              |              | regularly    |
|              |              |              |              |              | check        |
|              |              |              |              |              | performance  |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 2            | Software     | Medium       | We would     | Medium       | Test the     |
|              | library      |              | struggle to  |              | elements of  |
|              | doesn’t work |              | implement    |              | the library  |
|              | or lacks a   |              | the feature  |              | you plan to  |
|              | feature      |              | we want to   |              | use          |
|              | e.g. has a   |              | add. We      |              | beforehand.  |
|              | bug that     |              | would also   |              | Also, make   |
|              | stops the    |              | use up lots  |              | sure the     |
|              | game from    |              | of time      |              | library has  |
|              | working, or  |              | trying to    |              | an active    |
|              | is missing a |              | solve the    |              | community    |
|              | feature      |              | issue.       |              | surrounding  |
|              | required for |              |              |              | it and that  |
|              | the game to  |              |              |              | bugs are     |
|              | work.        |              |              |              | fixed        |
|              |              |              |              |              | quickly. If  |
|              |              |              |              |              | it was early |
|              |              |              |              |              | stages we    |
|              |              |              |              |              | could get a  |
|              |              |              |              |              | new library  |
|              |              |              |              |              | but this     |
|              |              |              |              |              | would        |
|              |              |              |              |              | require us   |
|              |              |              |              |              | to rewrite   |
|              |              |              |              |              | our code to  |
|              |              |              |              |              | work with    |
|              |              |              |              |              | the new      |
|              |              |              |              |              | library.     |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 3            | Code is hard | Low          | Could cause  | Medium       | Use          |
|              | to           |              | bugs and     |              | meaningful   |
|              | understand   |              | makes bug    |              | variable     |
|              | and seems    |              | fixing       |              | names and    |
|              | too complex. |              | harder.      |              | plenty of    |
|              |              |              | Slows down   |              | comments,bot |
|              |              |              | the          |              | h            |
|              |              |              | productivity |              | in code and  |
|              |              |              | of the       |              | in commit    |
|              |              |              | group.       |              | messages.    |
|              |              |              |              |              | Make sure    |
|              |              |              |              |              | code is      |
|              |              |              |              |              | reviewed by  |
|              |              |              |              |              | the majority |
|              |              |              |              |              | of members   |
|              |              |              |              |              | before it    |
|              |              |              |              |              | gets merged  |
|              |              |              |              |              | into the     |
|              |              |              |              |              | repository.  |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 4            | Conflicts in |  High        | May need to  | Low          | Make sure    |
|              | git.         |              | move code    |              | people work  |
|              | Different    |              | around and   |              | on separate  |
|              | members      |              | even         |              | elements by  |
|              | changing the |              | rewrite.     |              | assigning    |
|              | same code.   |              |              |              | them to      |
|              |              |              |              |              | different    |
|              |              |              |              |              | tasks and if |
|              |              |              |              |              | not then     |
|              |              |              |              |              | make use of  |
|              |              |              |              |              | Gits tools.  |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 5            | Our own      | High         | Will need to | Low          | This is a    |
|              | software     |              | bug fix.     |              | normal part  |
|              | doesn’t work |              | Loss of time |              | of software  |
|              | as intended. |              | and          |              | development. |
|              |              |              | potentially  |              | We all make  |
|              |              |              | productivity |              | mistakes.    |
|              |              |              | if that      |              | However,     |
|              |              |              | function or  |              | before code  |
|              |              |              | feature is   |              | is approved  |
|              |              |              | the          |              | by the group |
|              |              |              | bottleneck   |              | we will use  |
|              |              |              | of the game. |              | unit testing |
|              |              |              |              |              | that will    |
|              |              |              |              |              | test key     |
|              |              |              |              |              | functions of |
|              |              |              |              |              | the game as  |
|              |              |              |              |              | we develop   |
|              |              |              |              |              | them meaning |
|              |              |              |              |              | that should  |
|              |              |              |              |              | a function   |
|              |              |              |              |              | break we     |
|              |              |              |              |              | will know    |
|              |              |              |              |              | about it     |
|              |              |              |              |              | before it’s  |
|              |              |              |              |              | merged.      |
+--------------+--------------+--------------+--------------+--------------+--------------+

Hardware risks
~~~~~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| ID           | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 6            | Personal     | Low          | Could lose   | Low          | | Ensure     |
|              | computer     |              | work and be  |              |   work is    |
|              | breaks long  |              | unable to    |              |   saved      |
|              | term or is   |              | work.        |              |   online to  |
|              | lost.        |              |              |              |   google     |
|              |              |              |              |              |   drive      |
|              |              |              |              |              |    cloud     |
|              |              |              |              |              |   service    |
|              |              |              |              |              |   and that   |
|              |              |              |              |              |   code is    |
|              |              |              |              |              |   stored on  |
|              |              |              |              |              |   github.    |
|              |              |              |              |              |   Department |
|              |              |              |              |              |   PC’s       |
|              |              |              |              |              |   should be  |
|              |              |              |              |              |   accessible |
|              |              |              |              |              |   most days  |
|              |              |              |              |              |   and have   |
|              |              |              |              |              |   all the    |
|              |              |              |              |              |   tools we   |
|              |              |              |              |              |   need.      |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 7            | Personal     | Medium       | Potentially  | Low          | Save         |
|              | computer     |              | will have    |              | regularly,   |
|              | crashes      |              | lose work,   |              | google       |
|              | while        |              | meaning you  |              | docs[2] will |
|              | working.     |              | lose time    |              | do this for  |
|              |              |              | doing it     |              | us.          |
|              |              |              | again.       |              | Regularly    |
|              |              |              |              |              | commit code  |
|              |              |              |              |              | to personal  |
|              |              |              |              |              | branches so  |
|              |              |              |              |              | that it      |
|              |              |              |              |              | stored       |
|              |              |              |              |              | elsewhere    |
|              |              |              |              |              | other than   |
|              |              |              |              |              | your PC .    |
+--------------+--------------+--------------+--------------+--------------+--------------+

Risks with people
~~~~~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| ID           | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
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
| 9            | A team       | High         | They might   | Medium       | Hard to      |
|              | member is    |              | have been    |              | avoid, but   |
|              | ill/away for |              | skilled in a |              | we should    |
|              | a week or    |              | certain area |              | store work   |
|              | two.         |              | that no      |              | online where |
|              |              |              | other member |              | everyone can |
|              |              |              | can do well. |              | access.      |
|              |              |              | If they have |              |              |
|              |              |              | the only     |              | If we work   |
|              |              |              | access to    |              | in pairs to  |
|              |              |              | work may get |              | complete     |
|              |              |              | behind from  |              | tasks then   |
|              |              |              | it.          |              | there will   |
|              |              |              |              |              | be less of a |
|              |              |              |              |              | chance of    |
|              |              |              |              |              | having one   |
|              |              |              |              |              | person who   |
|              |              |              |              |              | knows the    |
|              |              |              |              |              | most about   |
|              |              |              |              |              | one area.    |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 10           | Arguments    | Medium       | Disrupts the | Medium       | Try to avoid |
|              | within the   |              | work of the  |              | conflict but |
|              | team.        |              | team and     |              | if necessary |
|              |              |              | prevents us  |              | have proper  |
|              |              |              | moving       |              | debates      |
|              |              |              | forwards.    |              | perhaps      |
|              |              |              | Also,        |              | using a      |
|              |              |              | unpleasant   |              | mediator, do |
|              |              |              | for the team |              | not keep     |
|              |              |              | as a whole.  |              | issues       |
|              |              |              |              |              | hidden.      |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 11           | Lack of      | Medium       | Tasks may be | Medium       | Keep strong  |
|              | communication|              | done twice   |              | communicatio |
|              |              |              | or not done  |              | n            |
|              |              |              | at all.      |              | using the    |
|              |              |              |              |              | tools we     |
|              |              |              |              |              | plan to use. |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 12           | A team       | Medium       | Could        | Low          | Don’t give   |
|              | member does  |              | disrupt      |              | members too  |
|              | not do their |              | other        |              | much work or |
|              | work.        |              | members work |              | work they    |
|              |              |              | and could    |              | cannot do,   |
|              |              |              | make the     |              | ensure that  |
|              |              |              | other team   |              | the team     |
|              |              |              | members      |              | communicates |
|              |              |              | annoyed.     |              | well and     |
|              |              |              |              |              | regularly    |
|              |              |              |              |              | meets up to  |
|              |              |              |              |              | discuss how  |
|              |              |              |              |              | the work is  |
|              |              |              |              |              | going.       |
+--------------+--------------+--------------+--------------+--------------+--------------+

Risks with tools
~~~~~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| ID           | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 13           | Google drive | Low          | Could        | Medium       | Store work   |
|              | servers      |              | lose/lose    |              | locally ,    |
|              | stop         |              | access to    |              | and on other |
|              | working.     |              | work that is |              | services.    |
|              |              |              | stored       |              |              |
|              |              |              | there.       |              |              |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 14           | Central git  | Low          | Temporarily  | Low          | Keep up to   |
|              | repository   |              | lose access  |              | date local   |
|              | [1]          |              | to it.       |              | copies so    |
|              | is lost in   |              |              |              | can be       |
|              | some way.    |              |              |              | easily       |
|              |              |              |              |              | restored. We |
|              |              |              |              |              | could host   |
|              |              |              |              |              | our own      |
|              |              |              |              |              | local copy   |
|              |              |              |              |              | should       |
|              |              |              |              |              | github go    |
|              |              |              |              |              | down.        |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 15           | Website      | Low          | Users lose   | Medium       | The website  |
|              | hosting      |              | access to    |              | files are    |
|              | fails.       |              | the website. |              | stored on    |
|              |              |              |              |              | github and   |
|              |              |              |              |              | every team   |
|              |              |              |              |              | member has a |
|              |              |              |              |              | local copy   |
|              |              |              |              |              | of the       |
|              |              |              |              |              | repository   |
|              |              |              |              |              | on their     |
|              |              |              |              |              | computer so  |
|              |              |              |              |              | we could     |
|              |              |              |              |              | bring the    |
|              |              |              |              |              | site back up |
|              |              |              |              |              | on a         |
|              |              |              |              |              | different    |
|              |              |              |              |              | server. The  |
|              |              |              |              |              | site is also |
|              |              |              |              |              | protected by |
|              |              |              |              |              | cloud-flare[ |
|              |              |              |              |              | 3]           |
|              |              |              |              |              | who will     |
|              |              |              |              |              | provide a    |
|              |              |              |              |              | cached       |
|              |              |              |              |              | version of   |
|              |              |              |              |              | the site if  |
|              |              |              |              |              | our host     |
|              |              |              |              |              | were to go   |
|              |              |              |              |              | down.        |
+--------------+--------------+--------------+--------------+--------------+--------------+

Requirements risks
~~~~~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| ID           | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 16           | Not          | Low          | We let the   | High         | Make sure    |
|              | including a  |              | customer     |              | key          |
|              | requirement  |              | down and     |              | requirements |
|              | which is     |              | have failed  |              | are elicited |
|              | required by  |              | them.        |              | from the     |
|              | the          |              |              |              | customer so  |
|              | customer.    |              |              |              | they get     |
|              |              |              |              |              | what they    |
|              |              |              |              |              | want.        |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 17           | A            | High         | May need to  | Medium       | Our software |
|              | requirement  |              | rewrite code |              | architecture |
|              | could        |              | or add extra |              | must be      |
|              | change / be  |              | code to      |              | flexible and |
|              | added.       |              | account for  |              | able to be   |
|              |              |              | it. Extra    |              | changed      |
|              |              |              | time will be |              | easily.      |
|              |              |              | needed.      |              |              |
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
|              | requirements |              | making       |              | requirements |
|              |              |              | something    |              | are clear    |
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
~~~~~~~~~~~~~~~~~~
+--------------+--------------+--------------+--------------+--------------+--------------+
| ID           | Description  | Likelihood   | Impact       | Severity     | Mitigation   |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 21           | Expect the   | Medium       | Work is not  | Medium       | Give tasks   |
|              | team or a    |              | done or is   |              | that people  |
|              | team member  |              | done to an   |              | can do and   |
|              | can do more  |              | insufficient |              | if they      |
|              | than they    |              | standard.    |              | can’t then   |
|              | actually     |              |              |              | help them.   |
|              | can.         |              |              |              | When working |
|              |              |              |              |              | on difficult |
|              |              |              |              |              | tasks work   |
|              |              |              |              |              | in pairs to  |
|              |              |              |              |              | complete the |
|              |              |              |              |              | task meaning |
|              |              |              |              |              | individual   |
|              |              |              |              |              | team members |
|              |              |              |              |              | don’t feel   |
|              |              |              |              |              | as           |
|              |              |              |              |              | overwhelmed  |
|              |              |              |              |              | by the task  |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 22           | We may       | Medium       | Work ends up | Medium       | Set          |
|              | underestimate|              | taking       |              | realistic    |
|              | how long it  |              | longer than  |              | timings to   |
|              | will take to |              | expected or  |              | do work and  |
|              | do some      |              | not done to  |              | be realistic |
|              | work.        |              | the standard |              | on how long  |
|              |              |              | it could be  |              | a task will  |
|              |              |              | done. This   |              | take.        |
|              |              |              | could cause  |              | Account for  |
|              |              |              | other areas  |              | unforeseen   |
|              |              |              | of the       |              | delays in    |
|              |              |              | project to   |              | our plan     |
|              |              |              | suffer       |              | adding time  |
|              |              |              |              |              | where we can |
|              |              |              |              |              | catch up.    |
+--------------+--------------+--------------+--------------+--------------+--------------+
| 23           | Be too       | Medium       | We end up    | Low          | Push our     |
|              | pessimistic  |              | with a       |              | limits but   |
|              | about what   |              | product      |              | also stay    |
|              | we can       |              | which is not |              | realistic    |
|              | achieve.     |              | as good as   |              | and within   |
|              |              |              | it could     |              | the          |
|              |              |              | have         |              | requirements |
|              |              |              | possibly     |              | .            |
|              |              |              | been.        |              | If we have   |
|              |              |              |              |              | extra time   |
|              |              |              |              |              | then we can  |
|              |              |              |              |              | use it to    |
|              |              |              |              |              | enhance the  |
|              |              |              |              |              | product.     |
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

Bibliography
--------------

[1] GitHub [online] Available https://github.com [Accessed 01/11/2016]

[2] Google Drive [online] Available https://www.google.com/drive/ [Accessed 01/11/2016]

[3] Cloud Flare Available[online] https://www.cloudflare.com/ [Accessed 01/11/2016]
