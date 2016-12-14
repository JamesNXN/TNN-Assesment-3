# Lorem Ipsum [![CircleCI](https://circleci.com/gh/Brookke/Lorem-Ipsum.svg?style=svg)](https://circleci.com/gh/Brookke/Lorem-Ipsum)
Visit out website at http://www.lihq.me, or read the documentation at http://docs.lihq.me

## Get started
### What you need
Before you can start working on the game you need to ensure you have Java installed and the latest JDK, you can get java here:

https://java.com/en/download/

and the JDK here:

http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

You also need the Intellij IDEA IDE, you can get this from here:

https://www.jetbrains.com/idea/#chooseYourEdition

The free version is good enough although the paid version is obtainable for free for students.
### Import the project
Once both of these are installed you need to:

1. Clone the GitHub repository on to your local documents. 
2. Open up IntelliJ and select import project, then find where you cloned the project to and select the build.gradle file in the game folder. Click ok.
3. Next uncheck 'Create separate module per source set', click ok again.
4. IntelliJ may ask you to set up the JDK, if it does this simply navigate to where the JDK is on your computer. 

It will then import the game, this may take some time.

One common problem is to do with a missing JDK, This is a solution :
Configure -> Project Defaults -> Project Structure then add your JDK in Platform Settings -> SDKs. Some other problems have solutions [here](https://github.com/libgdx/libgdx/wiki/Gradle-and-Intellij-IDEA).

 
### Edit the game
You can now edit the game , we recommend making a new branch, then make changes on that branch. You can use GitHub desktop or equivalent to commit the changes to your branch and then use sync to upload those changes. When you are ready submit a pull request and have someone check it.
### Run the game
You can run by first building as described below and then simply clicking run.

## Building the project
To build the game use the built in run configuration *Desktop* in the same way that you run tests.

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
