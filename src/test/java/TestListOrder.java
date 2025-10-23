import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.order.OrderChecks;
import praktikum.order.OrderClient;



public class TestListOrder {
    private final OrderClient orderClient = new OrderClient();
    private final OrderChecks check = new OrderChecks();

    @DisplayName("Проверка корректности получения списка заказов")
    @Test
    public void testGetOrderList() {
        Response response = orderClient.getOrderList();
        check.assertSuccessGetOrderList(response); // содержащий 30 элементов - значение по умолчанию.
    }
}
