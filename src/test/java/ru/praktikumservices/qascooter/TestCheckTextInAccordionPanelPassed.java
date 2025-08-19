package ru.praktikumservices.qascooter;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCheckTextInAccordionPanelPassed {

@RegisterExtension
    private DriverExtension driverExtension = new DriverExtension();



    private static java.util.stream.Stream<Arguments> textInAccordionPanel() {
        MainPage mainPage = new MainPage();
        return java.util.stream.Stream.of(
                Arguments.of( mainPage.accordionElement1, mainPage.accordionElement1Paragraph, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of( mainPage.accordionElement2, mainPage.accordionElement2Paragraph, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.of( mainPage.accordionElement3, mainPage.accordionElement3Paragraph, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.of( mainPage.accordionElement4, mainPage.accordionElement4Paragraph, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of( mainPage.accordionElement5, mainPage.accordionElement5Paragraph, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.of( mainPage.accordionElement6, mainPage.accordionElement6Paragraph, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.of( mainPage.accordionElement7, mainPage.accordionElement7Paragraph, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.of( mainPage.accordionElement8, mainPage.accordionElement8Paragraph, "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @ParameterizedTest
    @MethodSource("textInAccordionPanel")
    public void testCheckTextInAccordionPanelPassed(By locator_heading, By locator_paragraph, String text)  {
        String url = "https://qa-scooter.praktikum-services.ru/";
        WebDriver driver = driverExtension.getDriver();
        driver.get(url);
        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToAccordion();
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOfElementLocated(locator_heading));
        driver.findElement(locator_heading).click();
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOfElementLocated(locator_paragraph));
        assert driver.findElement(locator_paragraph).isDisplayed();
        assertEquals(text, driver.findElement(locator_paragraph).getText());

    }


}
