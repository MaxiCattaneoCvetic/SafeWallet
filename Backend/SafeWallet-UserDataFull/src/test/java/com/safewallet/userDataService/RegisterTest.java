package com.safewallet.userDataService;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.safewallet.userDataService.controller.UserController;
import com.safewallet.userDataService.feign.feignService.FeignService;
import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.reports.ExtentFactory;
import com.safewallet.userDataService.repository.IUserRepository;
import com.safewallet.userDataService.service.UserService;
import com.safewallet.userDataService.service.mongoDB.SequenceGeneratorService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class RegisterTest {

	@Mock
	private IUserRepository userRepository;

	@Mock
	private FeignService feignService;

	@Mock
	private SequenceGeneratorService sequenceGeneratorService; // Agregamos la simulación del SequenceGeneratorService

	@InjectMocks
	private UserService userService;

	@InjectMocks
	private UserController userController;

	static ExtentSparkReporter info = new ExtentSparkReporter("target/register_test.html");
	static ExtentReports extent;


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
		userController = new UserController(userService, feignService);
	}

	@Test
	void testCreateUser_Success() throws Exception {
		ExtentTest test = extent.createTest("Registro usuario con datos validos");
		// Arrange
		UserDto userDto = new UserDto(3L, "Maxi", "cvetic", "asdasdasd@gmail.com", "12312", "1545151215545", "BARCO.PERRO.MAR", "222312312");

		// Configuramos el comportamiento simulado del SequenceGeneratorService
		when(sequenceGeneratorService.generateSequence(UserDto.SEQUENCE_NAME)).thenReturn(1L);

		when(userRepository.findByUsername(userDto.getEmail())).thenReturn(null);
		when(userRepository.findByDni(userDto.getDni())).thenReturn(null);

		// Act
		ResponseEntity<?> response = userController.createUser(userDto);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		System.out.println("Respuesta: " + response.getBody() + "Usuario creado con exito");
	}
	@Test
	void testCreateUser_alreadyRegister_DNI() throws Exception {
		ExtentTest test = extent.createTest("Registro usuario con dni ya registrado");
		// Arrange
		UserDto userDto = new UserDto(3L, "Maxi", "cvetic", "asdasdasd@gmail.com", "12312", "1545151215545", "BARCO.PERRO.MAR", "222312312");

		// Configuramos el comportamiento simulado del SequenceGeneratorService
		when(sequenceGeneratorService.generateSequence(UserDto.SEQUENCE_NAME)).thenReturn(1L);

		when(userRepository.findByUsername(userDto.getEmail())).thenReturn(null);
		when(userRepository.findByDni(userDto.getDni())).thenReturn(userDto);

		// Act
		ResponseEntity<?> response = userController.createUser(userDto);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		System.out.println("Respuesta: " + response.getBody());
	}

	@Test
	void testCreateUser_alreadyRegister_EMAIL() throws Exception {
		ExtentTest test = extent.createTest("Registro usuario con email ya registrado");
		// Arrange
		UserDto userDto = new UserDto(3L, "Maxi", "cvetic", "asdasdasd@gmail.com", "12312", "1545151215545", "BARCO.PERRO.MAR", "222312312");

		// Configuramos el comportamiento simulado del SequenceGeneratorService
		when(sequenceGeneratorService.generateSequence(UserDto.SEQUENCE_NAME)).thenReturn(1L);

		when(userRepository.findByUsername(userDto.getEmail())).thenReturn(userDto);
		when(userRepository.findByDni(userDto.getDni())).thenReturn(null);

		// Act
		ResponseEntity<?> response = userController.createUser(userDto);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		System.out.println("Respuesta: " + response.getBody());
	}

	@Test
	void testCreateUser_alreadyRegister_emailNull() throws Exception {
		ExtentTest test = extent.createTest("Registro usuario con email nulo");
		// Arrange
		UserDto userDto = new UserDto(3L, "Maxi", "cvetic", null, "12312", "1545151215545", "BARCO.PERRO.MAR", "222312312");

		// Configuramos el comportamiento simulado del SequenceGeneratorService
		when(sequenceGeneratorService.generateSequence(UserDto.SEQUENCE_NAME)).thenReturn(1L);

		when(userRepository.findByUsername(userDto.getEmail())).thenReturn(null);
		when(userRepository.findByDni(userDto.getDni())).thenReturn(null);

		// Act
		ResponseEntity<?> response = userController.createUser(userDto);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		System.out.println("Respuesta: " + response.getBody());
	}



}
