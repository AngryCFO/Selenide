package ru.netology;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

   // @BeforeAll
    //public static void setUp() {
        //WebDriverManager.chromedriver().setup(); // Автоматически подбирает нужную версию ChromeDriver
    //    WebDriverManager.chromedriver().driverVersion("129.0.6668.90").setup();
    //    Configuration.browser = "chrome";
     //   Configuration.headless = true; // Запуск в headless режиме
   // }
    @BeforeEach
    void setupTest() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitValidData3() {
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

    @Test
    void shouldSubmitValidData4() {
        String date = setLocalDate(4);
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

    @Test
    void shouldSubmitValidDataYear() {
        String date = setLocalDate(365);
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
    @Test
    void shouldSubmitNotValidData2() {
        String date = setLocalDate(2);
        $("[data-test-id=city] .input__control").setValue("Ярославль");
        $("[data-test-id = date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id = name] .input__control").setValue("Ирина Ким");
        $("[data-test-id = phone] .input__control").setValue("+79600000000");
        $("[data-test-id = agreement]").click();
        $(".button").click();
        $(withText("невозможен")).should(visible, Duration.ofSeconds(5));
        $x("//*[@data-test-id=\"notification\"]").shouldNot(visible, Duration.ofSeconds(10));
    }

    @Test
    void shouldSubmitNotValidDataToday() {
        String date = setLocalDate(0);
        $("[data-test-id=city] .input__control").setValue("Ярославль");
        $("[data-test-id = date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id = name] .input__control").setValue("Ирина Ким");
        $("[data-test-id = phone] .input__control").setValue("+79600000000");
        $("[data-test-id = agreement]").click();
        $(".button").click();
        $(withText("невозможен")).should(visible, Duration.ofSeconds(5));
        $x("//*[@data-test-id=\"notification\"]").shouldNot(visible, Duration.ofSeconds(10));
    }

    @Test
    void shouldSubmitNotValidCity() {
        String date = setLocalDate(3);
        $("[data-test-id=city] .input__control").setValue("Рыбинск");
        $("[data-test-id = date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id = name] .input__control").setValue("Ирина Ким");
        $("[data-test-id = phone] .input__control").setValue("+79600000000");
        $("[data-test-id = agreement]").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid").shouldBe(visible, Duration.ofSeconds(5))
                .should(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldSubmitNotValidCityEnglish() {
        String date = setLocalDate(3);
        $("[data-test-id=city] .input__control").setValue("Yaroslavl");
        $("[data-test-id = date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id = name] .input__control").setValue("Ирина Ким");
        $("[data-test-id = phone] .input__control").setValue("+79600000000");
        $("[data-test-id = agreement]").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid").shouldBe(visible, Duration.ofSeconds(5))
                .should(exactText("Доставка в выбранный город недоступна"));
    }
// Аналогично все остальные тесты для ФИО, телефона
}
