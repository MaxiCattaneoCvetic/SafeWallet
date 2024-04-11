package com.example.Transfers.service.transfer;

import com.example.Transfers.exception.MessageException;
import com.example.Transfers.model.Card;
import com.example.Transfers.model.TransferInformation;
import com.example.Transfers.model.UserDto;
import com.example.Transfers.model.UserTransactionsDto;
import com.example.Transfers.repository.ITransferRepository;
import com.example.Transfers.service.mongoDB.SequenceGeneratorService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TransferService implements ITransfers {


    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
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
    public void sendMoney(Double monto, String cbuFrom, String data) throws MessageException {
        UserDto userFrom = iTransferRepository.findUserByCbu(cbuFrom);
        Double userFromBalance = userFrom.getBalance();

        UserDto userTo = findUserForTransfer(data);
        if (userTo == null) {
            throw new MessageException("¡Ups! Parece que el usuario al que quieres transferir no existe, por favor revisa los datos. ");
        }
        if (monto > userFromBalance) {
            throw new MessageException("No dispones de saldo para hacer esta transferencia :( .");
        } else {
            UserTransactionsDto transaction = settingNewTransaction(cbuFrom, monto * -1, data);
            transaction.setTransferDetail(UserTransactionsDto.TransferDetail.TRANSFER);
            userFrom.getTransactions().add(transaction);


            transaction = settingNewTransaction(data, monto, cbuFrom);
            transaction.setTransferDetail(UserTransactionsDto.TransferDetail.TRANSFER);
            userTo.getTransactions().add(transaction);
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
        UserDto userDto = iTransferRepository.findUserByCbu(cbu);
        if (userDto != null) {
            return userDto;
        }
        return null;
    }

    @Override
    public UserDto findUserByCvu(String cvu) {
        UserDto userDto = iTransferRepository.findUserByCvu(cvu);
        if (userDto != null) {
            return userDto;
        }
        return null;
    }

    @Override
    public UserDto findUserByAlias(String alias) {
        UserDto userDto = iTransferRepository.findUserByAlias(alias);
        if (userDto != null) {
            return userDto;
        }
        return null;

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
            transaction.setTransferDetail(UserTransactionsDto.TransferDetail.GIFT);
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
            Long idTRansaction = sequenceGeneratorService.generateSequence(UserTransactionsDto.SEQUENCE_NAME);
            userTransactionsDto = new UserTransactionsDto(idTRansaction, "SafeWallet", to, amount, date);
            return userTransactionsDto;
        }
        Long idTRansaction = sequenceGeneratorService.generateSequence(UserTransactionsDto.SEQUENCE_NAME);
        String userFrom = findUserForTransfer(from).getName();
        userFrom = userFrom.substring(0, 1).toUpperCase() + userFrom.substring(1);
        String userTo = findUserForTransfer(to).getName();

        userTo = userTo.substring(0, 1).toUpperCase() + userTo.substring(1);

        userTransactionsDto = new UserTransactionsDto(idTRansaction, userTo, userFrom, amount, date);

        return userTransactionsDto;
    }

    public UserTransactionsDto SettingNewCardTransaction(UserTransactionsDto userTransactionsDto) {
        // cardNumber, from, amount,  -> FRONT
        userTransactionsDto.setCardNumber(userTransactionsDto.getCardNumber());
        userTransactionsDto.setFrom("Cuenta propia.");
        userTransactionsDto.setAmount(userTransactionsDto.getAmount());
        userTransactionsDto.setTransferDetail(UserTransactionsDto.TransferDetail.DEPOSITCARD);
        // id, date -> BACK
        Long IdTransaction = sequenceGeneratorService.generateSequence(UserTransactionsDto.SEQUENCE_NAME);
        userTransactionsDto.setId(IdTransaction);
        LocalDateTime date = LocalDateTime.now();
        userTransactionsDto.setDate(date);
        userTransactionsDto.setTo("Cuenta propia.");
        return userTransactionsDto;
    }


    public int depositMoneyFromCard(UserDto userDto, UserTransactionsDto userTransactionsDto) {

        String cardNumber = userTransactionsDto.getCardNumber();
        List<Card> cardList = userDto.getCards();


        try {
            for (Card c : cardList) {
                if (c.getCardNumber().equals(cardNumber)) {
                    Double balance = userDto.getBalance();
                    balance += userTransactionsDto.getAmount();
                    userDto.setBalance(balance);
                    UserTransactionsDto transaction = SettingNewCardTransaction(userTransactionsDto);
                    userDto.getTransactions().add(transaction);
                    iTransferRepository.save(userDto);
                    return 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public UserDto findUserForTransfer(String data) {
        UserDto userDto;
        userDto = iTransferRepository.findUserByCbu(data);
        if (userDto == null) {
            userDto = iTransferRepository.findUserByCvu(data);
            if (userDto == null) {
                userDto = iTransferRepository.findUserByAlias(data);
                return userDto;
            }
            return userDto;
        }
        return userDto;

    }

}
