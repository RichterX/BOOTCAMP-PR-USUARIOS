package com.pedroluque.users.application.service;

import com.pedroluque.users.application.dto.UserDto;
import com.pedroluque.users.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    List<UserDto> getAllUsers();
    Optional<UserDto> getUserById(Long UserId);
    UserDto createUser(UserDto user);
    void deleteUserById(Long UserId);
}
