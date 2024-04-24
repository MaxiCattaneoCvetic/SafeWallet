package com.safewallet.userDataService.repository;

import com.safewallet.userDataService.model.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;


//Se utiliza para interactuar con la base de datos y realizar operaciones CRUD
public interface IUserRepository extends MongoRepository<UserDto,Long> {

    UserDto findByUsername(String username);
    UserDto findByDni(String dni);

    UserDto findByCbu(String cbu);
    UserDto findByAlias(String alias);
    UserDto findByCvu(String cvu);


}



