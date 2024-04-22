package com.pedroluque.users.infrastructure.rest;

import com.pedroluque.users.application.dto.UserDto;
import com.pedroluque.users.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController
{
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping(value="/", produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        var users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value="/{userId}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId)
    {
        return userService
                .getUserById(userId)
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping(value="/users", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        userDto = userService.createUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{userId}", produces = "application/json")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId)
    {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
