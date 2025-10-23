package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.notNullValue;

public class OrderChecks {

    @Step("Проверка корректности создания заказа")
    public void assertSuccessCreateOrder(Response response) {
        response.then()
                .statusCode(201)
                .body("track", notNullValue());
    }

    @Step("Проверка корректности получения списка заказов")
    public void assertSuccessGetOrderList(Response response) {
        response.then()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
