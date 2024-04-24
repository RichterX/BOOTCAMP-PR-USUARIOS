package com.pedroluque.users.infrastructure.mapper;

import com.pedroluque.users.application.dto.UserDto;
import com.pedroluque.users.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User>
{
}
