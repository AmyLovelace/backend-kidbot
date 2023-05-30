package com.kidbot.Repositories;

import com.kidbot.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User findUserById(Long id);

}
