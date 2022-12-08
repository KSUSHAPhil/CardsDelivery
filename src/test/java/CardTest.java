import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {
    @Test
    void Testik() {
        Configuration.holdBrowserOpen = true;

        open("http://localhost:9999");
        $("[data-test-id ='city'] input").val("Казань");
        $("[data-test-id = 'name'] input").val("Ксюшенька");
        $("[data-test-id = 'phone'] input").val("+79995558978");
        $("[data-test-id = 'agreement']").click();
        $x("//span[@class='button__text']").click();
        $("[data-test-id = 'notification']").shouldBe(visible, Duration.ofSeconds(15));
    }

}

