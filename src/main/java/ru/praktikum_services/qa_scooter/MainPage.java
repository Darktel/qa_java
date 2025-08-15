package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class MainPage {
    private static WebDriver driver;
    private final By accordion = By.xpath("//*[@id='root']/div/div[1]/div[5]/div[2]");

    private final By buttonOrderScooterTop = By.xpath("//button[@class='Button_Button__ra12g']");
    private final By buttonOrderScooterMiddle = By.xpath("//button[@class='Button_Button__ra12g']");



    public MainPage() {

    }


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonOrderScooter(By buttonOrder) {
        driver.findElement(buttonOrder).click();
    }

    public void scrollToAccordion() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(this.accordion));

    }

    public  By getButtonOrderScooterTop() {
        return this.buttonOrderScooterTop;
    }

    public  By getButtonOrderScooterMiddle() {
        return this.buttonOrderScooterMiddle;
    }
}
