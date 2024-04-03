package com.safewallet.userDataService.feign.feignService;

import com.safewallet.userDataService.feign.AccountFeignClient;
import com.safewallet.userDataService.feign.UserKeycloakFeign;
import com.safewallet.userDataService.model.UpdatesModel;
import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.service.serviceInterface.IUserService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeignService implements IUserService {

    @Autowired
    private AccountFeignClient accountFeignClient;

    @Autowired
    private UserKeycloakFeign userKeycloakFeign;

    @Autowired
    public FeignService(AccountFeignClient accountFeignClient, UserKeycloakFeign userKeycloakFeign) {
        this.accountFeignClient = accountFeignClient;
        this.userKeycloakFeign = userKeycloakFeign;
    }

    public FeignService() {
    }


    @Override
    public List<UserDto> findAll() {
        return null;
    }


    @Override
    public void createUser(UserDto userDto) throws Exception {
        try {
            userKeycloakFeign.createUserKeycloak(userDto);
            accountFeignClient.createAccountBalance(userDto);
        } catch (Exception e) {
            //accountFeignClient.deleteAccountBalance(userDto.getEmail());
            throw new Exception("Error al crear el usuario");
        }
    }


    @Override
    public UserDto findByUsername(String username) {
        return null;
    }

    @Override
    public void deleteUser(String email) throws Exception {
        try {
            userKeycloakFeign.deleteUserKeycloak(email);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el usuario");
        }

    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public void updateUser(UserDto userDto, UpdatesModel updatesModel, String oldEmail) {
        accountFeignClient.updateAccountBalance(userDto.getId(), updatesModel);
        if(updatesModel.getType().containsKey("email")){
            userKeycloakFeign.updateUserKeycloack(oldEmail,updatesModel);
        }

    }

    @Override
    public UserDto findByCbu(String cbu) {
        return null;
    }

    @Override
    public UserDto findByAlias(String alias) {
        return null;
    }

    @Override
    public UserDto findByCvu(String cvu) {
        return null;
    }

    @Override
    public List<UserRepresentation> findUser(String username) {
        try {
            return userKeycloakFeign.getUserKeycloak(username);
        } catch (Exception e) {
            return null;
        }

    }


}
