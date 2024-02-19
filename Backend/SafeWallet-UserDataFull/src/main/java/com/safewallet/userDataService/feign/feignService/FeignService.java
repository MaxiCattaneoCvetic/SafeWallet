package com.safewallet.userDataService.feign.feignService;

import com.safewallet.userDataService.feign.CreateAccountFeignClient;
import com.safewallet.userDataService.feign.CreateUserKeycloak;
import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.service.serviceInterface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeignService implements IUserService {

    @Autowired
    private CreateAccountFeignClient createAccountFeignClient;

    private CreateUserKeycloak createuserkeycloak;

    @Autowired
    public FeignService(CreateAccountFeignClient createAccountFeignClient, CreateUserKeycloak createUserKeycloak) {
        this.createAccountFeignClient = createAccountFeignClient;
        this.createuserkeycloak = createUserKeycloak;
    }

    public FeignService() {
    }



    @Override
    public List<UserDto> findAll() {
        return null;
    }


    @Override
    public void createUser(UserDto userDto) throws Exception {

        try{
            createuserkeycloak.createUserKeycloak(userDto);
            createAccountFeignClient.createAccountBalance(userDto);
            System.out.println("Cuenta balance creada");

        }catch (Exception e){
            throw new Exception("Error al crear el usuario");
        }


    }



    @Override
    public UserDto findByUsername(String username) {
        return null;
    }


}
