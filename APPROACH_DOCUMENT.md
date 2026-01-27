# Web Automation Framework - Approach Document

## 1. Overview

This is a web automation testing framework built using **Playwright** with **Java** and **TestNG**. The framework follows the **Page Object Model (POM)** design pattern for maintainable and scalable test automation.

## 2. Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| Programming Language | Java | 11+ |
| Automation Library | Playwright | 1.42.0 |
| Test Framework | TestNG | 7.9.0 |
| Build Tool | Maven | 3.x |
| Browser | Chromium/Chrome | Latest |

## 3. Framework Architecture

```
web-automation/
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java       # Base class with browser setup/teardown
│       │   ├── pages/
│       │   │   └── LoginPage.java      # Page Object classes
│       │   ├── tests/
│       │   │   └── LoginTest.java      # Test classes
│       │   └── utils/
│       │       └── ConfigReader.java   # Configuration utility
│       └── resources/
│           └── config.properties       # Environment configuration
├── pom.xml                             # Maven dependencies
└── testng.xml                          # TestNG suite configuration
```

## 4. Design Patterns

### 4.1 Page Object Model (POM)
- Each web page is represented by a separate Java class
- Page classes contain locators and methods to interact with page elements
- Tests use page objects to perform actions, improving readability and reusability

### 4.2 Base Test Pattern
- Common setup and teardown logic is centralized in `BaseTest.java`
- All test classes extend `BaseTest` to inherit browser initialization
- Reduces code duplication and ensures consistent test execution

## 5. Framework Components

### 5.1 BaseTest.java
**Purpose:** Handles browser lifecycle management

**Responsibilities:**
- Initialize Playwright instance
- Launch browser with configurable options (headless/headed, browser channel)
- Create new page and navigate to base URL
- Provide `page` object to test classes
- Clean up resources after test execution

### 5.2 Page Objects (e.g., LoginPage.java)
**Purpose:** Encapsulate page-specific elements and actions

**Responsibilities:**
- Define element locators using Playwright selectors
- Provide reusable methods for page interactions
- Abstract low-level Playwright API from tests

### 5.3 Test Classes (e.g., LoginTest.java)
**Purpose:** Contain actual test cases

**Responsibilities:**
- Define test methods with `@Test` annotation
- Use page objects to perform test steps
- Include assertions for validation

### 5.4 ConfigReader.java
**Purpose:** Manage configuration properties

**Responsibilities:**
- Load properties from `config.properties`
- Provide static method to retrieve configuration values
- Centralize environment-specific settings

### 5.5 config.properties
**Purpose:** Store environment configuration

**Contents:**
```properties
base.url=https://example.com/login
browser=chromium
headless=false
```

### 5.6 testng.xml
**Purpose:** TestNG suite configuration file

**Responsibilities:**
- Define test suites and test groups
- Specify which test classes to execute
- Configure test execution order
- Enable parallel execution settings

**Contents:**
```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Web Automation Suite">
    <test name="Smoke Tests">
        <classes>
            <class name="tests.LoginTest"/>
        </classes>
    </test>
</suite>
```

**Key Elements:**
| Element | Description |
|---------|-------------|
| `<suite>` | Root element, defines the test suite name |
| `<test>` | Groups related test classes (e.g., Smoke Tests, Regression Tests) |
| `<classes>` | Contains list of test classes to execute |
| `<class>` | Specifies individual test class with full package path |

## 6. Execution Flow

```
1. TestNG triggers test execution
        ↓
2. @BeforeMethod (BaseTest.setup())
   - Create Playwright instance
   - Launch browser
   - Create new page
   - Navigate to base URL
        ↓
3. @Test method executes
   - Instantiate Page Object
   - Perform test actions
   - Validate results
        ↓
4. @AfterMethod (BaseTest.tearDown())
   - Close browser
   - Close Playwright instance
```

## 7. Locator Strategies

The framework uses Playwright's locator strategies:

| Strategy | Example | Use Case |
|----------|---------|----------|
| CSS Selector | `page.locator("#username")` | By ID |
| CSS Selector | `page.locator("input[name='password']")` | By attribute |
| Test ID | `page.getByTestId("login-btn")` | Data-testid attribute |
| Text | `page.getByText("Submit")` | By visible text |
| Role | `page.getByRole("button")` | By ARIA role |
| Placeholder | `page.getByPlaceholder("Enter email")` | By placeholder text |

## 8. How to Run Tests

### 8.1 Using Maven (Command Line)
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=LoginTest

# Run specific test method
mvn test -Dtest=LoginTest#verifyUserCanLogin
```

### 8.2 Using IDE
- Right-click on test class/method → Run As → TestNG Test

### 8.3 Using testng.xml
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## 9. Configuration Options

| Property | Description | Values |
|----------|-------------|--------|
| `base.url` | Application URL | URL string |
| `browser` | Browser to use | chromium, firefox, webkit |
| `headless` | Run without UI | true, false |

## 10. Best Practices Implemented

1. **Separation of Concerns:** Tests, pages, and utilities are in separate packages
2. **Configuration Externalization:** Environment settings in properties file
3. **Reusable Components:** Base class and page objects reduce duplication
4. **Maintainability:** Locators centralized in page objects
5. **Scalability:** Easy to add new pages and tests

## 11. Future Enhancements

- [ ] Add reporting (Allure/ExtentReports)
- [ ] Implement parallel test execution
- [ ] Add screenshot capture on failure
- [ ] Integrate with CI/CD pipeline
- [ ] Add data-driven testing support
- [ ] Implement retry mechanism for flaky tests
- [ ] Add logging framework (Log4j/SLF4J)

## 12. Prerequisites

1. **Java JDK 11+** installed and configured
2. **Maven 3.x** installed
3. **Google Chrome** browser installed (for `setChannel("chrome")`)
4. **IDE** (VS Code, IntelliJ, Eclipse) with Java and TestNG plugins

## 13. Dependencies (pom.xml)

```xml
<dependencies>
    <!-- Playwright -->
    <dependency>
        <groupId>com.microsoft.playwright</groupId>
        <artifactId>playwright</artifactId>
        <version>1.42.0</version>
    </dependency>

    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.9.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

**Document Version:** 1.0
**Last Updated:** January 2026
