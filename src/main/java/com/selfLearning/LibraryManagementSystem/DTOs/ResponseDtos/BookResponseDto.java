package com.selfLearning.LibraryManagementSystem.DTOs.ResponseDtos;

import com.selfLearning.LibraryManagementSystem.Enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponseDto {
    private String title;
    private int pages;
    private int price;
    private Genre genre;
    private String authorName;
    private boolean isIssued;
}
