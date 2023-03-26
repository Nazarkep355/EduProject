package com.example.eduproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name="messages")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class Message {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "chat")
    private Chat chat;
}
