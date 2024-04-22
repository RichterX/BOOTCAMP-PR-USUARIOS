package com.pedroluque.users.infrastructure.persistence;

import com.pedroluque.users.domain.entity.User;
import com.pedroluque.users.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserPersistenceImpl implements UserPersistence
{
    private final UserRepository userRepository;

    @Autowired
    public UserPersistenceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers()
    {
        return this.userRepository.findAll();
    }

    @Override
    public Optional getUserById(Long UserId)
    {
        return this.userRepository.findById(UserId);
    }

    @Override
    public User createUser(User user)
    {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long UserId)
    {
        this.userRepository.deleteById(UserId);
    }
}
