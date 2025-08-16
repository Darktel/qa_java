package ru.praktikumservices.qascooter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;
    // Заголовок "Для кого самокат"
    private final By inputName = By.xpath("//*[@id='root']/div/div[2]/div[1]");
    public final WebDriverWait wait;

    // Кнопка "Далее" \ Кнопка "Заказать"
    private final By buttonNextFormOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // поля формы 1
    // Поле ввода "Имя"
    private final By inputFirstName = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");
    // Поле ввода "Фамилия"
    private final By inputLastName = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/input");
    // Поле ввода "Адрес: куда привести заказ"
    private final By inputAddress = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input");
    // Выпадающий список "Станция метро"
    private final By inputMetroStation = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");
    // Кнопка "Телефон: на него позвонит курьер" //input[@placeholder='* Телефон: на него позвонит курьер']
    private final By inputPhone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // выбор из выпадающего списка
    private final By buttonOrderScooterBottom = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[1]/button");

    // поля формы 2
    // Когда привести самокат
    private final By inputDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Срок аренды
    private final By dropdownPeriod = By.xpath("//div[@class='Dropdown-control']");
    // Чекбокс выбор цвета самоката
    private final By checkboxColorScooterBlack = By.xpath("//label[@for='black']");
    private final By checkboxColorScooterGrey = By.xpath("//label[@for='grey']");
    // Комментарии для курьера
    private final By inputComment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    // Кнопка "Да" - подтверждение заказа.
    private final By buttonOrderYes = By.xpath("//button[contains(text(),'Да')]");
    // Окно подтверждения заказа
//    private final By windowOrderConfirm = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");
    // Окно с номером заказа
    public By windowNumberOrder = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public OrderPage fillFirstName(String firstName) {
        driver.findElement(inputName).click();
        driver.findElement(inputFirstName).sendKeys(firstName);
        return this;
    }

    public OrderPage fillLastName(String lastName) {
        driver.findElement(inputLastName).sendKeys(lastName);
        return this;
    }


   public OrderPage fillAddress(String address)
    {
        driver.findElement(inputAddress).sendKeys(address);
        return this;
    }

    public OrderPage checkMetroStation() {
        driver.findElement(inputMetroStation).click();
        driver.findElement(buttonOrderScooterBottom).click();
        return this;
    }

    public OrderPage fillPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
        return this;
    }

    public OrderPage fillDateOrder(String date) {
        driver.findElement(inputDate).click();
        driver.findElement(inputDate).sendKeys(date);
        driver.findElement(inputDate).sendKeys(Keys.ENTER);
        return this;
    }

    public OrderPage selectPeriod(int period) {
        driver.findElement(dropdownPeriod).click();
        String locatorDaysOrderPeriod = "//div[@class='Dropdown-menu']";
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorDaysOrderPeriod)));
        driver.findElement(By.xpath(locatorDaysOrderPeriod + "/div[" + period + "]")).click();
        return this;
    }

    /**
     * Selects the color of the scooter.
     *
     * @param color the color of the scooter to select. Possible values are "any", "black" and "grey".
     * @return the current instance of the OrderPage class
     */
    public OrderPage selectColorScooter(String color) {
        switch (color) {
            case "any":
                driver.findElement(checkboxColorScooterBlack).click();
                driver.findElement(checkboxColorScooterGrey).click();
                break;
            case "black":
                driver.findElement(checkboxColorScooterBlack).click();
                break;
            case "grey":
                driver.findElement(checkboxColorScooterGrey).click();
                break;
        }
        return this;
    }

    public OrderPage sendTextForCourier(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
        return this;
    }


    public void clickButtonNextFormOrder() {
        driver.findElement(buttonNextFormOrder).click();
    }

//    public WebElement checkWindowOrder() throws InterruptedException {
//        return driver.wait().until(ExpectedConditions.visibilityOfElementLocated(windowNumberOrder));
//    }

    public void clickButtonYes() {
        driver.findElement(buttonOrderYes).click();
    }

    public boolean checkOrderScooterVisible() {
        return driver.findElement(windowNumberOrder).isDisplayed();
    }

    public String checkOrderScooterText() {
        return driver.findElement(windowNumberOrder).getText();
    }

}