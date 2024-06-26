package com.safewallet.userDataService;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.reports.ExtentFactory;
import com.safewallet.userDataService.repository.IUserRepository;
import com.safewallet.userDataService.service.UserService;
import com.safewallet.userDataService.service.mongoDB.SequenceGeneratorService;

import org.junit.jupiter.api.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
public class Cbu_Alias_test {
    static ExtentSparkReporter info = new ExtentSparkReporter("target/cbu_alias_test.html");
    static ExtentReports extent;

    @Mock
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    final int testCount = 100;


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
        userService = new UserService(userRepository, sequenceGeneratorService);

    }



    @Mock
    private SequenceGeneratorService sequenceGeneratorService;


    @Test
    public void generateCbu_alias_Success() {
        ExtentTest test = extent.createTest("Generando un alias y un cbu");
        List<String> list = userService.checkUniqueCbu_Alias();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(3, list.size());
        Assertions.assertNotNull(list.get(0));
        Assertions.assertNotNull(list.get(1));
        Assertions.assertNotNull(list.get(2));
    }
    @Test
    public void generateCbu_length() {
        ExtentTest test = extent.createTest("Test de longitud de cbu");
        List<String> list = userService.checkUniqueCbu_Alias();
        Assertions.assertNotNull(list);
        System.out.println("Longitud de cbu: " + list.get(0).length());
        Assertions.assertEquals(22, list.get(0).length());

    }


    @Test
    @RepeatedTest(testCount)
    public void generateCbu_alias_Success_CheckUnique() {
        ExtentTest test = extent.createTest("Chequeando Cbu y Alias unicos se testean casos:  " + testCount);
        List<String> list1 = userService.checkUniqueCbu_Alias();

        Assertions.assertNotNull(list1);
        Assertions.assertEquals(3, list1.size());

        for (int i = 0; i < list1.size() - 1; i++) {
            System.out.println(list1.get(i));
            for (int j = i + 1; j < list1.size(); j++) {
                Assertions.assertNotEquals(list1.get(i), list1.get(j),
                        "El elemento en la posición " + i + " es igual al elemento en la posición " + j);

            }

        }
    }

}
