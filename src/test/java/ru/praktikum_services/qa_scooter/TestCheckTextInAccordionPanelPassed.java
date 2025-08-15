package ru.praktikum_services.qa_scooter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCheckTextInAccordionPanelPassed {

    private final DriverFactory driverFactory = new DriverFactory();

    @BeforeEach
    public void initDriver() {
        driverFactory.initDriver();
        String url = "https://qa-scooter.praktikum-services.ru/";
        driverFactory.getDriver().get(url);
    }


    private static java.util.stream.Stream<Arguments> textInAccordionPanel() {
        return java.util.stream.Stream.of(
                Arguments.of( "//*[@id='accordion__heading-0']", "//*[@id='accordion__panel-0']/p", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of( "//*[@id='accordion__heading-1']", "//*[@id='accordion__panel-1']/p", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.of( "//*[@id='accordion__heading-2']", "//*[@id='accordion__panel-2']/p", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.of( "//*[@id='accordion__heading-3']", "//*[@id='accordion__panel-3']/p", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of( "//*[@id='accordion__heading-4']", "//*[@id='accordion__panel-4']/p", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.of( "//*[@id='accordion__heading-5']", "//*[@id='accordion__panel-5']/p", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.of( "//*[@id='accordion__heading-6']", "//*[@id='accordion__panel-6']/p", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.of( "//*[@id='accordion__heading-7']", "//*[@id='accordion__panel-7']/p", "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @ParameterizedTest
    @MethodSource("textInAccordionPanel")
    public void testCheckTextInAccordionPanelPassed(String locator_heading, String locator_paragraph, String text)  {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToAccordion();
        driver.findElement(By.xpath(locator_heading)).click();
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator_paragraph)));
        assert driver.findElement(By.xpath(locator_paragraph)).isDisplayed();
        assertEquals(text, driver.findElement(By.xpath(locator_paragraph)).getText());

    }

        @AfterEach
        public void tearDown() {
            driverFactory.getDriver().quit();
        }
}
