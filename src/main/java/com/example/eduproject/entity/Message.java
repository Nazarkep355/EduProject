package com.example.eduproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name="messages")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
