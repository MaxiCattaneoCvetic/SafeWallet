package com.safewallet.userDataService.feign;

import com.safewallet.userDataService.model.UpdatesModel;
import com.safewallet.userDataService.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-balance") // url = "localhost:8086")
public interface AccountFeignClient {

    @PostMapping("/accounts/create")
    void createAccountBalance(UserDto userDto);

    @DeleteMapping("/accounts")
    void deleteAccountBalance(String email);

    @PutMapping("/accounts/update/{id}") // El metodo path no anda con feign, resolvemos enviando un model custom
    void updateAccountBalance(@PathVariable("id") Long id, @RequestBody UpdatesModel updatesModel);

}
