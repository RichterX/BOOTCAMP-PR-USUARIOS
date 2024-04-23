package com.pedroluque.users.domain.entity;

import com.pedroluque.users.domain.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //El GenerationType.SEQUENCE no es soportado por todas las bases de datos
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "email", nullable = false, length = 40)
    private String email;

    @Column(name = "role", nullable = false, length = 20)
    private UserType role;


    public User()
    {
        //Constructor vacio para JPA
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
}
