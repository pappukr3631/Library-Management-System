package com.selfLearning.LibraryManagementSystem.DTOs.RequestDtos;

import com.selfLearning.LibraryManagementSystem.Enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddStudentRequestDto {
    private String name;
    private int age;
    private String email;
    private String mobileNo;
    private Department department;
}
