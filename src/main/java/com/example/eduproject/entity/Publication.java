package com.example.eduproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name="publications")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "group")
    private Group group;
    @ManyToOne
    private User author;
    @Column
    private String header;
    @Column
    private String content;
    @Column
    private String attachment;
}
