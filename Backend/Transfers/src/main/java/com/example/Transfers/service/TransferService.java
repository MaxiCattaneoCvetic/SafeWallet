package com.example.Transfers.service;

import com.example.Transfers.model.UserDto;
import com.example.Transfers.repository.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferService implements Itranfers{



    @Autowired
    private ITransferRepository iTransferRepository;

    @Autowired
    public TransferService(ITransferRepository iTransferRepository) {
        this.iTransferRepository = iTransferRepository;
    }

    public TransferService() {
    }

    @Autowired


    @Override
    public Double getSaldo(Long id) {
        Optional<UserDto> user = findUserById(id);
        Double balance;
        if(user.isPresent()){
            balance = user.get().getBalance();
            return balance;
        }else{
            return 0.0;
        }

    }

    @Override
    public void updateSaldo(Double monto,Long id) {
        Double initialBalance;
        double newAmount;
        Optional<UserDto> user = findUserById(id);
        if(user.isPresent()){
            initialBalance = user.get().getBalance();
            newAmount = initialBalance + monto;
            user.get().setBalance(newAmount);
            System.out.println("Saldo actualizado, nuevo monto es de $" +newAmount);
        }else{
            System.out.println("No se encuentra el usuario");
        }


    }



    @Override
    public void sendMoney(Double monto, String cbuFrom, String cbuTo) {

        UserDto userFrom = findByCbu(cbuFrom);
        UserDto userTo = findByCbu(cbuTo);
        Double userFromBalance;

        if(userFrom != null && userTo != null){
            userFromBalance = userFrom.getBalance();

            if(monto > userFromBalance){
                System.out.println("No dispones saldo para esta transaccion");
            }else{
                updateSaldo(monto,userFrom.getId());
                updateSaldo(monto,userTo.getId());
            }
        }else{
            System.out.println("No se encuentra el usuario");
        }
    }




    @Override
    public Optional<UserDto>  findUserById(Long id) {
        Optional<UserDto> user = iTransferRepository.findById(id);
        if(user.isPresent()){
            return user;
        }else{
            System.out.println("Usuario no encontrado");
            return null;
        }

    }

    @Override
    public UserDto  findByCbu(String CBU) {
        UserDto user = iTransferRepository.findByCbu(CBU);
        if(user != null && user.getCBU() != null){
            return user;
        }else{
            System.out.println("Usuario no encontrado");
            return null;
        }

    }

    @Override
    public void createBalanceAccount(UserDto userDto) {
        iTransferRepository.save(userDto);

    }

}
