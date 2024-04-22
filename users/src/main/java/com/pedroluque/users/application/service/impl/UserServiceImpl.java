package com.pedroluque.users.application.service.impl;

import com.pedroluque.users.application.dto.UserDto;
import com.pedroluque.users.application.mapper.UserMapper;
import com.pedroluque.users.application.service.UserService;
import com.pedroluque.users.domain.entity.User;
import com.pedroluque.users.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    private final UserPersistence userPersistence;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserPersistence userPersistence, UserMapper userMapper)
    {
        this.userPersistence = userPersistence;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public List<UserDto> getAllUsers()
    {
        List<User> users = userPersistence.getAllUsers();
        return userMapper.toDto(users);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getUserById(Long UserId)
    {
        return this.userPersistence.getUserById(UserId).map(userMapper::toDto);
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto)
    {
        var user = userMapper.toEntity(userDto);
        user = this.userPersistence.createUser(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUserById(Long UserId)
    {
        userPersistence.deleteUserById(UserId);
    }
}
