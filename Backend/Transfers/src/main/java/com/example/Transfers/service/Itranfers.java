package com.example.Transfers.service;


import com.example.Transfers.model.UserDto;

import java.util.Optional;

public interface Itranfers {
    // definir metodos de transferencias
    Double getSaldo(Long id);
    void updateSaldo(Double monto,Long id);
    void sendMoney(Double monto, String cbuFrom, String cbuTo);
    Optional<UserDto> findUserById(Long id);
    UserDto findByCbu(String cbu);

    void createBalanceAccount(UserDto userDto);



}
