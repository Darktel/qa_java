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
    final By accordionElement1 = By.xpath("//*[@id='accordion__heading-0']");
    final By accordionElement1Paragraph = By.xpath("//*[@id='accordion__panel-0']/p");
    final By accordionElement2 = By.xpath(  "//*[@id='accordion__heading-1']");
    final By accordionElement2Paragraph = By.xpath("//*[@id='accordion__panel-1']/p");
    final By accordionElement3 = By.xpath( "//*[@id='accordion__heading-2']");
    final By accordionElement3Paragraph = By.xpath("//*[@id='accordion__panel-2']/p");
    final By accordionElement4 = By.xpath( "//*[@id='accordion__heading-3']");
    final By accordionElement4Paragraph = By.xpath("//*[@id='accordion__panel-3']/p");
    final By accordionElement5 = By.xpath( "//*[@id='accordion__heading-4']");
    final By accordionElement5Paragraph = By.xpath("//*[@id='accordion__panel-4']/p");
    final By accordionElement6 = By.xpath( "//*[@id='accordion__heading-5']");
    final By accordionElement6Paragraph = By.xpath("//*[@id='accordion__panel-5']/p");
    final By accordionElement7 = By.xpath( "//*[@id='accordion__heading-6']");
    final By accordionElement7Paragraph = By.xpath("//*[@id='accordion__panel-6']/p");
    final By accordionElement8 = By.xpath( "//*[@id='accordion__heading-7']");
    final By accordionElement8Paragraph = By.xpath("//*[@id='accordion__panel-7']/p");



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
