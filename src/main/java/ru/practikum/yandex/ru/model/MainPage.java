package ru.practikum.yandex.ru.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MainPage {

    /*Тестирование раздела «Вопросы о важном»*/
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/"; // Адрес сайта для тестирования
    private static final By STATUS_STATE_BUTTON = By.cssSelector("button.Header_Link__1TAG7"); // Кнопка "Статус заказа"
    private static final By INPUT_ORDER_NUMBER_FIELD = By.xpath("//*[@class=\"Input_Input__1iN_Z Header_Input__xIoUq\"]"); // Поле "Введите номер заказа"
    private static final By GO_BUTTON = By.xpath("//*[@class='Button_Button__ra12g Header_Button__28dPO']"); // Кнопка "Go!"
    private static final By IMG_NOT_FOUND = By.cssSelector("div.Track_NotFound__6oaoY > img"); // Изображение "Такого заказа нет"
    private static final By CLICK_ORDER_BUTTON_NOT_SCROLL = By.cssSelector("button.Button_Button__ra12g"); // Кнопка "Заказать" без скролла (кнопка на верху на против "Статус заказа")
    private static final By CLICK_ORDER_BUTTON_SCROLL = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM'][text()='Заказать']"); // Кнопка "Заказать" cо скроллом (кнопка внизу)


    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(PAGE_URL);
    }

    public String scrollDropdownListOfQuestionAndAnswer(String questionFromDropdownList) {
        WebElement element = driver.findElement(By.xpath("//div[text()='" + questionFromDropdownList + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.xpath("//div[text()='" + questionFromDropdownList + "']")).click();
        return questionFromDropdownList;
    }

    public String checkingTextAnswer(String answerFromDropdownList) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='" + answerFromDropdownList + "']")));
        WebElement element = driver.findElement(By.xpath("//p[text()='" + answerFromDropdownList + "']"));
        assertEquals(answerFromDropdownList, element.getText());
        return answerFromDropdownList;
    }

    public MainPage clickOrderStatusButton() {
        driver.findElement(STATUS_STATE_BUTTON).click();
        return this;
    }

    public MainPage clickOrderButton() {
        driver.findElement(CLICK_ORDER_BUTTON_NOT_SCROLL).click();
        return this;
    }

    public MainPage clickOrderButtonScroll() {
        WebElement element = driver.findElement(CLICK_ORDER_BUTTON_SCROLL);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(CLICK_ORDER_BUTTON_SCROLL));
        driver.findElement(CLICK_ORDER_BUTTON_SCROLL).click();
        return this;
    }

    public MainPage enterOrderNumber(String orderNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(INPUT_ORDER_NUMBER_FIELD));
        driver.findElement(INPUT_ORDER_NUMBER_FIELD).clear();
        driver.findElement(INPUT_ORDER_NUMBER_FIELD).sendKeys(orderNumber);
        driver.findElement(GO_BUTTON).click();
        return this;
    }

    public boolean isImageNotFoundDisplayed() {
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(IMG_NOT_FOUND));
        return driver.findElement(IMG_NOT_FOUND).isDisplayed();
    }
}
