package com.selfLearning.LibraryManagementSystem.DTOs.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class AuthorResponseDto {
//    private int id;
    private String name;
    private int age;
    private String email;
    //No. of books available in the Library
    private int noOfBooks;
}
