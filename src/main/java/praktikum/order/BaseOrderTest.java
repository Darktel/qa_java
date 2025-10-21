package praktikum.order;

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

    public Response createOrder(Order orderData) {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(orderData)
                .when()
                .post("/orders");
    }

    public void assertSuccessCreateOrder(Response response) {
        response.then()
                .statusCode(201)
                .body("track", notNullValue());
    }
}
