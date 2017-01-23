Executable Test Plan
====================

Unit Tests
----------------------------------

This project is unit tested with JUnit. Tests are located in the
``/game/tests`` directory in the GitHub repository. For documentation on writing
these tests, please see https://github.com/junit-team/junit4/wiki

Running Unit Tests
~~~~~~~~~~~~~~~~~~~~~~~~~~

For every commit CircleCI runs all the included tests, however, we
recommend that you run tests locally too before committing.

We have included a handy test configuration inside the repository that can be run from
IntelliJ IDEA. |Running tests locally in intellij|

Adding Tests
~~~~~~~~~~~~

-  Create new class for tests under ``/game/tests/src`` When naming the
   class end the name with ``UnitTests`` for consistency e.g.
   ``PlayerUnitTests``
-  This class should extend ``GameTester`` this initialises the backend
   of the game so that tests run correctly.
-  Import ``org.junit.Test``
-  Write a test function using assertions, and use ``@Test`` decorator
   above it
-  See this page for examples of assertions:
   https://github.com/junit-team/junit4/wiki/assertions
-  Run your tests locally and see if they pass!

CircleCI
----------
Viewing Results
~~~~~~~~~~~~~~~~~~~~~

After tests have run the results are displayed in the “Test Summary” tab
on CircleCI. This tab contains a summary of the testing result, along with
any tests that have failed.

If the tests have failed and no test summary is provided, this normally
means that the code doesn’t compile, or there is a problem with the test
code. To gather more information, scroll down to read the console output
from when the tests were run.

Also, CircleCI collects test "artifacts", which are located in the
"Artifacts" tab. This contains useful output files such as:

- HTML output: A website that provides a visual testing report with more details
- JUnit XML output: XML files for each class that provide raw data about each run test


Configuration
~~~~~~~~~~~~~~~~~~~~~

We have included a configuration file for setting up CircleCI tests in the
root directory of the project. See ``circle.yml``.

To setup CircleCI, you will need to link your GitHub account. Once this is done,
you can add a project within CircleCI, which will automatically setup tests using
the configuration file (``circle.yml``) mentioned earlier.

Whenever a commit is pushed to GitHub, CircleCI will run the tests and
inform GitHub of the result, which will be displayed against each commit, and in
any pull requests.


Acceptance Tests
----------------------

We’ve also included a bunch of acceptance tests that can be run manually to
ensure the game performs as expected, and meets the requirements. These have not been automated,
so will need to be run by hand. These can be found in the Appendix for the Assessment 2 documents.


.. |Running tests locally in intellij| image:: https://thumbs.gfycat.com/SentimentalGargantuanAmericanshorthair-size_restricted.gif
