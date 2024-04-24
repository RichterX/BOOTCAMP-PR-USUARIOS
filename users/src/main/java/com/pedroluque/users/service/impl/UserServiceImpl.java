package com.pedroluque.users.application.service.impl;

import com.pedroluque.users.application.dto.UserDto;
import com.pedroluque.users.application.mapper.UserMapper;
import com.pedroluque.users.application.service.UserService;
import com.pedroluque.users.domain.UserType;
import com.pedroluque.users.domain.entity.User;
import com.pedroluque.users.infrastructure.repository.UserRepository;
import com.pedroluque.users.infrastructure.specs.UserSpecification;
import com.pedroluque.users.infrastructure.specs.shared.SearchCriteria;
import com.pedroluque.users.infrastructure.specs.shared.SearchOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers()
    {
        List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    @Override
    public Optional<UserDto> getUserById(Long userId)
    {
        return userRepository
                .findById(userId)
                .map(userMapper::toDto);
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto)
    {
        var user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUserById(Long userId)
    {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> getUsersByCriteria(String name, String surname, UserType role, Pageable pageable) {
        List<SearchCriteria> criteriaList = new ArrayList<>();

        if (name != null) {
            criteriaList.add(new SearchCriteria("name", name, SearchOperation.MATCH));
        }
        if (surname != null) {
            criteriaList.add(new SearchCriteria("surname", surname, SearchOperation.MATCH));
        }
        if (role != null) {
            criteriaList.add(new SearchCriteria("role", role, SearchOperation.EQUAL));
        }

        UserSpecification spec = new UserSpecification(criteriaList);
        Page<User> users = userRepository.findAll(spec, pageable);
        return users.map(userMapper::toDto);
    }
}
