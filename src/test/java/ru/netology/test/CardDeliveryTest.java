package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardDeliveryTest {
    @BeforeEach
    void SetUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldTest() {
        String planningDate = generateDate(5, "dd.MM.yyyy");
        SelenideElement form = $(".form");
        var validUser = DataGenerator.Registration.generateUser("ru");
        var days = 10;
        var firstDate = DataGenerator.generateDate(days);
        
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(planningDate);
        $("[data-test-id=name] input").setValue("Бобровский Павель");
        $("[data-test-id=phone] input").setValue("+79222141414");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(Condition.hidden);
        $("[data-test-id='success-notification'] .notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(11));
    }
}
