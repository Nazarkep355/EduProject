package com.example.eduproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name="users")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class User {
    @Id
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
}
