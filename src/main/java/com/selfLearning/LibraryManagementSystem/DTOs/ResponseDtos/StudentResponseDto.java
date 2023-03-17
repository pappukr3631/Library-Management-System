package com.selfLearning.LibraryManagementSystem.DTOs.ResponseDtos;

import com.selfLearning.LibraryManagementSystem.Enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponseDto {
    private String name;
    private int age;
    private String email;
    private String mobileNe;
    private Department department;
    private int libraryCardId;
}
