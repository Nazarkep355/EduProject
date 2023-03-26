package com.example.eduproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name="users")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
}
