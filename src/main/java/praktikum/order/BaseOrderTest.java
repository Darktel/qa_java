package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class BaseOrderTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/api/v1";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Step("Создание заказа")
    public Response createOrder(Order orderData) {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
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
