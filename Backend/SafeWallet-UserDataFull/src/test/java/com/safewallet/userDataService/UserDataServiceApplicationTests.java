package com.safewallet.userDataService;

import com.safewallet.userDataService.controller.UserController;
import com.safewallet.userDataService.feign.feignService.FeignService;
import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.repository.IUserRepository;
import com.safewallet.userDataService.service.UserService;
import com.safewallet.userDataService.service.mongoDB.SequenceGeneratorService;
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
class UserDataServiceApplicationTests {

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

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		userController = new UserController(userService, feignService);
	}

	@Test
	void testCreateUser_Success() throws Exception {
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
