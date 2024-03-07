package com.example.Transfers.repository;


import com.example.Transfers.model.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITransferRepository extends MongoRepository<UserDto, Long> {

    UserDto findUserByEmail(String email);
    UserDto findUserByCbu(String cbu);

    void deleteByEmail(String email);

    UserDto findUserById(Long id);









}
