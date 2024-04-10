package com.example.Transfers;

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

import static io.restassured.RestAssured.given;

public class Card_delete_test {

    static ExtentSparkReporter info = new ExtentSparkReporter("target/card_test.html");
    static ExtentReports extent;

    @Mock
    private TransferService transferService;
    @Mock
    private ITransferRepository transferRepository;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    private String cardNumber = "516518948";
    private Long userId = 20L;

    @BeforeAll
    public static void create_report() {
        extent = ExtentFactory.getInsance(); // instanciamos la clase para crear reportes
        extent.attachReporter(info); // seleccionamos la ruta donde guardamos el report
        UserDto userDto = new UserDto();


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


    String login_successful() {
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



    public void a_create_card_test() {
        ExtentTest test = extent.createTest("Crear una nueva tarjeta");
        String token = login_successful();


    }

    @Test
    public void d_delete_card() {

        ExtentTest test = extent.createTest("Eliminar Tarjeta");
        String token = login_successful();
        String idCard = cardNumber;
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cardNumber\": \"" + cardNumber + "\", \"cardType\": \"debito\", \"expirationDate\": \"10/01/2024\", \"cvv\": \"124\" }")
                .when()
                .post("http://localhost:8086/accounts/" + userId + "/cards");



        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cardNumber\":"+ idCard +"}")
                .when()
                .delete("http://localhost:8086/accounts/" + userId + "/cards")
                .then().assertThat().statusCode(200);

    }


    @Test
    public void e_delete_card_wrongId() {

        ExtentTest test = extent.createTest("Eliminar Tarjeta");
        String token = login_successful();
        String idCard = "123455";

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{ \"cardNumber\":"+ idCard +"}")
                .when()
                .delete("http://localhost:8086/accounts/" + userId + "/cards")
                .then().assertThat().statusCode(404);
    }



}
