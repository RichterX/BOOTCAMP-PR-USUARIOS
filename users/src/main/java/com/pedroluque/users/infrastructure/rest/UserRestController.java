package com.pedroluque.users.infrastructure.rest;

import com.pedroluque.users.application.dto.UserDto;
import com.pedroluque.users.service.UserService;
import com.pedroluque.users.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600) //Nos permite hacer peticiones desde cualquier lugar
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

    @GetMapping(value="/search", produces = "application/json")
    public ResponseEntity<Page<UserDto>> getUsersByCriteria(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) UserType role,
            Pageable pageable) {
        Page<UserDto> users = userService.getUsersByCriteria(name, surname, role, pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //Editar un usuario
    @CrossOrigin(origins = "*")
    @PatchMapping(path="/users", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserDto> updateUserById(@RequestBody UserDto userDto)
    {
        userDto = userService.createUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
