package com.pedroluque.users.service;

import com.pedroluque.users.application.dto.UserDto;
import com.pedroluque.users.domain.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    List<UserDto> getAllUsers();
    Optional<UserDto> getUserById(Long userId);
    UserDto createUser(UserDto user);
    void deleteUserById(Long userId);
    Page<UserDto> getUsersByCriteria(String name, String surname, UserType role, Pageable pageable);

    UserDto editUserById(UserDto userDto);
}
