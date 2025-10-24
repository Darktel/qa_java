package praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import praktikum.base.BaseHttpClient;
import praktikum.models.Courier;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierClient {
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
                .spec(baseHttpClient.requestSpecification)
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
}