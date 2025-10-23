package praktikum.base;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import praktikum.models.Courier;
import praktikum.courier.CredentialsLoginPassword;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BaseCourierTest {
    BaseHttpClient baseHttpClient = new BaseHttpClient();


    // Метод для создания курьера
    @Step("Создание курьера")
    public Response createCourier(Courier courierData) {
        return given()
                .spec(baseHttpClient.requestSpecification)
                .body(courierData)
                .when()
                .post("/courier");
    }

    @Step("Создание курьера, используя параметры: {courierData}")
    public Response createCourier(Map<String, String> courierData) {
        return given()
                .spec(baseHttpClient.requestSpecification)
                .body(courierData)
                .when()
                .post("/courier");
    }

    @Step("Логин курьера")
    public Response loginCourier(CredentialsLoginPassword credentials) {
        return given()
                .spec(baseHttpClient.requestSpecification)
                .body(credentials)
                .when()
                .post("/courier/login");
    }
    @Step("Логин курьера используя параметры: {credentials}")
    public Response loginCourierMapCreds(Map<String, String> credentials) {
        return given()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post("/courier/login");
    }

    @Step("Получение ID Курьера")
    public String getIdCourier(CredentialsLoginPassword credentials) {
        Response response = given()
                .spec(baseHttpClient.requestSpecification)
                .body(credentials)
                .when()
                .post("/courier/login");

        return response.jsonPath().getString("id");
    }

    @Step("Получение ID Курьера {credentials}")
    public String getIdCourier(Map<String, String> credentials) {
        Response response = given()
                .spec(baseHttpClient.requestSpecification)
                .body(credentials)
                .when()
                .post("/courier/login");

        return response.jsonPath().getString("id");
    }

    @Step("Удаление курьра")
    public Response deleteCourier(String id) {
        return given()
                .spec(baseHttpClient.requestSpecification)
                .when().delete("courier/" + id);
    }

    @Step("Проверка успешного ответа при логине курьера и что id не пустой")
    public void assertSuccessLogin(Response response) {
        response.then()
                .statusCode(200)
                .body("id", notNullValue());
    }


    // Метод для проверки ошибки (400 + сообщение)
    @Step("Проверка на код ответа 400 и корректное сообщение об ошибке")
    public void assertBadRequest(Response response, String expectedMessage) {
        response.then()
                .statusCode(400)
                .body("message", equalTo(expectedMessage));
    }

    @Step("Проверка на код ответа 404 и корректное сообщение об ошибке")
    public void assertNotFoundRequest(Response response, String expectedMessage) {
        response.then()
                .statusCode(404)
                .body("message", equalTo(expectedMessage));
    }

    @Step("Проверка что Произошло успешное создание курьера")
    public void assertSuccessCreate(Response response) {
        response.then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Step("Проверка на код ответа 409 и корректное сообщение об ошибке")
    public void assertOnConflict(Response response) {
        response.then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }
}