package com.example.eduproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name="chats")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class Chat {
    @Id
    private Long id;
    @Column
    private String name;
    @ManyToMany
    private List<User> users;
    @OneToMany(mappedBy = "chat")
    private List<Message> messages;
}
