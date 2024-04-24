package com.example.Transfers.testing_sprint_4;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
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
public class sendMoney_Test {
    static ExtentSparkReporter info = new ExtentSparkReporter("target/sendMoney.html");
    static ExtentReports extent;

    @Mock
    private TransferService transferService;
    @Mock
    private ITransferRepository transferRepository;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    private static String baseUrladmin ="http://54.156.39.107:8080";


    static String login_successful() {
        ExtentTest test = extent.createTest("Login exitoso");
        String urlKeycloak = baseUrladmin + "/realms/safewallet/protocol/openid-connect/token";
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




    @Test
    @Order(1)
    public void send_money_cbu() {
        ExtentTest test = extent.createTest("Enviar dinero a un usuario - CBU");
        String token = login_successful();
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cbuFrom\": \"6513591138974673569357\", \"cbuTo\": \"5069815974733830697771\", \"amount\": 100 }")
                .when()
                .put("http://localhost:8086/accounts/send")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }

    @Test
    @Order(2)
    public void send_money_alias() {
        ExtentTest test = extent.createTest("Enviar dinero a un usuario - ALIAS");
        String token = login_successful();
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cbuFrom\": \"6513591138974673569357\", \"cbuTo\": \"tempestad.martillo.cerezas\", \"amount\": 100 }")
                .when()
                .put("http://localhost:8086/accounts/send")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }


    @Test
    @Order(3)
    public void send_money_cvu() {
        ExtentTest test = extent.createTest("Enviar dinero a un usuario - CVU");
        String token = login_successful();
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cbuFrom\": \"6513591138974673569357\", \"cbuTo\": \"svLcsWWN0PsejfZwIAjY0y\", \"monto\": 100 }")
                .when()
                .put("http://localhost:8086/accounts/send")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }

    @Test
    @Order(4)
    public void send_money_badRequest() {
        ExtentTest test = extent.createTest("Enviar dinero a un usuario - BAD REQUEST");
        String token = login_successful();
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cbuFrom\": \"6513591138974673569357\", \"cbuTo\": \"svLcsWWN0PsejfZwIAjY0y\", \"monto\": 0 }")
                .when()
                .put("http://localhost:8086/accounts/send")
                .then()
                .log().all()
                .assertThat().statusCode(400);
    }

    @Test
    @Order(5)
    public void send_money_insufficientFunds() {
        ExtentTest test = extent.createTest("Enviar dinero a un usuario - FONDOS INSUFICIENTES");
        String token = login_successful();
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cbuFrom\": \"6513591138974673569357\", \"cbuTo\": \"svLcsWWN0PsejfZwIAjY0y\", \"monto\": 9999999999 }")
                .when()
                .put("http://localhost:8086/accounts/send")
                .then()
                .log().all()
                .assertThat().statusCode(410);
    }



}
