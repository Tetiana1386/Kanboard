package api.tests.users;

import api.steps.UserApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static api.enums.UserRoles.MANAGER;
import static utils.MyCustomFaker.getRandomNumber;

public class UpdateUserApiTests {
    UserApiSteps userApiSteps = new UserApiSteps();
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    private String userId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
    }

    @Test
    @Description("Checking positive case of updating a user' role")
    public void updateUserPositiveApiTest() {
        boolean result = userApiSteps.updateUserWithRequiredParameters(Integer.valueOf(userId), MANAGER.getRole());
        Assert.assertTrue(result, "The user's role isn't updated");
    }

    @Test
    @Description("Checking negative case of updating a user's role")
    public void updateUserNegativeApiTest() {
        boolean result = userApiSteps.updateUserWithoutRequiredParameters(MANAGER.getRole());
        Assert.assertFalse(result, "The user's role is updated");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        userApiSteps.deleteUser(Integer.valueOf(userId));

    }
}
