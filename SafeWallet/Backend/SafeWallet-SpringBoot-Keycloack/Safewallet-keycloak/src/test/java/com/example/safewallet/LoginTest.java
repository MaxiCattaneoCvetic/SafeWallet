package com.example.safewallet;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.safewallet.reports.ExtentFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static io.restassured.RestAssured.given;


public class LoginTest {

    static ExtentSparkReporter info = new ExtentSparkReporter("target/login_test.html");

    static ExtentReports extent;



    @BeforeAll
    public static void create_report() {
        extent = ExtentFactory.getInsance(); // instanciamos la clase para crear reportes
        extent.attachReporter(info); // seleccionamos la ruta donde guardamos el report

    }




    //@Test
    void login_successful() {
        ExtentTest test = extent.createTest("Login exitoso");
        String urlKeycloak = "http://safewalletiam:8080/realms/safewallet/protocol/openid-connect/token";
        String username = "admin";
        String password = "admin";
        String clientId = "safe-wallet-api";
        String grantType = "password";

        given()
                .param("username", username)
                .param("password", password)
                .param("client_id", clientId)
                .param("grant_type", grantType)
                .when()
                .post(urlKeycloak)
                .then().assertThat().statusCode(200);

    }

    //@Test
    void login_with_wrong_credentials() {
        ExtentTest test = extent.createTest("Login con credenciales incorrectas");
        String urlKeycloak = "http://safewalletiam:8080/realms/safewallet/protocol/openid-connect/token";
        String username = "admin";
        String password = "test";
        String clientId = "safe-wallet-api";
        String grantType = "password";

        given()
                .param("username", username)
                .param("password", password)
                .param("client_id", clientId)
                .param("grant_type", grantType)
                .when()
                .post(urlKeycloak)
                .then().assertThat().statusCode(401);


    }

    //@Test
    void login_with_invalid_email() {
        ExtentTest test = extent.createTest("Login con email inválido");
        String urlKeycloak = "http://safewalletiam:8080/realms/safewallet/protocol/openid-connect/token";
        String username = "testing@example";
        String password = "test";
        String clientId = "safe-wallet-api";
        String grantType = "password";

        given()
                .param("username", username)
                .param("password", password)
                .param("client_id", clientId)
                .param("grant_type", grantType)
                .when()
                .post(urlKeycloak)
                .then().assertThat().statusCode(401);
    }
}
