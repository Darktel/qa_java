package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.base.BaseHttpClient;
import praktikum.models.Order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderClient {

    private final BaseHttpClient baseHttpClient = new BaseHttpClient();
    @Step("Создание заказа")
    public Response createOrder(Order orderData) {
        return given()
                .spec(baseHttpClient.requestSpecification)
                .body(orderData)
                .when()
                .post("/orders");
    }

    @Step("Получение списка заказов")
    public Response getOrderList() {
        return given()
                .spec(baseHttpClient.requestSpecification)
                .when()
                .get("/orders");
    }


}
