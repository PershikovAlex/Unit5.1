package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardDeliveryTest {
    @BeforeEach
    void SetUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldTest() {
        String city = DataGenerator.generateCity();
        String firstDate = DataGenerator.generateDate(5, "dd.MM.yyyy");
        String secondDate = DataGenerator.generateDate(7, "dd.MM.yyyy");
        String fullName = DataGenerator.generateFullName();
        String phoneNumber = DataGenerator.generatePhoneNumber();

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").doubleClick().sendKeys(firstDate);
        $("[data-test-id=name] input").setValue(fullName);
        $("[data-test-id=phone] input").setValue(phoneNumber);
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".notification__content").shouldBe(Condition.hidden);
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + firstDate));
        $(".button").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(secondDate);
        $(".button").click();
        $(".button__text").click();
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondDate));
    }
}
