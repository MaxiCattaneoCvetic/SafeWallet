package com.safewallet.userDataService.feign.feignService;

import com.safewallet.userDataService.feign.CreateAccountFeignClient;
import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.service.serviceInterface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceFeign implements IUserService {

    @Autowired
    public AccountServiceFeign(CreateAccountFeignClient createAccountFeignClient) {
        this.createAccountFeignClient = createAccountFeignClient;
    }

    public AccountServiceFeign() {
    }

    @Autowired
    private CreateAccountFeignClient createAccountFeignClient;

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public void createUser(UserDto userDto) throws Exception {
        createAccountFeignClient.createAccountBalance(userDto);
        System.out.println("Cuenta balance creada");

    }

    @Override
    public ResponseEntity<?> findByUsername(String username) {
        return null;
    }
}
