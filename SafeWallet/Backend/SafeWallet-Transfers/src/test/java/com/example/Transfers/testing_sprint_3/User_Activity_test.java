package com.example.Transfers.testing_sprint_3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.Transfers.model.UserDto;
import com.example.Transfers.reports.ExtentFactory;
import com.example.Transfers.repository.ITransferRepository;
import com.example.Transfers.service.mongoDB.SequenceGeneratorService;
import com.example.Transfers.service.transfer.TransferService;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class User_Activity_test {
    static ExtentSparkReporter info = new ExtentSparkReporter("target/transferences_test.html");
    static ExtentReports extent;

    @Mock
    private TransferService transferService;
    @Mock
    private ITransferRepository transferRepository;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    private static String cardNumber = "23126515545616";
    private static Long userId = 20L;

    private int activityId = 57;

    static String login_successful() {
        ExtentTest test = extent.createTest("Login exitoso");
        String urlKeycloak = "http://localhost:8080/realms/safewallet/protocol/openid-connect/token";
        String username = "admin";
        String password = "admin";
        String clientId = "safe-wallet-api";
        String grantType = "password";

        Response response = given()
                .param("username", username)
                .param("password", password)
                .param("client_id", clientId)
                .param("grant_type", grantType)
                .when()
                .post(urlKeycloak)
                .thenReturn();

        String responseBody = response.getBody().asString();


        // Acceder a la propiedad 'access_token' dentro de la respuesta
        String accessToken = response.jsonPath().getString("access_token");
        return accessToken;

    }

    @BeforeAll
    public static void create_report() {
        extent = ExtentFactory.getInsance(); // instanciamos la clase para crear reportes
        extent.attachReporter(info); // seleccionamos la ruta donde guardamos el report
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        transferService = new TransferService(transferRepository);

    }



    static void createCard(){
        String token = login_successful();

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cardNumber\": \"" + cardNumber + "\", \"cardType\": \"debito\", \"expirationDate\": \"10/01/2028\", \"cvv\": \"124\" }")
                .when()
                .post("http://localhost:8086/accounts/" + userId + "/cards")
                .then().log().all();
    }


    @Test
    @Order(1)
    public void get_all_user_activity() {
        ExtentTest test = extent.createTest("Obtener todas las actividades de un usuario");
        String token = login_successful();
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .when()
                .get("http://localhost:8086/accounts/transaction/" + userId + "/all")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }


    @Test
    @Order(2)
    public void get_all_user_activity_wrong_user() {
        ExtentTest test = extent.createTest("Seleccionar transacción de un usuario que no existe");
        String token = login_successful();

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:8086/accounts/" + "p" + "/cards/" + cardNumber)
                .then()
                .log().all().assertThat().statusCode(400);
    }

    @Test
    @Order(3)
    public void get_specific_transaction() {

        ExtentTest test = extent.createTest("Detalle de una actividad especifica.");
        String token = login_successful();

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:8086/accounts/" + userId + "/activity/" + activityId)
                .then()
                .log().all().assertThat().statusCode(200);

    }

    @Test
    @Order(4)
    public void get_specific_transaction_wrongID() {

        ExtentTest test = extent.createTest("Detalle de una actividad especifica.");
        String token = login_successful();

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:8086/accounts/" + userId + "/activity/" + "p")
                .then()
                .log().all().assertThat().statusCode(400);

    }

    @Test
    @Order(5)
    public void get_specific_transaction_noAuth() {

        ExtentTest test = extent.createTest("Detalle de una actividad especifica sin autorización.");

        given()
                .get("http://localhost:8086/accounts/" + userId + "/activity/" + activityId)
                .then()
                .log().all().assertThat().statusCode(403);

    }

    @Test
    @Order(6)
    public void deposit_money_from_card() {
        ExtentTest test = extent.createTest("Depositar dinero desde una tarjeta");
        String token = login_successful();
        createCard();

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cardNumber\": \"" + cardNumber + "\", \"amount\": 10 }")
                .when()
                .post("http://localhost:8086/accounts/" + userId + "/transferences")
                .then()
                .log().all().assertThat().statusCode(201);
    }

    @Test
    @Order(6)
    public void deposit_money_from_card_wrong() {
        ExtentTest test = extent.createTest("Depositar dinero desde una tarjeta");
        String token = login_successful();
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cardNumber\": \"" + cardNumber +"515461546" +"\", \"amount\":" + 10 + "}")
                .when()
                .post("http://localhost:8086/accounts/" + userId + "/transferences")
                .then()
                .log().all().assertThat().statusCode(500);
    }



}
