package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class OrderCardDeliveryTest {
    public static String setLocalDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
                new Locale("ru")));
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.baseUrl = "http://localhost:9999";
        Configuration.browserSize = "1366x768";
        Configuration.timeout = 15000;
        Configuration.browserCapabilities = new org.openqa.selenium.chrome.ChromeOptions()
                .addArguments("--remote-allow-origins=*");
    }

    @DisplayName("Successful submission with 3 days delivery")
    @Test
    void shouldSubmitValidData3() {
        open();
        String date = setLocalDate(3);
        $("[data-test-id=city] .input__control").setValue("Ярославль");
        $("[data-test-id = date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id = name] .input__control").setValue("Ирина Ким");
        $("[data-test-id = phone] .input__control").setValue("+79600000000");
        $("[data-test-id = agreement]").click();
        $(".button").click();
        $("[data-test-id = notification]").shouldHave(text("Успешно!"),
                Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    // Аналогично для остальных тестов
}
