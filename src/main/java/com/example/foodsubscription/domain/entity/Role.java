package com.example.foodsubscription.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="roles")
@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String roleName;

    public Role(String roleName){
        this.roleName = roleName;
    }
}
