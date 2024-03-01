package com.example.Transfers.service;

import com.example.Transfers.exception.MessageException;
import com.example.Transfers.model.UpdatesModel;
import com.example.Transfers.model.UserDto;
import com.example.Transfers.model.UserTransactionsDto;
import com.example.Transfers.repository.ITransferRepository;
import com.mongodb.client.model.Updates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService implements ITransfers {


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
    public void deleteByEmail(String email) {
        UserDto userDto = findUserByEmail(email);
        if (userDto != null) {
            iTransferRepository.deleteByEmail(email);
        }
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
        UserDto userFrom = iTransferRepository.findUserByCbu(cbuFrom);
        UserDto userTo = iTransferRepository.findUserByCbu(cbuTo);
        Double userFromBalance = userFrom.getBalance();
        if (userTo == null) {
            throw new MessageException("¡Ups! Parece que el usuario al que quieres transferir no existe, por favor revisa los datos. ");
        }
        if (monto > userFromBalance) {
            throw new MessageException("No dispones de saldo para hacer esta transferencia :( .");
        } else {
            UserTransactionsDto transactionFrom = settingNewTransaction(cbuTo, monto * -1, cbuFrom);
            userFrom.getTransactions().add(transactionFrom);
            transactionFrom = settingNewTransaction(cbuFrom, monto, cbuTo);
            userTo.getTransactions().add(transactionFrom);
            iTransferRepository.save(userFrom);
            iTransferRepository.save(userTo);

            this.updateSaldo(monto * -1, userFrom.getId());
            this.updateSaldo(monto, userTo.getId());
        }
    }


    @Override
    public UserDto findUserByEmail(String email) {
        return iTransferRepository.findUserByEmail(email);

    }


    @Override
    public void createBalanceAccount(UserDto userDto) {
        System.out.println("Seteando balance 0, nuevo usuario");
        userDto.setBalance(0.0);
        userDto.setWelcomeGift(false);
        List<UserTransactionsDto> transactions = new ArrayList<>();
        userDto.setTransactions(transactions);
        iTransferRepository.save(userDto);

    }

    public UserDto findUserByCbu(String cbu) {
        return iTransferRepository.findUserByCbu(cbu);
    }

    @Override
    public int getGifts(String cbu) {
        // Si se completa con exito devuelve 1, sino devuelve 0. En el caso de 0 el user ya reclamo el premio.
        UserDto userDto = iTransferRepository.findUserByCbu(cbu);
        if (userDto.getWelcomeGift() == false) {
            userDto.setWelcomeGift(true);
            Double balance = userDto.getBalance();
            balance += 10000;
            userDto.setBalance(balance);
            UserTransactionsDto transaction = settingNewTransaction("SafeWallet", 10000.0, userDto.getName());
            List<UserTransactionsDto> transactions = userDto.getTransactions();
            transactions.add(transaction);
            iTransferRepository.save(userDto);
            return 1;
        }
        return 0;
    }

    @Override
    public UserDto findUserById(Long id) {
        if (iTransferRepository.findUserById(id) != null) {
            return iTransferRepository.findUserById(id);
        }
        return null;
    }

    @Override
    public void updateUser(UserDto userDto) {
        try {
            iTransferRepository.save(userDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserTransactionsDto settingNewTransaction(String from, Double amount, String to) {
        LocalDateTime date = LocalDateTime.now();
        UserTransactionsDto userTransactionsDto = null;
        if (from.equalsIgnoreCase("SafeWallet")) {
            userTransactionsDto = new UserTransactionsDto("SafeWallet", to, amount, date);
            return userTransactionsDto;
        }
        String userFrom = iTransferRepository.findUserByCbu(from).getName();
        String userTo = iTransferRepository.findUserByCbu(to).getName();

        userTransactionsDto = new UserTransactionsDto(userFrom, userTo, amount, date);

        return userTransactionsDto;
    }


}
