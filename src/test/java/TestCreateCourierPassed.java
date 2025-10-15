import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.courier.Courier;
import java.io.File;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCreateCourierPassed {


    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @DisplayName("Проверка создания курьера")
    @Test
    public void TestCreateNewCourierPassed() {
        var rnd = (int) (Math.random() * 1000);
        var courier = new Courier("ninja97795"+rnd,  "1234", "saske99998");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @DisplayName("Проверка создания не уникального курьера")
    @Description("нельзя создать двух одинаковых курьеров")
    @Test
    public void TestCreateNewCourierNotUnique() {
        File json = new File("src/test/resources/newCourierJson.json");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post("/api/v1/courier");
        response.then()
                .extract()
                .response();

        assertEquals(409, response.getStatusCode(), "Не верный код ответа");
        assertEquals("Этот логин уже используется", response.body().jsonPath().get("message"),
                "Не верное сообщение об ошибке");
    }


    @DisplayName("Проверка создания курьера с незаполненным полем login")
    @Description("Проверка, что для создать курьера в системе не получится если требуется заполнены все обязательные поля.")
    @Test
    public void TestCreateNewCourierWithoutLoginFields() {

        Map<String, String> json = Map.of("password", "1234", "firstName", "saske");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post("/api/v1/courier");
        response.then()
                .extract()
                .response();

        assertEquals(400, response.getStatusCode(), "Не верный код ответа");
        assertEquals("Недостаточно данных для создания учетной записи", response.body().jsonPath().get("message"), "Не верное сообщение об ошибке");

    }

    @DisplayName("Проверка создания курьера с незаполненным полем password")
    @Description("Проверка, что для создать курьера в системе не получится если требуется заполнены все обязательные поля.")
    @Test
    public void TestCreateNewCourierWithoutPasswordFields() {

        Map<String, String> json = Map.of("login", "ninja7795", "firstName", "saske");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post("/api/v1/courier");
        response.then()
                .extract()
                .response();

        assertEquals(400, response.getStatusCode(), "Не верный код ответа");
        assertEquals("Недостаточно данных для создания учетной записи", response.body().jsonPath().get("message"), "Не верное сообщение об ошибке");

    }

    @DisplayName("Проверка создания курьера с незаполненным полем firstName")
    @Description("Проверка, что для создать курьера в системе не получится если требуется заполнены все обязательные поля.")
    @Test
    public void TestCreateNewCourierWithoutFirstNameFields() {

        var json = Map.of("login", "ninja97795", "password", "1234");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post("/api/v1/courier");
        response.then()
                .extract()
                .response();

        assertEquals(400, response.getStatusCode(), "Не верный код ответа");
        assertEquals("Недостаточно данных для создания учетной записи",
                response.body().jsonPath().get("message"), "Не верное сообщение об ошибке");

    }


}
