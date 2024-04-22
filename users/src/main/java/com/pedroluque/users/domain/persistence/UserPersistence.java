package com.pedroluque.users.domain.persistence;

import com.pedroluque.users.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPersistence
{
    List<User> getAllUsers();
    Optional<User> getUserById(Long UserId);
    User createUser(User user);
    void deleteUserById(Long UserId);
}
