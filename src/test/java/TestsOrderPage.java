import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.practikum.yandex.ru.model.MainPage;
import ru.practikum.yandex.ru.model.MainOrderPage;


public class TestsOrderPage {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();// TODO Используйте `new FirefoxDriver()` для запуска тестов в Firefox
    }


    @Test
    public void clickOrderButtonNotScroll() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOrderButton();
        MainOrderPage orderPage = new MainOrderPage(driver);
        fillingOrderOne(orderPage);
        orderPage.fillingFieldMetroStation();
        orderPage.clickButtonNext();
        orderPage.fillingFieldWhen("07.02.2023");
        orderPage.fillingFieldRentTerm();
        orderPage.filingFieldColorBlack();
        orderPage.filingFieldColorGrey();
        orderPage.filingFieldComment("Жду самокат!");
        orderPage.clickButtonOrder();
        orderPage.clickButtonConfirm();
        orderPage.checkingOrderCreation();
    }
    private void fillingOrderOne(MainOrderPage page) {
        page.fillingFieldFistName("Вова");
        page.fillingFieldSurname("Пупкин");
        page.fillingFieldAdress("Москва Кремль 77");
        page.fillingFieldPhoneNumber("89617774321");

}

    @Test
    public void clickOrderButtonWithScroll() {
        MainPage page = new MainPage(driver);
        page.open();
        page.clickOrderButtonScroll();
        MainOrderPage orderPage = new MainOrderPage(driver);
        fillingOrderOne(orderPage);
        orderPage.fillingFieldMetroStation();
        orderPage.clickButtonNext();
        orderPage.fillingFieldWhen("31.02.2023");
        orderPage.fillingFieldRentTerm();
        orderPage.filingFieldColorBlack();
        orderPage.filingFieldComment("Хочу самокат!");
        orderPage.clickButtonOrder();
        orderPage.clickButtonConfirm();
        orderPage.checkingOrderCreation();
    }
    @Test
    public void clickOrderButtonNotScrollFillingMandatoryFields() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOrderButton();
        MainOrderPage orderPage = new MainOrderPage(driver);
        orderPage.fillingFieldFistName("Паша");
        orderPage.fillingFieldSurname("Пупкин");
        orderPage.fillingFieldAdress("СПб Фантанка 107");
        orderPage.fillingFieldMetroStation();
        orderPage.fillingFieldPhoneNumber("8961621123222");
        orderPage.clickButtonNext();
        orderPage.fillingFieldWhen("07.02.2023");
        orderPage.fillingFieldRentTerm();
        orderPage.clickButtonOrder();
        orderPage.clickButtonConfirm();
        orderPage.checkingOrderCreation();
    }
    @After
    public void cleanUp(){
        driver.quit();
    }
}
