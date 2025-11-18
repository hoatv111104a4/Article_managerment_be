package com.example.ArticleManagerment.entity;

import com.example.ArticleManagerment.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<User> users = new ArrayList<>();

}
