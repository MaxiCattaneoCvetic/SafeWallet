package com.safewallet.userDataService.repository;

import com.safewallet.userDataService.model.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;


//Se utiliza para interactuar con la base de datos y realizar operaciones CRUD
public interface IUserRepository extends MongoRepository<UserDto,Long> {



}



