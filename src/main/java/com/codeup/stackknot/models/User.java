package com.codeup.stackknot.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 30, unique = true)
    @NotBlank(message = "username cannot be blank")
    @Size(min = 3, message = "username must be at least 3 characters")
    private String username;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "email cannot be blank")
    @Email(message = "invalid email, please try again")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "password cannot be blank")
    @Size(min = 7, message = "password must be at least 7 characters")
    private String password;

    @Column(length = 50)
    @NotBlank(message = "first name cannot be blank")
    @Size(min = 1, message = "first name must be at least 1 character")
    private String firstName;

    @Column(length = 50)
    @NotBlank(message = "last name cannot be blank")
    @Size(min = 1, message = "username must be at least 1 character")
    private String lastName;

    @Column
    private Boolean isAdmin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Set> sets;


    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String username, String email, String password, String firstName, String lastName, Boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
    }

    public User(long id, String username, String email, String password, String firstName, String lastName, Boolean isAdmin, List<Set> sets) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.sets = sets;
    }

    public User(User copy) {
        id = copy.id;
        username = copy.username;
        email = copy.email;
        password = copy.password;
        firstName = copy.firstName;
        lastName = copy.lastName;
        isAdmin = copy.isAdmin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
