import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.practikum.yandex.ru.model.MainPage;

public class TestOrderNotFound {
    private final String orderNumberFailed = "111111";
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test // Доп зад. № 4.4 Проверить: если ввести неправильный номер заказа, попадёшь на страницу//
    // статуса заказа. На ней должно быть написано, что такого заказа нет.//

    public void findOrderExpectNotFound() {
        MainPage page = new MainPage(driver);
        page.open();
        page.clickOrderStatusButton();
        page.enterOrderNumber(orderNumberFailed);
        Assert.assertTrue(page.isImageNotFoundDisplayed());
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
