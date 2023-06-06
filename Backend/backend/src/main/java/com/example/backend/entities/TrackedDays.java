package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tracked_days")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackedDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "marked", nullable = false)
    private Boolean marked;
    @Column(name = "marking_day")
    private Date markingDate;
}
