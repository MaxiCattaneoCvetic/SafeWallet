package com.safewallet.userDataService.feign;

import com.safewallet.userDataService.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "account-balance") // url = "localhost:8086")
public interface CreateAccountFeignClient {

    @PostMapping("/transfer")
    void createAccountBalance(UserDto userDto);

    @DeleteMapping("/transfer")
    void deleteAccountBalance(String email);
}
