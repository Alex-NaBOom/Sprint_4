import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.practikum.yandex.ru.model.MainOrderPage;
import ru.practikum.yandex.ru.model.MainPage;

@RunWith(Parameterized.class)
public class TestsOrderPage {
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String when;
    private final String textCommets;
    private WebDriver driver;

    public TestsOrderPage(String name, String surname, String address, String phoneNumber, String when, String textCommets) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.when = when;
        this.textCommets = textCommets;
    }

    @Parameterized.Parameters
    public static Object[][] getInputOrder() {
        return new Object[][]{
                {"Вова", "Пупкин", "Москва Кремль 77", "89617774321", "10.02.2023", "Жду самокат!"},
                {"Иван", "Иванович", "СПб Фантанка 107", "8961621123222", "21.02.2023", ""},
        };
    }

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
        orderPage.fillingFieldFistName(name);
        orderPage.fillingFieldSurname(surname);
        orderPage.fillingFieldAdress(address);
        orderPage.fillingFieldPhoneNumber(phoneNumber);
        orderPage.fillingFieldMetroStation();
        orderPage.clickButtonNext();
        orderPage.fillingFieldWhen(when);
        orderPage.fillingFieldRentTerm();
        orderPage.filingFieldColorBlack();
        orderPage.filingFieldColorGrey();
        orderPage.filingFieldComment(textCommets);
        orderPage.clickButtonOrder();
        orderPage.clickButtonConfirm();
        orderPage.checkingOrderCreation();
    }

    @Test
    public void clickOrderButtonWithScroll() {
        MainPage page = new MainPage(driver);
        page.open();
        page.clickOrderButtonScroll();
        MainOrderPage orderPage = new MainOrderPage(driver);
        orderPage.fillingFieldFistName(name);
        orderPage.fillingFieldSurname(surname);
        orderPage.fillingFieldAdress(address);
        orderPage.fillingFieldPhoneNumber(phoneNumber);
        orderPage.fillingFieldMetroStation();
        orderPage.clickButtonNext();
        orderPage.fillingFieldWhen(when);
        orderPage.fillingFieldRentTerm();
        orderPage.filingFieldColorBlack();
        orderPage.filingFieldComment(textCommets);
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
        orderPage.fillingFieldFistName(name);
        orderPage.fillingFieldSurname(surname);
        orderPage.fillingFieldAdress(address);
        orderPage.fillingFieldPhoneNumber(phoneNumber);
        orderPage.fillingFieldMetroStation();
        orderPage.clickButtonNext();
        orderPage.fillingFieldWhen(when);
        orderPage.fillingFieldRentTerm();
        orderPage.filingFieldColorGrey();
        orderPage.filingFieldComment(textCommets);
        orderPage.clickButtonOrder();
        orderPage.clickButtonConfirm();
        orderPage.checkingOrderCreation();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
