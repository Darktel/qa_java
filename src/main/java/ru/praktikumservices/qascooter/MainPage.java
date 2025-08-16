package ru.praktikumservices.qascooter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static WebDriver driver;
    private final By accordion = By.xpath("//div[@class='Home_FAQ__3uVm4']");

    private final By buttonOrderScooterTop = By.xpath("//button[@class='Button_Button__ra12g']");
    private final By buttonOrderScooterMiddle = By.xpath("//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']");
    private final WebDriverWait wait;


    public MainPage() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void clickButtonOrderScooter(By buttonOrder) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(buttonOrder));
        this.wait.until(ExpectedConditions.elementToBeClickable(buttonOrder));
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
