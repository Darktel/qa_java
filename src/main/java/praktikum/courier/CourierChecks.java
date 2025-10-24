package praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierChecks {

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
