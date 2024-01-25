package com.example.Transfers.service;

import com.example.Transfers.exception.MessageException;
import com.example.Transfers.model.UserDto;
import com.example.Transfers.repository.ITransferRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferService implements Itranfers {


    @Autowired
    private ITransferRepository iTransferRepository;

    @Autowired
    public TransferService(ITransferRepository iTransferRepository) {
        this.iTransferRepository = iTransferRepository;
    }


    public TransferService() {
    }


    @Override
    public List<UserDto> findAllUser() {
        List<UserDto> allUser = iTransferRepository.findAll();
        return allUser;
    }


    @Override
    public Double getSaldo(Long id) {
        Optional<UserDto> user = iTransferRepository.findById(id);
        Double balance = 0.0;
        if (user.isPresent()) {
            balance = user.get().getBalance();
        }
        return balance;

    }


    @Override
    public void updateSaldo(Double monto, Long id) {
        Double initialBalance;
        Double newAmount;
        Optional<UserDto> user = iTransferRepository.findById(id);


        if (user.isPresent()) {
            UserDto userDto = user.get();
            initialBalance = userDto.getBalance();

            newAmount = initialBalance + monto;
            userDto.setBalance(newAmount);
            iTransferRepository.save(userDto);

            System.out.println("Saldo actualizado, nuevo monto es de $" + newAmount);
        } else {
            System.out.println("No se encuentra el usuario");
        }
    }


    @Override
    public void sendMoney(Double monto, String cbuFrom, String cbuTo) throws MessageException {
        List<UserDto> allUsers = this.findAllUser();
        UserDto userFrom = null;
        UserDto userTo = null;
        Double userFromBalance = 0.0;

        for(int i = 0; i < allUsers.size(); i++){
            if(cbuFrom.equals(allUsers.get(i).getCbu())){
                userFrom = allUsers.get(i);
            }else if(cbuTo.equals(allUsers.get(i).getCbu())){
                userTo = allUsers.get(i);
            }
        }

        if (userFrom != null && userTo != null) {
            userFromBalance = userFrom.getBalance();
            if (monto > userFromBalance) {
                throw new MessageException("No dispones de saldo para hacer esta transferencia :( .");
            } else {
                this.updateSaldo(monto * -1 , userFrom.getId());
                this.updateSaldo(monto, userTo.getId());
            }
        } else {
            throw new MessageException("¡Ups! Parece que el usuario al que quieres transferir no existe, por favor revisa los datos. ");
        }
    }


    @Override
    public UserDto findUserByEmail(String email) {
        UserDto user = iTransferRepository.findUserByEmail(email);
        return user;

    }


    @Override
    public void createBalanceAccount(UserDto userDto) {
        System.out.println("Seteando balance 0, nuevo usuario");
        userDto.setBalance(0.0);
        iTransferRepository.save(userDto);

    }




}
