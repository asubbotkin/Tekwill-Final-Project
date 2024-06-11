package com.tekwill.Final_Project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserModel {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;
}
