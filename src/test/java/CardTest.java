import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    public String generateDate(int days) {
        return
                LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void happyDelivery() {
        Configuration.holdBrowserOpen = true;

        String planningDate = generateDate(5);

        open("http://localhost:9999");
        $("[data-test-id ='city'] input").val("Казань");
        $("[data-test-id = 'name'] input").val("Ксюшенька");
        $("[data-test-id = 'phone'] input").val("+79995558978");
        $("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = 'date'] input").val(planningDate);
        $("[data-test-id = 'agreement']").click();
        $x("//span[@class='button__text']").click();
        $("[data-test-id = 'notification']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

}

