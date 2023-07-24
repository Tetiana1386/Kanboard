package api.tests.users;

import api.steps.UserApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static utils.MyCustomFaker.getRandomNumber;

public class CreateUserPositiveApiTests {
    UserApiSteps userApiSteps = new UserApiSteps();
    private static final String USERNAME = "User" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    private String userId;
    @Test
    @Description("Checking positive case of creating user")
    public void createUserWithValidDataApiTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
        System.out.println(userId);

        Assert.assertNotNull(userId, "User isn't created");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest(){
        userApiSteps.deleteUser(Integer.valueOf(userId));

    }
}
