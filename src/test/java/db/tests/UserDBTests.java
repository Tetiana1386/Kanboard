package db.tests;

import db.controllers.UserDBController;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.enums.UserRoles.ADMIN;
import static utils.MyCustomFaker.getRandomNumber;

public class UserDBTests {
    private static final String USERNAME = "USER" + getRandomNumber();
    private static final String PASSWORD = "password" + getRandomNumber();
    private Integer userId;

    @BeforeMethod
    @Description("Setup test data")
    public void prepareDataForTest() {
        UserDBController.insertUserDB(USERNAME, PASSWORD);
        userId = UserDBController.getUserByNameDB(USERNAME).getId();
        System.out.println(userId);
    }

    @Test
    public void testUpdateUserDB() {
        UserDBController.updateUserDB(userId, ADMIN.getRole());
        String userRole = UserDBController.getUserByIdDB(userId).getRole();
        Assert.assertEquals(userRole, ADMIN.getRole(), "The user's role has not been changed");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Cleaning up test data")
    public void removeDataAfterTest() {
        UserDBController.deleteUserDB(userId);
    }
}
