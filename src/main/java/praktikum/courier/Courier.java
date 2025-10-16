package praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class Courier {
    private final String login;
    private final String password;
    private final String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Step("Создание курьера")
    public static Response createCourier(Courier courier) {
        Response response = given().contentType(ContentType.JSON)
                .log().all()
                .body(courier)
                .when()
                .post("/courier");
        return response;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    @Step("Получение ID курьера")
    public String getIdCourier(Courier courier) {
        Credentials creds = Credentials.from(courier);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(creds)
                .log().all()
                .when()
                .post("/courier/login");

        return response.jsonPath().getString("id");
    }

    @Step("Удаление курьера")
    public void deleteCourier(String id) {
        Response resp = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when().delete("/courier/" + id);
    }

}
