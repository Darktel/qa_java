import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import praktikum.courier.Courier;
import praktikum.courier.Credentials;

import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestLoginCourier {

    private static final Logger log = LoggerFactory.getLogger(TestLoginCourier.class);
    private static String createdCourierId;
    int rnd = (int) (Math.random() * 1000);
    Courier courier = new Courier("gusev_ae"+rnd, "99998998", "Aleksandr");

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/api/v1";
        // создания курьера, предаем в него параметры выше.
        Courier.createCourier(courier);
    }

    @DisplayName("Проверка успешного логина курьера")
    @Test
    public void TestLoginCourierPassed() {
        var creds = Credentials.from(courier);
        Response response = given()
            .contentType(ContentType.JSON)
            .body(creds)
            .when()
            .post("/courier/login");
        response.then()
                .statusCode(200)
                .body("id", notNullValue());

        createdCourierId = response.jsonPath().getString("id");
    }

    @DisplayName("Проверка логина курьера без пароля")
    @Test
    public void TestLoginCourierWithoutPassword() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(Map.of("login", "gusev_ae"))
                .when()
                .post("/courier/login");
        response.then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @DisplayName("Проверка логина курьера без логина")
    @Test
    public void TestLoginCourierWithoutLogin() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(Map.of("password", "99998998"))
                .when()
                .post("/courier/login");
        response.then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @DisplayName("Проверка логина курьера с неправильным паролем")
    @Test
    public void TestLoginCourierWrongPassword() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(Map.of("login", "gusev_ae", "password", "123"))
                .when()
                .post("/courier/login");
        response.then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @DisplayName("Проверка логина курьера с неправильным паролем")
    @Test
    public void TestLoginCourierWrongLogin() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(Map.of("login", "gusev", "password", "99998998"))
                .when()
                .post("/courier/login");
        response.then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
    @AfterEach
    public void terDown() {
        if (createdCourierId != null) {
            courier.deleteCourier(createdCourierId);
        }
    }

}
