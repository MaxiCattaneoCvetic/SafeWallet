package com.safewallet.userDataService.service;


import com.safewallet.userDataService.exception.MessageException;
import com.safewallet.userDataService.feign.feignService.FeignService;
import com.safewallet.userDataService.model.UpdatesModel;
import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.repository.IUserRepository;
import com.safewallet.userDataService.service.mongoDB.SequenceGeneratorService;
import com.safewallet.userDataService.service.serviceInterface.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import static com.safewallet.userDataService.aliasCbu_generator.Alias.generateAlias;
import static com.safewallet.userDataService.aliasCbu_generator.Cbu.generateCbu;

//Contiene la lógica de negocio de la aplicación.
//Actúa como intermediario entre el controlador y el repositorio.
@Service
public class UserService implements IUserService {

    Logger logger = Logger.getLogger(UserService.class);
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private FeignService feignService;


    public UserService() {
    }

    public UserService(IUserRepository userRepository, FeignService feignService) {
        this.userRepository = userRepository;
        this.feignService = feignService;
    }

    public UserService(IUserRepository userRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.userRepository = userRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }


    public Boolean userExists(String value, String type) {
        UserDto user = null;

        switch (type) {
            case "alias":
                user = userRepository.findByAlias(value);
                if (user != null) {
                    return true;
                }
            case "email":
                user = userRepository.findByUsername(value);
                if (user != null) {
                    return true;
                }
        }
        return false;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll();

    }

    @Override
    public void createUser(UserDto userDto) throws MessageException {
        logger.info("creando usuario");
        try {
            //seteamos un id incremental
            userDto.setId(sequenceGeneratorService.generateSequence(UserDto.SEQUENCE_NAME));

            //seteamos un CBU
            List<String> lista = checkUniqueCbu_Alias();
            userDto.setCbu(lista.get(0));
            userDto.setAlias(lista.get(1));
            try {
                userRepository.save(userDto);
                feignService.createUser(userDto);
            }catch (Exception e){
                feignService.deleteUser(userDto.getEmail());
                throw new MessageException("Hubo un problema, no se creo el usuario");
            }

        } catch (Exception e) {
            throw new MessageException("Hubo un problema, no se creo el usuario");

        }


    }

    @Override
    public UserDto findByUsername(String username) {
        UserDto userFound = userRepository.findByUsername(username);
        if (userFound != null) {
            userFound.setPassword(null);
            return userFound;
        }
        return null;
    }

    public UserDto findByDni(String dni) {
        try {
            return userRepository.findByDni(dni);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public void deleteUser(String email) {
        UserDto userDto = findByUsername(email);
        userRepository.delete(userDto);

    }

    @Override
    public UserDto findById(Long id) {
        try {
            UserDto userDto = userRepository.findById(id).get();
            return userDto;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateUser(UserDto userDto, UpdatesModel updatesModel) {
        try {
            userRepository.save(userDto);
            feignService.updateUser(userDto, updatesModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDto findByCbu(String cbu) {
        try {
            return userRepository.findByCbu(cbu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public List<String> checkUniqueCbu_Alias() {
        List<UserDto> allUsers = userRepository.findAll();
        List<String> lista = new ArrayList<>();

        String cbuGenerated = generateCbu();
        String aliasGenerated = generateAlias();

        System.out.println("---");

        if (allUsers.size() == 0) {
            lista.add(cbuGenerated);
            lista.add(aliasGenerated);
            System.out.println("retorne lista: " + lista.get(0) + " " + lista.get(1));
            return lista;
        }


        for (UserDto user : allUsers) {
            if (user.getCbu() != null && user.getCbu().equals(cbuGenerated)) {
                cbuGenerated = generateCbu();
            }
            if (user.getAlias() != null && user.getAlias().equals(aliasGenerated)) {
                aliasGenerated = generateAlias();
            }
        }

        lista.add(cbuGenerated);
        lista.add(aliasGenerated);

        return lista;
    }

    @Override
    public UserDto findByAlias(String alias) {
        try {
            return userRepository.findByAlias(alias);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }





}


