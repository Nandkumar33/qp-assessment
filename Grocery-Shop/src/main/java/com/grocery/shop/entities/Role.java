package com.grocery.shop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "role")
public class Role {
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    List<User> users = new ArrayList<>();
    @Id
    @Column(name = "role_id")
    private int id;
    @Column(name = "role_name")
    private String name;
}
