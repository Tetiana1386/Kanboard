package utils;

import net.datafaker.Faker;

public class MyCustomFaker {
    public static int getRandomNumber() {
        Faker faker = new Faker();
        int randomNumber = faker.number().numberBetween(10, 5000);
        return randomNumber;
    }
}
