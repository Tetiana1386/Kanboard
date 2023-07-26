# <h2 align="center">Tests(UI, API) for the Kanboard App</h3>
---
<div align="center">
  <a href="https://github.com/Tetiana1386/Kanboard">
    <img src="src/images/image_1.png" alt="Kanboard" width="" height="">
  </a>
</div>

## Frameworks & Tools
---

<code><img src="src/images/icons/java.png" alt="html5" height='50px' width='70'/></code>

<code><img src="src/images/icons/maven.png" alt="html5" height='50px' width='70'/></code>
<code><img src="src/images/icons/testng.png" alt="html5" height='50px' width='70'/></code>
<code><img src="src/images/icons/selenide.png" alt="html5" height='50px' width='70'/></code>
<code><img src="src/images/icons/rest-assured.png" alt="html5" height='50px' width='70'/></code>
<code><img src="src/images/icons/Allure.png" alt="html5" height='50px' width='70'/></code>
<code><img src="src/images/icons/Lombok.png" alt="html5" height='50px' width='70'/></code>
<code><img src="src/images/icons/mariadb.png" alt="html5" height='50px' width='70'/></code>
<code><img src="src/images/icons/docker.png" alt="html5" height='50px' width='70'/></code>
<code><img src="src/images/icons/jenkins.png" alt="html5" height='50px' width='75'/></code>

## Getting Started
---

Run your app with Compose:

1. From project directory, start up your application by running:
  ```sh
  $ docker compose up
  ```
2. Enter http://localhost:8080/ in a browser to see the application running.
3. If this doesn’t resolve, you can also try http://127.0.0.1:8080.

## Commands for running tests:
---


- [x] `mvn clean test -Papi_regression` - to run all API tests;


- [x] `mvn clean test -Pui_regression -Dbrowser=chrome -Dheadless=false` - to run all Ui tests in the Chrome browser;


- [x] `mvn clean test -Pui_regression -Dbrowser=firefox -Dheadless=false` - to run all Ui tests in the Firefox browser;


- [x] `mvn clean test -Pui_regression -Dbrowser=chrome -Dheadless=true` - to run all Ui tests in the headless mode Chrome browser;


## Commands for receiving Allure report:
---


- [x] `allure generate target/allure-results`;


- [x] `allure serve target/allure-results`.

## My Allure reports:
---

[//]: # (- [x] [Allure report UI_Tests]&#40;https://github.com/Tetiana1386/Kanboard/blob/main/src/images/screenshots/AR_UI_TESTS.png&#41;)

[//]: # ()
[//]: # (- [x] [Allure report API_Tests]&#40;https://github.com/Tetiana1386/Kanboard/blob/main/src/images/screenshots/AR_API_TESTS.png&#41;)

[//]: # ()
[//]: # (- [x] [Allure report DB_Tests]&#40;https://github.com/Tetiana1386/Kanboard/blob/main/src/images/screenshots/AR_DB_TESTS.png&#41;)
