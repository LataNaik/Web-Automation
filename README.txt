================================================================================
                      JAVA WEB AUTOMATION FRAMEWORK
================================================================================

A Playwright-based web automation framework using Java and TestNG.


QUICK START
--------------------------------------------------------------------------------

1. Ensure Java 11+ is installed:
   java -version

2. Ensure Maven is installed:
   mvn -version

3. Install dependencies:
   mvn clean install -DskipTests

4. Run tests:
   mvn test


PROJECT STRUCTURE
--------------------------------------------------------------------------------

web-automation/
|-- src/
|   |-- test/
|       |-- java/
|       |   |-- base/
|       |   |   |-- BaseTest.java       # Browser setup/teardown
|       |   |
|       |   |-- pages/
|       |   |   |-- LoginPage.java      # Page Object classes
|       |   |
|       |   |-- tests/
|       |   |   |-- LoginTest.java      # Test classes
|       |   |
|       |   |-- utils/
|       |       |-- ConfigReader.java   # Configuration utility
|       |
|       |-- resources/
|           |-- config.properties       # Configuration file
|
|-- pom.xml                             # Maven dependencies
|-- testng.xml                          # TestNG suite configuration
|-- APPROACH_DOCUMENT.txt               # Detailed documentation
|-- README.txt                          # This file


RUNNING TESTS
--------------------------------------------------------------------------------

Run all tests:
    mvn test

Run specific test class:
    mvn test -Dtest=LoginTest

Run specific test method:
    mvn test -Dtest=LoginTest#verifyUserCanLogin

Run using testng.xml:
    mvn test -DsuiteXmlFile=testng.xml

Run from IDE:
    Right-click on test class/method -> Run As -> TestNG Test


CONFIGURATION
--------------------------------------------------------------------------------

Edit src/test/resources/config.properties to change settings:

    base.url    - Application URL
    browser     - chromium, firefox, or webkit
    headless    - true (no UI) or false (visible browser)


ADDING NEW TESTS
--------------------------------------------------------------------------------

1. Create page object in pages package:

   // pages/HomePage.java
   package pages;

   import com.microsoft.playwright.Locator;
   import com.microsoft.playwright.Page;

   public class HomePage {
       private Page page;
       private Locator welcomeMsg;

       public HomePage(Page page) {
           this.page = page;
           this.welcomeMsg = page.locator("#welcome");
       }

       public String getWelcomeText() {
           return welcomeMsg.textContent();
       }
   }

2. Create test class in tests package:

   // tests/HomeTest.java
   package tests;

   import org.testng.Assert;
   import org.testng.annotations.Test;
   import base.BaseTest;
   import pages.HomePage;

   public class HomeTest extends BaseTest {

       @Test
       public void verifyWelcomeMessage() {
           HomePage homePage = new HomePage(page);
           Assert.assertTrue(homePage.getWelcomeText().contains("Welcome"));
       }
   }

3. Add test class to testng.xml:

   <class name="tests.HomeTest"/>


DEPENDENCIES (pom.xml)
--------------------------------------------------------------------------------

- playwright        : Browser automation library
- testng            : Test framework
- maven-surefire    : Test execution plugin


TESTNG.XML CONFIGURATION
--------------------------------------------------------------------------------

Current configuration:

    <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
    <suite name="Web Automation Suite">
        <test name="Smoke Tests">
            <classes>
                <class name="tests.LoginTest"/>
            </classes>
        </test>
    </suite>

Adding test groups:

    <test name="Regression Tests">
        <groups>
            <run>
                <include name="regression"/>
            </run>
        </groups>
        <classes>
            <class name="tests.LoginTest"/>
            <class name="tests.HomeTest"/>
        </classes>
    </test>


BROWSER OPTIONS
--------------------------------------------------------------------------------

The framework uses Google Chrome by default. To use other browsers,
modify BaseTest.java:

    // Chromium (default)
    BrowserType browserType = playwright.chromium();

    // Firefox
    BrowserType browserType = playwright.firefox();

    // WebKit (Safari)
    BrowserType browserType = playwright.webkit();


LOCATOR STRATEGIES
--------------------------------------------------------------------------------

By ID:
    page.locator("#elementId")

By CSS Selector:
    page.locator("input[name='username']")

By Test ID:
    page.getByTestId("login-btn")

By Text:
    page.getByText("Submit")

By Role:
    page.getByRole(AriaRole.BUTTON)

By Placeholder:
    page.getByPlaceholder("Enter email")


TROUBLESHOOTING
--------------------------------------------------------------------------------

1. Compilation error - Java version:
   Add to pom.xml:
   <properties>
       <maven.compiler.source>11</maven.compiler.source>
       <maven.compiler.target>11</maven.compiler.target>
   </properties>

2. Browser dependencies missing:
   Run: mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install-deps"

3. Chrome not found:
   Install Google Chrome or remove .setChannel("chrome") from BaseTest.java

4. Test not found:
   Ensure test class is listed in testng.xml

5. Timeout errors:
   Increase timeout in BaseTest.java:
   page.setDefaultTimeout(60000);


PREREQUISITES
--------------------------------------------------------------------------------

1. Java JDK 11+ installed and JAVA_HOME configured
2. Maven 3.x installed
3. Google Chrome browser installed
4. IDE (VS Code, IntelliJ, Eclipse) with Java and TestNG plugins


================================================================================
