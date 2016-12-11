# Lorem Ipsum [![CircleCI](https://circleci.com/gh/Brookke/Lorem-Ipsum.svg?style=svg)](https://circleci.com/gh/Brookke/Lorem-Ipsum)
Visit out website at http://www.lihq.me, or read the documentation at http://docs.lihq.me

## Building the project
Import the contents of the `/game` directory using Gradle into IntelliJ IDEA or Eclipse
https://github.com/libgdx/libgdx/wiki/Gradle-and-Intellij-IDEA

## Testing
This project is tested using JUnit. Tests are located within the `/game/tests` directory. For test documentation, please see https://github.com/junit-team/junit4/wiki

### Running Tests
For every commit CircleCI runs all the included tests, however we recomend that you run test locally too before committing.

####Running tests locally
We have included a handy test configuration that can be ran from intellij.
![Running tests locally in intellij](https://thumbs.gfycat.com/SentimentalGargantuanAmericanshorthair-size_restricted.gif)

### Adding Tests
- Create new class for tests under `/game/tests/src` When naming the class end the name with `UnitTests` for consistency e.g. `PlayerUnitTests`
- This class should extend `GameTester` this initialises the backend of the game so that test run correctly. 
- Import `org.junit.Test`
- Write a test function using assertions, and use `@Test` decorator above it
- See this page for examples of assertions: https://github.com/junit-team/junit4/wiki/assertions
- Run your tests locally and see if they pass!

### CircleCI Test Results
After tests have run the results are displayed in the "Test Summary" tab on CircleCI.

If the tests have failed and no test summary is provided, this normally means that the code doesn't compile, or there is a problem with the test code. To gather more information, scroll down to read the console output from when the tests were run.
