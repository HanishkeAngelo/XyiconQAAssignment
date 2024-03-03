# XyIcon Assignment

- The **Xyicon** Assessment Project aims to automate the testing process for web applications using Selenium WebDriver,
with TestNG as the backbone for test case management and execution. 
Log4j is used to handle logging, 
ensuring that detailed information about the test runs is available for analysis. 
WebDriverManager streamlines the setup process by handling WebDriver binary management, 
and Allure Report generates detailed and visually appealing test reports. 
This combination of technologies ensures a robust, efficient, and effective testing solution for the Xyicon assessment.

## Technologies Used

- **Selenium WebDriver:** A browser automation framework that allows us to perform web-based application testing across various browsers. It simulates user actions on web elements and is instrumental in executing our test cases.
- **TestNG:** Our testing framework of choice, TestNG, is utilized for its powerful testing capabilities. It provides annotations to design and manage test cases, supporting data-driven testing and allowing for a structured way to generate test suites and groups.
- **Log4j:** A reliable logging framework, Log4j, is incorporated to facilitate the logging of the system's runtime behavior. It helps in debugging and provides insights into the execution flow, making it easier to identify any issues during the test execution.
- **WebDriverManager:** This tool simplifies the management of driver binaries for different browsers. It automatically downloads the required WebDriver binaries for Chrome, Firefox, Edge, or other browsers, ensuring that our Selenium WebDriver tests are always running on the latest versions.
- **Allure Report:** For reporting purposes, Allure Report is integrated to generate comprehensive test execution reports. It offers a web-based report that is easy to navigate and provides detailed information about test execution, including test case descriptions, steps, statuses, and attachments for evidence.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java 17 is installed on your machine
- Allure reporting is installed on your machine

## Setting Up the Project

- Open project in IntelliJ idea and install maven dependencies


## Project Configuration

- all project configuration handle by "[configuration.properties](src%2Ftest%2Fresources%2Fconfig%2Fconfiguration.properties)"
- Users can switch between three browsers: Chrome, Firefox, and Edge.

## Execution 

- Run the tests in "src/test/java/com/xyiconqaassignment/xyiconqaassignment/Test/XYIconLoginTest.java"


## Report Generation

- Run the command in cmd:  Allure server " Absolute path to allure-results folder"