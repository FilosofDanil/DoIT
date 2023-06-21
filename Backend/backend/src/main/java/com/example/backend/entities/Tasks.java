package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "credentials_id", nullable = false)
    private User user;
    @Column(name = "task_name", nullable = false)
    private String name;
    @JsonIgnore
    @JsonBackReference
    @OneToOne(mappedBy = "task", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private DailyTasks dailyTask;

    @JsonIgnore
    @JsonBackReference
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Subtasks> subtasks;

}
