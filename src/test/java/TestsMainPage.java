import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.practikum.yandex.ru.model.MainPage;


public class TestsMainPage {
    private WebDriver driver;
    @Before
    public void setUp(){
        driver = new ChromeDriver();
    }

    //Тестирование раздела «Вопросы о важном»/
    @Test
    public void сheckingDropdownListQuestionsAndAnswers(){
        MainPage page = new MainPage(driver);
        page.open();
        page.findAndCheckImportantQuestionsField();
        page.checkingTheDropdownListOfQuestionsAndAnswers();


    }
    @Test // Доп зад. № 4.4 Проверить: если ввести неправильный номер заказа, попадёшь на страницу//
          // статуса заказа. На ней должно быть написано, что такого заказа нет.//
    public void findOrderExpectNotFound() {
        MainPage page = new MainPage(driver);
        page.open();
        page.clickOrderStatusButton();
        page.enterOrderNumber("111111");
        Assert.assertTrue(page.isImageNotFoundDisplayed());
    }


    @After
    public void cleanUp(){
        driver.quit();
    }

}

