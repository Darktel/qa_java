package ru.praktikumservices.qascooter;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;


public class DriverExtension implements BeforeEachCallback, AfterEachCallback {
    private final DriverFactory driverFactory = new DriverFactory();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        driverFactory.initDriver();
    }


    @Override
    public void afterEach(ExtensionContext context) {
        driverFactory.getDriver().quit();
    }

    public WebDriver getDriver() {
        return driverFactory.getDriver();
    }
}
