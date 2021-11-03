package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


class CardOrderTest {

    String generateDate(int date) {
        return LocalDate.now().plusDays(date).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void cardWithDelivery() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(generateDate(4));
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText("Встреча успешно забронирована")).shouldHave(text(generateDate(4)));
    }
}
