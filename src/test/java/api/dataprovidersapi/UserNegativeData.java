package api.dataprovidersapi;

import org.testng.annotations.DataProvider;

public class UserNegativeData {
    @DataProvider(name = "userNegativeData")
    public static Object[][] userCredentialsDataProvider() {
        return new Object[][]{
                {"", "admin"},
                {"admin", "1315"},
                {"", ""},
                {"admin", "45967833"}
        };
    }
}
