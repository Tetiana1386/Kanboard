package ui.tests.users;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import ui.tests.BaseUiTest;

import static utils.MyCustomFaker.getRandomNumber;

public class LoginUiTests extends BaseUiTest {
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    private final static String MAIN_URL = "http://localhost/login";
    private final static String WRONG_URL = "http://localhost/?controller=AuthController&action=check";
    private final static String SUCCESSFUL_MSG = "The login was successful";
    private final static String NOT_SUCCESSFUL_MSG = "The login wasn't successful";
    private String userId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        loginPage.openLoginPage();
    }

    @Test
    @Description("Checking negative case of logging with wrong name and password")
    public void loginWithWrongDataUiTest() {
        loginPage
                .openLoginPage()
                .loginByUser("user", "password");
        assertsLoginWithWrongData();
    }

    @Test
    @Description("Checking negative case of logging with wrong password")
    public void loginWithWrongPasswordUiTest() {
        loginPage
                .openLoginPage()
                .loginByUser(USERNAME, "password!");
        assertsLoginWithWrongData();
    }

    @Test
    @Description("Checking negative case of logging with empty data")
    public void loginWithNotValueUiTest() {
        loginPage
                .openLoginPage()
                .loginByUser("", "");
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), MAIN_URL);
    }

    @Test
    @Description("Checking positive case of logging")
    public void loginWithValidDataUiTest() {
        loginPage
                .openLoginPage()
                .loginByUser(USERNAME, PASSWORD)
                .assertDashboardSectionIsOpened();
        Assert.assertNotEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), MAIN_URL, NOT_SUCCESSFUL_MSG);
    }

    public void assertsLoginWithWrongData() {
        SoftAssert softAssert = new SoftAssert();

        String actualError = loginPage.errorNotification().getText();
        String expectedError = "Bad username or password";

        softAssert.assertEquals(actualError, expectedError, SUCCESSFUL_MSG);
        softAssert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), WRONG_URL);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        userApiSteps.deleteUser(Integer.valueOf(userId));

    }
}
