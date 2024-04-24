package com.pedroluque.users.application.dto;

import com.pedroluque.users.domain.UserType;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable
{
    private Long id;
    private String name;
    private String surname;
    private String email;
    private UserType role;

    public UserDto()
    {
        // Constructor vacío para Serializables
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public UserType getRole()
    {
        return role;
    }

    public void setRole(UserType role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name) && Objects.equals(surname, userDto.surname) && Objects.equals(email, userDto.email) && role == userDto.role;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, surname, email, role);
    }

    @Override
    public String toString()
    {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
