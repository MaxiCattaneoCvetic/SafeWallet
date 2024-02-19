package com.safewallet.userDataService.service;

import com.safewallet.userDataService.exception.MessageException;
import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.repository.IUserRepository;
import com.safewallet.userDataService.service.mongoDB.SequenceGeneratorService;
import com.safewallet.userDataService.service.serviceInterface.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.List;
import org.apache.log4j.Logger;

import static com.safewallet.userDataService.cbuGenerator.Cbu.generateCbu;

//Contiene la lógica de negocio de la aplicación.
//Actúa como intermediario entre el controlador y el repositorio.
@Service
public class UserService implements IUserService {

    @Autowired
    private  IUserRepository userRepository;

    Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;



    @Override
    public List<UserDto> findAll() {
        return  userRepository.findAll();

    }

    @Override
    public void createUser(UserDto userDto) throws MessageException {
        logger.info("creando usuario");
        String errorMessage = "El DNI o el correo ya fue registrado";
        String errorMessage2 = "Error al crear el usuario, por favor contacte con un administrador.";
        UserDto newUser = new UserDto(userDto.getName(),userDto.getLastName(),userDto.getEmail(),userDto.getPhone(),userDto.getDni());

        //seteamos un id incremental
        userDto.setId(sequenceGeneratorService.generateSequence(UserDto.SEQUENCE_NAME));


        String correo = userDto.getEmail();
        List<UserDto> userDtoList = userRepository.findAll();

        for(UserDto user : userDtoList ){
            if(user.getEmail().equals(correo) || user.getDni().equals(userDto.getDni())){
                System.out.println(errorMessage);
                 throw  new MessageException(errorMessage);
            }

        }

        String newCbu = checkUniqueCbu();
        userDto.setCbu(newCbu);

        try{
            userRepository.save(userDto);
        }catch (Exception e){
            throw new MessageException(errorMessage);
        }


    }

    @Override
    public UserDto findByUsername(String username) {
        List<UserDto> allUsers = userRepository.findAll();
        UserDto userFound = null;

        for (UserDto userDto: allUsers) {
            if(userDto.getEmail().equals(username)){
                return  userDto;
            }
        }
        return userFound;
    }


    public String checkUniqueCbu() {
        String cbuGenerated = generateCbu();

        List<UserDto> allUsers;
        allUsers = userRepository.findAll();
        for(UserDto users : allUsers){
            if(users.getCbu() == null){
                // El primer usuario tendra un CBU vacio, con lo cual es la unica vez que entrara a este if
                return cbuGenerated;
            }
            if(users.getCbu().equals(cbuGenerated)){
                cbuGenerated = generateCbu();
            }
        }

        return cbuGenerated;

    }




}


