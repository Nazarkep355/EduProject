package com.example.eduproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name="chats")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String name;
    @ManyToMany
    private List<User> users;
    @OneToMany(mappedBy = "chat")
    private List<Message> messages;
}
