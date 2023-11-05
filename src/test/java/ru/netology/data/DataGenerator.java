package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator(){}

    public static String generateName(String locale){
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateCity() {
        var cities = new String[] {"Москва", "Санкт-Петербург", "Екатеринбург", "Казань", "Тюмень", "Иркутск"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateDate(int addDays) { // статичный метод
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    public static class Registration {
        private Registration() {
        }
        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateName(locale), generatePhone(locale), generateCity());
        }
    }

    @Value
    public static class UserInfo {
        String name;
        String phone;
        String city;
    }
}
