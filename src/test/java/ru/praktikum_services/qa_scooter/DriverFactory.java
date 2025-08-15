package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void initDriver() {
        if ("chrome".equals(System.getProperty("browser"))) {
            SetupChrome();
        }
        else {
            SetupFirefox();
        }
    }

    public void SetupFirefox() {
        driver = new org.openqa.selenium.firefox.FirefoxDriver();
        driver.manage().window().maximize();
    }

    public void SetupChrome() {
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        driver.manage().window().maximize();
    }
}
