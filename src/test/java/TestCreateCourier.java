import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.courier.*;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCreateCourier {
    int rnd = (int) (Math.random() * 1000);
    private Courier courier = new Courier("ninja97795"+rnd,  "1234", "saske99998"); // Поле класса

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/api/v1";

    }

    @DisplayName("Проверка создания курьера")
    @Test
    public void TestCreateNewCourierPassed() {
        Response response = Courier.createCourier(courier);
        response.then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @DisplayName("Проверка создания не уникального курьера")
    @Description("Нельзя создать двух одинаковых курьеров")
    @Test
    public void TestCreateNewCourierNotUnique() {
        Courier.createCourier(courier);
        Response response = Courier.createCourier(courier);

        assertEquals(409, response.getStatusCode(), "Не верный код ответа");
        assertEquals("Этот логин уже используется", response.body().jsonPath().get("message"),
                "Не верное сообщение об ошибке");
    }


    @DisplayName("Проверка создания курьера с незаполненным полем login")
    @Description("Проверка, что для создать курьера в системе не получится если требуется заполнены все обязательные поля.")
    @Test
    public void TestCreateNewCourierWithoutLoginFields() {
        Map<String, String> courier = Map.of("password", "1234", "firstName", "saske");
        Response response = given()
                .header("Content-Type", "application/json")
                .log().all()
                .body(courier)
                .when()
                .post("/courier");
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
        Map<String, String> courier = Map.of("login", "ninja7795", "firstName", "saske");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(courier)
                .when()
                .post("/courier");
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
        var creds = Credentials.from(courier);
        Response response = given()
                .header("Content-Type", "application/json")
                .log().all()
                .body(creds)
                .when()
                .post("/courier");
        response.then()
                .log().all()
                .extract()
                .response();

        assertEquals(400, response.getStatusCode(), "Не верный код ответа");
        assertEquals("Недостаточно данных для создания учетной записи",
                response.body().jsonPath().get("message"), "Не верное сообщение об ошибке");

    }

    @AfterEach
    public void tearDown() {
        if (courier != null) {
            courier.deleteCourier(courier.getIdCourier(courier));
        }
    }


}
