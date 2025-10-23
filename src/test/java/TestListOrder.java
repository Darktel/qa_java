import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.base.BaseOrderTest;



public class TestListOrder {
    private final BaseOrderTest baseOrderTest = new BaseOrderTest();

    @DisplayName("Проверка корректности получения списка заказов")
    @Test
    public void testGetOrderList() {
        Response response = baseOrderTest.getOrderList();
        baseOrderTest.assertSuccessGetOrderList(response); // содержащий 30 элементов - значение по умолчанию.
    }
}
