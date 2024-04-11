package com.example.example_2_seminar_5.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    // Устанавливаем значение статуса по умолчанию
    @PrePersist
    public void setDefaultStatus() {
        if (status == null) {
            status = TaskStatus.NOT_STARTED;
        }
    }

    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;

}

