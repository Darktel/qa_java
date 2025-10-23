import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import praktikum.base.BaseCourierTest;
import praktikum.models.Courier;
import praktikum.courier.CredentialsLoginPassword;
import java.util.Map;

public class TestLoginCourier {
    private final BaseCourierTest courierApi = new BaseCourierTest();
//    private static final Logger log = LoggerFactory.getLogger(TestLoginCourier.class);
    private static String createdCourierId;
    Courier courier = new Courier("gusev_ae", "99998998", "Aleksandr");


    @BeforeEach
    public void setUp() {
        // создания курьера, предаем в него параметры выше.
        courierApi.createCourier(courier);
    }

    @DisplayName("Проверка успешного логина курьера")
    @Test
    public void testLoginCourierPassed() {
        var creds = CredentialsLoginPassword.from(courier);
        Response response = courierApi.loginCourier(creds);
        courierApi.assertSuccessLogin(response);
    }

    @DisplayName("Проверка логина курьера без пароля")
    @Test
    public void testLoginCourierWithoutPassword() {
        Response response = courierApi.loginCourierMapCreds(Map.of("login", "gusev_ae"));
        courierApi.assertBadRequest(response, "Недостаточно данных для входа");
    }

    @DisplayName("Проверка логина курьера без логина")
    @Test
    public void testLoginCourierWithoutLogin() {
        Response response = courierApi.loginCourierMapCreds(Map.of("password", "99998998"));
        courierApi.assertBadRequest(response, "Недостаточно данных для входа");

    }

    @DisplayName("Проверка логина курьера с неправильным паролем")
    @Test
    public void testLoginCourierWrongPassword() {
        Response response = courierApi.loginCourierMapCreds(Map.of("login", "gusev_ae", "password", "123"));
        courierApi.assertNotFoundRequest(response, "Учетная запись не найдена");
    }

    @DisplayName("Проверка логина курьера с неправильным логином")
    @Test
    public void testLoginCourierWrongLogin() {
        Response response = courierApi.loginCourierMapCreds(Map.of("login", "gusev", "password", "99998998"));
        courierApi.assertNotFoundRequest(response, "Учетная запись не найдена");
    }
    @AfterEach
    public void terDown() {
        if (createdCourierId != null) {
            courierApi.deleteCourier(courierApi.getIdCourier(CredentialsLoginPassword.from(courier)));
        }
    }

}
