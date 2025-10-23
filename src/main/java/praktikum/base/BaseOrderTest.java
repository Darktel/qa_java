package praktikum.base;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import praktikum.models.Order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class BaseOrderTest {

    private final BaseHttpClient baseHttpClient = new BaseHttpClient();
    @Step("Создание заказа")
    public Response createOrder(Order orderData) {
        return given()
                .spec(baseHttpClient.requestSpecification)
                .body(orderData)
                .when()
                .post("/orders");
    }

    @Step("Проверка корректности создания заказа")
    public void assertSuccessCreateOrder(Response response) {
        response.then()
                .statusCode(201)
                .body("track", notNullValue());
    }

    @Step("Получение списка заказов")
    public Response getOrderList() {
        return given()
                .spec(baseHttpClient.requestSpecification)
                .when()
                .get("/orders");
    }

    @Step("Проверка корректности получения списка заказов")
    public void assertSuccessGetOrderList(Response response) {
        response.then()
                .statusCode(200)
                .body("orders", notNullValue());
    }

}
