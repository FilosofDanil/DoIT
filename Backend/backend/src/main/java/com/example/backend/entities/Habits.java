package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "habits")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Habits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "credentials_id", nullable = false)
    private User user;
    @Column(name = "habit_name", nullable = false)
    private String name;
    @Column(name = "day_count", nullable = false)
    private Integer day_count;
    @Column(name = "description")
    private String description ;
    @Column(name = "completed", nullable = false)
    private Boolean completed;
}
