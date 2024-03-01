package com.example.Transfers.service;


import com.example.Transfers.exception.MessageException;
import com.example.Transfers.model.UpdatesModel;
import com.example.Transfers.model.UserDto;

import java.util.List;


public interface ITransfers {

    Double getSaldo(Long id);

    void updateSaldo(Double monto, Long id);

    void sendMoney(Double monto, String cbuFrom, String cbuTo) throws MessageException;

    UserDto findUserByEmail(String email);

    UserDto findUserByCbu(String cbu);


    void createBalanceAccount(UserDto userDto);


    List<UserDto> findAllUser();

    void deleteByEmail(String email);

    int getGifts(String cbu);

    UserDto findUserById(Long id);

    void updateUser(UserDto userDto);
}
