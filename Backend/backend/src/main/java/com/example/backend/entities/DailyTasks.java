package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "daily_tasks")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    @JoinColumn(name = "task_id", nullable = false, referencedColumnName = "id")
    private Tasks task;
    @Column(name = "today")
    private Date today;
    @Column(name = "done", nullable = false)
    private Boolean done;
}
