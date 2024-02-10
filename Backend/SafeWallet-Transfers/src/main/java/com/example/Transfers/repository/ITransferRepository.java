package com.example.Transfers.repository;

import com.example.Transfers.model.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITransferRepository extends MongoRepository<UserDto, Long> {
//    UserDto findByCbu(String cbu);

    UserDto findUserByEmail(String email);





}
