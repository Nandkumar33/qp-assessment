package com.grocery.shop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name", length = 100, unique = true)
    private String name;

    @Column(name = "user_email", length = 50, unique = true)
    private String email;

    @Column(name = "user_password", length = 100)
    private String password;

    @Column(name = "user_gender", length = 10)
    private String gender;

    @Column(name = "about", length = 1000)
    private String about;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
