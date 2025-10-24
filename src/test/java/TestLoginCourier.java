import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import praktikum.courier.CourierChecks;
import praktikum.courier.CourierClient;
import praktikum.models.Courier;
import praktikum.courier.CredentialsLoginPassword;
import java.util.Map;

public class TestLoginCourier {
    private final CourierClient courierApi = new CourierClient();
    private final CourierChecks check = new CourierChecks();
//    private static final Logger log = LoggerFactory.getLogger(TestLoginCourier.class);
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
        check.assertSuccessLogin(response);
    }

    @DisplayName("Проверка логина курьера без пароля")
    @Test
    public void testLoginCourierWithoutPassword() {
        Response response = courierApi.loginCourierMapCreds(Map.of("login", "gusev_ae"));
        check.assertBadRequest(response, "Недостаточно данных для входа");
    }

    @DisplayName("Проверка логина курьера без логина")
    @Test
    public void testLoginCourierWithoutLogin() {
        Response response = courierApi.loginCourierMapCreds(Map.of("password", "99998998"));
        check.assertBadRequest(response, "Недостаточно данных для входа");

    }

    @DisplayName("Проверка логина курьера с неправильным паролем")
    @Test
    public void testLoginCourierWrongPassword() {
        Response response = courierApi.loginCourierMapCreds(Map.of("login", "gusev_ae", "password", "123"));
        check.assertNotFoundRequest(response, "Учетная запись не найдена");
    }

    @DisplayName("Проверка логина курьера с неправильным логином")
    @Test
    public void testLoginCourierWrongLogin() {
        Response response = courierApi.loginCourierMapCreds(Map.of("login", "gusev", "password", "99998998"));
        check.assertNotFoundRequest(response, "Учетная запись не найдена");
    }
    @AfterEach
    public void tearDown() {
        courierApi.deleteCourier(courierApi.getIdCourier(CredentialsLoginPassword.from(courier)));
    }

}
