# Web Automation Testing Framework



## 🚀 Overview
This is a robust test automation framework built using Selenium WebDriver, Java, TestNG, and Cucumber. The framework supports multi-browser testing and follows a modular, page object model architecture.

## 🛠 Tech Stack
- **Java:** Core programming language (JDK 11+)
- **Maven:** Dependency management and build tool
- **Selenium WebDriver:** Web automation tool
- **TestNG:** Test execution framework
- **Cucumber:** BDD framework
- **Log4j:** Logging framework
- **ExtentReports:** Reporting tool
- **SnakeYAML:** Configuration management
- **WebDriverManager:** Browser driver management

## 📁 Project Structure
```
automation-framework/
├── common-utils/
│   ├── src/
│   │   ├── main/java/com/framework/utils/
│   │   │   ├── BrowserUtils.java
│   │   │   ├── ConfigReader.java
│   │   │   ├── ExcelUtils.java
│   │   │   └── LogUtils.java
│   │   └── pom.xml
├── web-automation/
│   ├── src/
│   │   ├── main/java/com/framework/
│   │   │   ├── pages/
│   │   │   ├── steps/
│   │   │   └── config/
│   │   ├── test/java/com/framework/
│   │   │   └── runners/
│   │   └── test/resources/
│   │       ├── features/
│   │       ├── config.yaml
│   │       └── testng.xml
│   └── pom.xml
├── api-automation/
│   └── [API testing related files]
└── pom.xml
```

## 🔧 Configuration
### Browser Configuration (config.yaml)
```yaml
browser:
  type: "chrome"  # chrome, firefox, edge
  headless: false
  implicit_wait: 10
  explicit_wait: 20

environment:
  qa: "https://qa.example.com"
  stage: "https://stage.example.com"
  prod: "https://prod.example.com"
```

## 🔄 Prerequisites
1. JDK 11 or higher
2. Maven 3.8.x or higher
3. Chrome/Firefox/Edge browsers
4. Git

## 🚀 Getting Started
1. Clone the repository:
```bash
git clone https://github.com/yourusername/automation-framework.git
```

2. Install dependencies:
```bash
mvn clean install -DskipTests
```

3. Run tests:
```bash
# Run all tests
mvn test

# Run specific test suite
mvn test -Dsuite=regression

# Run with specific browser
mvn test -Dbrowser=firefox

# Run with specific environment
mvn test -Denv=qa
```

## 🎯 Test Execution Options
### Running Tests in Different Browsers
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

### Running Tests in Parallel
```bash
mvn test -Dparallel=methods -DthreadCount=3
```

### Running Specific Features
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

## 📊 Reporting
- Test execution reports are generated in `target/reports`
- Screenshots for failed tests are saved in `target/screenshots`
- Extent Reports are generated at `target/extent-reports`

## 🔍 Key Features
1. **Cross-browser Testing**: Support for Chrome, Firefox, and Edge
2. **Parallel Execution**: TestNG parallel execution capabilities
3. **Screenshot Capture**: Automatic capture on test failure
4. **Data-Driven**: Support for Excel, CSV, and YAML data sources
5. **BDD**: Cucumber integration for behavior-driven development
6. **Page Object Model**: Modular and maintainable page objects
7. **Logging**: Comprehensive logging with Log4j
8. **Reporting**: Detailed HTML reports with ExtentReports
9. **Configuration Management**: External configuration support
10. **Reusable Components**: Common utilities module

## 🏗 Framework Architecture
```
[Browser] ←→ [WebDriver] ←→ [Page Objects] ←→ [Step Definitions] ←→ [Feature Files]
                               ↑
                         [Common Utils]
```

## 📝 Writing Tests
### Feature File Example
```gherkin
Feature: Login Functionality

Scenario: Successful Login
    Given I am on the login page
    When I enter username "test@example.com"
    And I enter password "password123"
    And I click the login button
    Then I should be logged in successfully
```

### Page Object Example
```java
public class LoginPage {
    @FindBy(id = "username")
    private WebElement usernameInput;
    
    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }
}
```

## 🤝 Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📫 Contact
- Project Maintainer: Your Name
- Email: your.email@example.com

## 📄 License
This project is licensed under the MIT License - see the LICENSE.md file for details
