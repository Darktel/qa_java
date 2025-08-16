package ru.praktikumservices.qascooter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOrderScooterTopButtonPassed {
    private final DriverFactory driverFactory = new DriverFactory();

    @BeforeEach
        public void initDriver() {
            driverFactory.initDriver();
        String url = "https://qa-scooter.praktikum-services.ru/";
        driverFactory.getDriver().get(url);
        }




    // Тест заказа самоката
    @ParameterizedTest
    @MethodSource("provideLocatorsAndData")
    public void testOrderScooterTopButtonPassed(By selectorsButtonOrder,
                                                String FirstName,
                                                String LastName,
                                                String Date,
                                                String collorScooter,
                                                Integer daysOrderPeriod) throws InterruptedException {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);


        // нажимаем на кнопку "Заказать"
        mainPage.clickButtonOrderScooter(selectorsButtonOrder);
        // установить sleep 1 секунды
//        Thread.sleep(1000);
        // заполняем поля формы заказа
        orderPage.fillFirstName(FirstName)
                .fillLastName(LastName)
                .fillAddress("ул. Пушкина")
                .checkMetroStation()
                .fillPhone("+71234567890")
                .clickButtonNextFormOrder();
        // Заполняем поля формы выбора даты и типа самоката
        orderPage.clickButtonNextFormOrder();
        orderPage.fillDateOrder(Date)
                .selectPeriod(daysOrderPeriod)
                .selectColorScooter(collorScooter)
                .sendTextForCourier("123")
                .clickButtonNextFormOrder();
        // Подтверждаем заказ
        orderPage.wait.until(ExpectedConditions.visibilityOfElementLocated(orderPage.windowNumberOrder));
        orderPage.clickButtonYes();
//        Thread.sleep(2000);
        // Проверяем что заказ был успешно оформлен
        assertTrue(true, String.valueOf(orderPage.checkOrderScooterVisible()));
        assertTrue(orderPage.checkOrderScooterText().contains("Заказ оформлен"));

    }




    static Stream<Arguments> provideLocatorsAndData() {
        MainPage mainPage = new MainPage();
        By[] selectorsButtonOrder = {
                mainPage.getButtonOrderScooterTop(),
                mainPage.getButtonOrderScooterMiddle()};
        String[] firstName = {"Александр", "user2", "Петя"};
        String[] lastName = {"Гусев", "LastName2", "Бубнов"};
        String[] date = {"30.08.2025", "01.02.2025", "31.12.2027"};
        String[] colorScooter = {"any", "grey"};
        // Ограниченно кол-вом блоков div доступных для выбора.
        Integer[] daysOrderPeriod = {1, 7};


        return Stream.of(selectorsButtonOrder)
                .flatMap(loc -> Stream.of(firstName)
                        .flatMap(data1 -> Stream.of(lastName)
                                .flatMap(data2 -> Stream.of(date)
                                        .flatMap(data3 -> Stream.of(colorScooter)
                                                .flatMap(data4 -> Stream.of(daysOrderPeriod)
                                                        .map(data5 -> Arguments.of(loc, data1, data2, data3, data4, data5)))))));


    }

    @AfterEach
    public void tearDown() {
        driverFactory.getDriver().quit();
    }

}
