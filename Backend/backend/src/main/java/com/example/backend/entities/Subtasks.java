package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subtasks")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subtasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "task_id", nullable = false, referencedColumnName = "id")
    private Tasks task;
    @Column(name = "subtask_name", nullable = false)
    private String name;
    @Column(name = "done", nullable = false)
    private Boolean done;
}
