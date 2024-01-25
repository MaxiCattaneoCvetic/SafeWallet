package com.example.Transfers.service;


import com.example.Transfers.exception.MessageException;
import com.example.Transfers.model.UserDto;

import java.util.List;


public interface Itranfers {

    Double getSaldo(Long id);

    void updateSaldo(Double monto, Long id);

    void sendMoney(Double monto, String cbuFrom, String cbuTo) throws MessageException;

    UserDto findUserByEmail(String email);


    void createBalanceAccount(UserDto userDto);


    List<UserDto> findAllUser();
}
