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

        userRepository.save(userDto);


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


