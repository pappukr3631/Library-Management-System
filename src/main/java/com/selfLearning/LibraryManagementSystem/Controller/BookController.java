package com.selfLearning.LibraryManagementSystem.Controller;

import com.selfLearning.LibraryManagementSystem.DTOs.RequestDtos.AddBookRequestDto;
import com.selfLearning.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    /* Working Fine */
    @PostMapping("/add")
    public String addBook(@RequestBody AddBookRequestDto addBookRequestDto) {
        return bookService.addBook(addBookRequestDto);
    }

}
