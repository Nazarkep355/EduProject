package com.example.eduproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Table(name="groups")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class Group {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private GroupAccessibility accessibility;
    @Column
    private String password;
    @ManyToMany
    private List<User> teachers;
    @ManyToMany
    private List<User> students;
    @OneToMany(mappedBy = "group")
    private List<Publication> publications;
}
