import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.courier.*;
import java.util.Map;



public class TestCreateCourier extends BaseCourierTest {
    private final BaseCourierTest apiCourier = new BaseCourierTest();
    int rnd = (int) (Math.random() * 1000);
    private final Courier courier = new Courier("ninja97795"+rnd,  "1234", "saske99998"); // Поле класса


    @DisplayName("Проверка создания курьера")
    @Test
    public void testCreateNewCourierPassed() {
        Response response = apiCourier.createCourier(courier);
        apiCourier.assertSuccessCreate(response);
    }

    @DisplayName("Проверка создания не уникального курьера")
    @Description("Нельзя создать двух одинаковых курьеров")
    @Test
    public void testCreateNewCourierNotUnique() {
        apiCourier.createCourier(courier);
        Response response = apiCourier.createCourier(courier);
        apiCourier.assertOnConflict(response);
    }


    @DisplayName("Проверка создания курьера с незаполненным полем login")
    @Description("Проверка, что для создать курьера в системе не получится если требуется заполнены все обязательные поля.")
    @Test
    public void testCreateNewCourierWithoutLoginFields() {
        var creds = Map.of("password", "1234", "firstName", "saske");
        Response response = apiCourier.createCourier(creds);
        apiCourier.assertBadRequest(response, "Недостаточно данных для создания учетной записи");

        if (response.getStatusCode()==201){
            apiCourier.deleteCourier(apiCourier.getIdCourier(creds));
        }
    }

    @DisplayName("Проверка создания курьера с незаполненным полем password")
    @Description("Проверка, что для создать курьера в системе не получится если требуется заполнены все обязательные поля.")
    @Test
    public void testCreateNewCourierWithoutPasswordFields() {
        var creds = Map.of("login", "ninja7795", "firstName", "saske");
        Response response = apiCourier.createCourier(creds);
        apiCourier.assertBadRequest(response, "Недостаточно данных для создания учетной записи");
        if (response.getStatusCode()==201){
            apiCourier.deleteCourier(apiCourier.getIdCourier(creds));
        }
    }

    @DisplayName("Проверка создания курьера с незаполненным полем firstName")
    @Description("Проверка, что для создать курьера в системе не получится если требуется заполнены все обязательные поля.")
    @Test
    public void testCreateNewCourierWithoutFirstNameFields() {
        var creds = Map.of("login", "ninja97795"+rnd, "password", "1234");
        Response response = apiCourier.createCourier(creds);
        apiCourier.assertBadRequest(response, "Недостаточно данных для создания учетной записи");
        if (response.getStatusCode()==201){
            apiCourier.deleteCourier(apiCourier.getIdCourier(creds));
        }
    }

    @AfterEach
    public void tearDown() {
        if (courier != null) {
            apiCourier.deleteCourier(apiCourier.getIdCourier(CredentialsLoginPassword.from(courier)));
        }
    }


}
