package com.selfLearning.LibraryManagementSystem.DTOs.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddAuthorRequestDto {
    private String name;
    private int age;
    private String email;
}
