package api.tests.users;

import api.dataprovidersapi.UserNegativeData;
import api.steps.UserApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserNegativeApiTests {
    UserApiSteps userApiSteps = new UserApiSteps();
    private String userId;

    @Test(dataProvider = "userNegativeData", dataProviderClass = UserNegativeData.class)
    @Description("Checking negative case of creating user")
    public void createUserWithWrongDataApiTest(String userName, String password) {
        userId = userApiSteps.createUser(userName, password);

        Assert.assertFalse(Boolean.valueOf(userId), "User is created");
    }
}
