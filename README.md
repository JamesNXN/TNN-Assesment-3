# Lorem Ipsum
Visit out website at http://www.lihq.me, or read the documentation at http://docs.lihq.me

## Building the project
Import the contents of the `/game` directory using Gradle into IntelliJ IDEA or Eclipse
https://github.com/libgdx/libgdx/wiki/Gradle-and-Intellij-IDEA

## Testing
This project is tested using JUnit. Tests are located within the `/game/tests` directory. For test documentation, please see https://github.com/junit-team/junit4/wiki

### Running Tests
- Open a terminal and run `./gradlew test` from `/game` directory
- Results are shown in the terminal window.

### Adding Tests
- Create new class for tests under `/game/tests/src`, end the class name with UnitTests for consistency
- Import `org.junit.Test`
- Write a test function using assertions, and use `@Test` decorator above it
- See this page for examples of assertions: https://github.com/junit-team/junit4/wiki/assertions
- Run your tests and see if they pass!
