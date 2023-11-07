package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));
    private static Random random = new Random();
    private static String[] cities = {
            "Москва",
            "Санкт-Петербург",
            "Казань",
            "Екатеринбург",
            "Тюмень",
            "Иркутск",
            "Новосибирск",
            "Омск",
            "Владивосток",
            "Нижний Новгород"
    };

    private DataGenerator() {
    }

    public static String generateCity() {
        int index = random.nextInt(cities.length);
        return cities[index];
    }

    public static String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generateFullName() {

        return faker.name().fullName();
    }

    public static String generatePhoneNumber() {

        return faker.numerify("+7 (###) ### ## ##");
    }

    @Value //рекомендуется аннотировать Value
    public static class UserInfo { //дата класс - специальный класс, который служит для задания структуры объекта с данными, т.е. описываются те поля, которые должны быть в объекте
        private String city;
        private String name;
        private String phone;
    }
}