package api.tests.users;

import api.models.BodyResult;
import api.models.users.UserProperties;
import api.steps.UserApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static utils.MyCustomFaker.getRandomNumber;

public class GetUserApiTests {
    UserApiSteps userApiSteps = new UserApiSteps();
    private BodyResult<UserProperties> userProperties;
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    private String userId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
    }

    @Test
    @Description("Checking negative case of getting a user")
    public void getUserNegativeApiTest() {
        userProperties = userApiSteps.getUserById(Integer.valueOf(userId + userId));
        Assert.assertNull(userProperties.getResult(), "Found a user with some data");
    }

    @Test
    @Description("Checking positive case of getting a user")
    public void getUserPositiveApiTest() {
        userProperties = userApiSteps.getUserById(Integer.valueOf(userId));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userProperties.getResult().getId(), Integer.valueOf(userId), "No user found with this id");
        softAssert.assertEquals(userProperties.getResult().getName(), USERNAME, "No user found with this name");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest(){
        userApiSteps.deleteUser(Integer.valueOf(userId));

    }
}
