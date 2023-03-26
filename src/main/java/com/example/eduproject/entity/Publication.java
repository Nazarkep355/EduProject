package com.example.eduproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name="publications")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class Publication {
    @Id
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
