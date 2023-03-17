package com.selfLearning.LibraryManagementSystem.Controller;

import com.selfLearning.LibraryManagementSystem.DTOs.RequestDtos.AddAuthorRequestDto;
import com.selfLearning.LibraryManagementSystem.DTOs.ResponseDtos.AuthorResponseDto;
import com.selfLearning.LibraryManagementSystem.DTOs.ResponseDtos.BookResponseDto;
import com.selfLearning.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    /* Working Fine */
    @PostMapping("/add")
    public String addAuthor(@RequestBody AddAuthorRequestDto addAuthorRequestDto) {
        return authorService.addAuthor(addAuthorRequestDto);
    }

    /* Working Fine */
    @GetMapping("/get_all_authors")
    public List<AuthorResponseDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }
    /* Working Fine */
    @GetMapping("/find-author")
    public AuthorResponseDto findAuthor(@RequestParam int authorId) {
        return authorService.findAuthor(authorId);
    }
    /* Working Fine */
    @GetMapping("/all_books_by_author")
    public List<BookResponseDto> getAllBooksByAuthor(@RequestParam int authorId) {
        return authorService.getAllBooksByAuthor(authorId);
    }
    /* Working Fine */
    @PostMapping("/update_email")
    public AuthorResponseDto updateEmail(@RequestParam("authorId") int authorId, @RequestParam("newEmail") String newEmail) {
        return authorService.updateEmail(authorId,newEmail);
    }
}
