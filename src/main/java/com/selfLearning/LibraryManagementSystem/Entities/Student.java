package com.selfLearning.LibraryManagementSystem.Entities;

import com.selfLearning.LibraryManagementSystem.Enums.Department;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admissionId;

    private String name;

    private int age;

    @Column(unique = true)
    private String email;

    private String mobileNo;

    @Enumerated(EnumType.STRING)
    private Department department;

    //Object Relational Mapping
    //to LibraryCard
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private LibraryCard libraryCard;
}
