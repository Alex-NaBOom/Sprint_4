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
    private static final By SECTION_IMPORTANT_QUESTIONS = By.xpath("//div[@class = 'Home_FourPart__1uthg']/div[@class = 'Home_SubHeader__zwi_E']"); // Раздел "Вопросы о важном"

    private static final By CLICK_ORDER_BUTTON_NOT_SCROLL = By.cssSelector("button.Button_Button__ra12g"); // Кнопка "Заказать" без скролла (кнопка на верху на против "Статус заказа")
    private static final By CLICK_ORDER_BUTTON_SCROLL = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM'][text()='Заказать']"); // Кнопка "Заказать" cо скроллом (кнопка внизу)


    private final WebDriver driver;

   public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(PAGE_URL);
    }

    public void findAndCheckImportantQuestionsField() {

        WebElement element = driver.findElement(SECTION_IMPORTANT_QUESTIONS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        String questionsAboutImportant = "Вопросы о важном";
        assertEquals(questionsAboutImportant, element.getText()); // проверку текста
    }

    
    public void checkingTheDropdownListOfQuestionsAndAnswers() {

        By[] questions = new By[8];  // На кнопки выпадающего списока в разделе «Вопросы о важном»
        questions[0] = By.xpath("//div[text()='Сколько это стоит? И как оплатить?']");
        questions[1] = By.xpath("//div[text()='Хочу сразу несколько самокатов! Так можно?']");
        questions[2] = By.xpath("//div[text()='Как рассчитывается время аренды?']");
        questions[3] = By.xpath("//div[text()='Можно ли заказать самокат прямо на сегодня?']");
        questions[4] = By.xpath("//div[text()='Можно ли продлить заказ или вернуть самокат раньше?']");
        questions[5] = By.xpath("//div[text()='Вы привозите зарядку вместе с самокатом?']");
        questions[6] = By.xpath("//div[text()='Можно ли отменить заказ?']");
        questions[7] = By.xpath("//div[text()='Я жизу за МКАДом, привезёте?']");

        String[] writtenText = new String[8]; // вводимый верный текст для сверки
        writtenText[0] = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        writtenText[1] = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
        writtenText[2] = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
        writtenText[3] = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
        writtenText[4] = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
        writtenText[5] = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
        writtenText[6] = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
        writtenText[7] = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

        By[] texts = new By[8]; // Локаторы на ответы из выпадающего списока в разделе «Вопросы о важном»
        texts[0] = By.cssSelector("#accordion__panel-0 > p");
        texts[1] = By.cssSelector("#accordion__panel-1 > p");
        texts[2] = By.cssSelector("#accordion__panel-2 > p");
        texts[3] = By.cssSelector("#accordion__panel-3 > p");
        texts[4] = By.cssSelector("#accordion__panel-4 > p");
        texts[5] = By.cssSelector("#accordion__panel-5 > p");
        texts[6] = By.cssSelector("#accordion__panel-6 > p");
        texts[7] = By.cssSelector("#accordion__panel-7 > p");

        for (int i = 0; i < writtenText.length; i++) {
            WebElement element1 = driver.findElement(questions[i]);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element1);
            driver.findElement(questions[i]).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.elementToBeClickable(texts[i]));
            WebElement element = driver.findElement(texts[i]);
            assertEquals(writtenText[i], element.getText());
        }

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
