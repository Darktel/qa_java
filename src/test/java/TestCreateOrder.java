import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.order.BaseOrderTest;
import praktikum.order.Order;
import java.util.*;
import java.util.stream.Stream;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


public class TestCreateOrder extends BaseOrderTest {
    private final BaseOrderTest baseOrderTest = new BaseOrderTest();

    static Stream<Arguments> colorCombinations() {
        return Stream.of(
                // Один цвет
                Arguments.of("GREY only", Arrays.asList("GREY")),
                Arguments.of("BLACK only", Arrays.asList("BLACK")),

                // Несколько цветов
                Arguments.of("BLACK or BLACK", Arrays.asList("GREY", "BLACK")),

                // Пустые значения
                Arguments.of("Empty list", Collections.emptyList()),
                Arguments.of("Null colors", null)
        );
    }

    @DisplayName("Проверка корректности создания заказа")
    @ParameterizedTest(name = "{0}")
    @MethodSource("colorCombinations")
    public void TestCteateOrderPassed(String testName, List<String> colors) {
       Order order = new Order("Naruto999", "Uzumaki777",
               "Konoha, 142 apt.", "4",
               "+7 800 355 35 35", 5,
               "2020-06-06", "Saske, come back to Konoha",
               colors);
        Response response = baseOrderTest.createOrder(order);
        baseOrderTest.assertSuccessCreateOrder(response);

        // Проверяем, что это действительно int
        assertInstanceOf(Integer.class, response.jsonPath().get("track"));
    }

}
