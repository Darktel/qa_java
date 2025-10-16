import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class TestListOrder {
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/api/v1";
    }

    @DisplayName("Проверка корректности получения списка заказов")
    @Test
    public void TestGetOrderList() {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/orders");
        response.then().assertThat()
                .statusCode(200)
                .body("orders",  // в теле ответа есть JSON c объектом orders,
                hasSize(30)); // содержащий 30 элементов - значение по умолчанию.
    }
}
