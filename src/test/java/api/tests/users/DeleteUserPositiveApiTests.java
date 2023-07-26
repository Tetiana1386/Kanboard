package api.tests.users;

import api.steps.UserApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.MyCustomFaker.getRandomNumber;

public class DeleteUserPositiveApiTests {
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
    @Description("Checking positive case of deleting user")
    public void deleteUserPositiveApiTest() {
        boolean result = userApiSteps.deleteUser(Integer.valueOf(userId));
        Assert.assertTrue(Boolean.valueOf(result), "The user has not been deleted");
    }
}
