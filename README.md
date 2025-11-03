# Selenium Testing

This projects tests the main end-to-end flows of the [sample-app-web](https://github.com/tamara-dimeska/sample-app-web).
I used Selenium and Java for this project. The reason for this is that I wanted to play around with old-school technologies, and compare it how they hold in comparison to newer ones, like Cypress and Playwright. I have done project for the same app with Cypress and Playwright too. They can be found in [this repo](https://github.com/tamara-dimeska/sample-app-web). There are designated branches for each project: `e2e-tests-with-cypress` and `e2e-tests-with-playwright`.
All the tests and assets for them can be found in `src/test/java/dev.selenium`.

## End-to-end Tests
The tests follow Page Object Model (POM) design pattern.

Each page of the app has its own page class. They are all saved in the `pageobjects` folder. All classes inherit from `BasePage` class that has all the code that is shared amongst all pages.
For location of elements I used `PageFactory` pattern. I find it a clean and easy to maintain way of locating the elements. The downside is that it doesn't support locating elements by custom ids, in this case `data-test`. The workaround that I used is to look for the elements by css, but in the css, I used the custom ids, I have added in the source code repo.

For the interaction with the elements, I have created my own class, called `BaseElement`, located in the `utils` folder. The reason for the introduction of this class with all custom methods is that sometimes we need to wait before interacting with the elements, or before doing assertions, and this doesn't come out of the box with Selenium.
In the `utils` folder other helper/util classes can be found, such as `Screenshot`. In this class we have the `takeScreenshot` method that takes screenshot at the end of each test.

Last, but not least, is the `tests` folder, in which are all the test files. All test classes inherit from the `BaseTest` class. In it we have all the shared code for the tests, including `launch` and `tearDown` methods, that run before/after each test method.

All tests are independent of each other and can run stand alone or sequentially. 

## Reporting
After the tests are run, an Allure report is created. It can be found in `target/allure-report/index.html`. I decided to use Allure report due to its ease of installation and integration, stability, popularity, and the possibility to generate reports in different formats.

## CI integration
The tests also run in GitHub Actions, when a Pull Request is created, but they can also be triggered manually, on demand, when needed. The workflow is called `run-tests`. 
The steps that it executes:
1. Checks out the repo
2. Checks out the repo where the app is
3. Installs all the necessary dependencies, such as Node, Java, Chrome
4. Starts a localhost with the app 
5. Runs the tests
6. Generates a report
7. Publish the report as an artifact in GH Actions

## Security
For better security, the sensitive data, such as user's password, is saved in GitHub Actions secrets.
When running the tests locally, you need to have it saved locally as system environment variable. On Mac this can be done by executing the following command:
```
export USER_PASSWORD=*the password*
```
If you want to check if the password was properly saved, execute:
```
echo $USER_PASSWORD
```

## How to run the tests?
You first need to have a localhsot with the app running. Follow the instructions from [this repo](https://github.com/tamara-dimeska/sample-app-web) for this.

After the server is up, the tests can be run by executing the command:
```
mvn test
```
