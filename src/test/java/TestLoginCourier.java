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
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestLoginCourier {

    private static final Logger log = LoggerFactory.getLogger(TestLoginCourier.class);
    private static String createdCourierId;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        Courier courier = new Courier("gusev_ae", "99998998", "Aleksandr");
        // создания курьера, предаем в него параметры выше.
        Response response = given()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @DisplayName("Проверка успешного логина курьера")
    @Test
    public void TestLoginCourierPassed() {
        Response response = given()
            .contentType(ContentType.JSON)
            .body(Map.of("login", "gusev_ae", "password", "99998998"))
            .when()
            .post("/api/v1/courier/login");
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
                .post("/api/v1/courier/login");
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
                .post("/api/v1/courier/login");
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
                .post("/api/v1/courier/login");
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
                .post("/api/v1/courier/login");
        response.then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
    @AfterEach
    public void terDown() {
        if (createdCourierId != null) {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .when().delete("/api/v1/courier/" + createdCourierId);
            response.then().statusCode(200);
        }
    }

}
