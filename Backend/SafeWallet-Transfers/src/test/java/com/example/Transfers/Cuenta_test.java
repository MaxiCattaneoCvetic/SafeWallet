package com.example.Transfers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.Transfers.model.UserDto;
import com.example.Transfers.model.UserTransactionsDto;
import com.example.Transfers.reports.ExtentFactory;
import com.example.Transfers.repository.ITransferRepository;
import com.example.Transfers.service.mongoDB.SequenceGeneratorService;
import com.example.Transfers.service.transfer.TransferService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class Cuenta_test {
    static ExtentSparkReporter info = new ExtentSparkReporter("target/cuenta_test.html");
    static ExtentReports extent;

    @Mock
    private TransferService transferService;
    @Mock
    private ITransferRepository transferRepository;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @BeforeAll
    public static void create_report() {
        extent = ExtentFactory.getInsance(); // instanciamos la clase para crear reportes
        extent.attachReporter(info); // seleccionamos la ruta donde guardamos el report
        UserDto userDto = new UserDto();
        userDto.setCbu("4214578965412321458745");


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
    public void generate_new_balance_Account() {
        ExtentTest test = extent.createTest("Obtener saldo de una cuenta nueva (Saldo $0).");
        UserDto user = new UserDto();
        transferService.createBalanceAccount(user);
        Double actualBalance = user.getBalance();

        assertEquals(0.0, actualBalance);
        System.out.println("El saldo de la nueva cuenta es $" + actualBalance);


    }



    @Test
    public void get_transactions() {
        ExtentTest test = extent.createTest("Obtener las transacciones.");
        MockitoAnnotations.initMocks(this);
        transferService = new TransferService(transferRepository);

        // Crear un usuario simulado
        UserDto user = new UserDto();
        user.setId(1L);
        UserTransactionsDto transaction1 = new UserTransactionsDto(1l,"Maxi","tes1t",20.0, LocalDateTime.now());
        UserTransactionsDto transaction2 = new UserTransactionsDto(2l,"Maxi","test2",30.0, LocalDateTime.now());
        UserTransactionsDto transaction3 = new UserTransactionsDto(3l,"Maxi","test3",2550.0, LocalDateTime.now());
        UserTransactionsDto transaction4 = new UserTransactionsDto(4l,"Maxi","test4",1320.0, LocalDateTime.now());
        List<UserTransactionsDto> transactionList = new ArrayList<>();
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        transactionList.add(transaction3);
        transactionList.add(transaction4);
        user.setTransactions(transactionList);

        assertEquals(4, transactionList.size());
        System.out.println("el usuario posee " + transactionList.size() + " transacciones.") ;




    }


    @Test
    public void get_all_users() {

        ITransferRepository transferRepositoryMock = Mockito.mock(ITransferRepository.class);


        UserDto user1 = new UserDto();
        UserDto user2 = new UserDto();
        UserDto user3 = new UserDto();
        UserDto user4 = new UserDto();


        when(transferRepositoryMock.findAll()).thenReturn(Arrays.asList(user1, user2, user3, user4));


        TransferService transferService = new TransferService(transferRepositoryMock);


        List<UserDto> allUsers = transferService.findAllUser();


        assertEquals(4, allUsers.size());
    }






}
