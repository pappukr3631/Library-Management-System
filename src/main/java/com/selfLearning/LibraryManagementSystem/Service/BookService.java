package com.selfLearning.LibraryManagementSystem.Service;

import com.selfLearning.LibraryManagementSystem.DTOs.RequestDtos.AddBookRequestDto;
import com.selfLearning.LibraryManagementSystem.Entities.Author;
import com.selfLearning.LibraryManagementSystem.Entities.Book;
import com.selfLearning.LibraryManagementSystem.Repository.AuthorRepository;
import com.selfLearning.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(AddBookRequestDto addBookRequestDto) {
        Author author;
        try {
            author = authorRepository.findById(addBookRequestDto.getAuthorId()).get();
        } catch (Exception e) {
            System.out.println("Invalid AuthorID. " + e.getMessage());
            return "Author Not found!";
        }

        //we have author object
        // make Book object
        Book book = new Book();
        //set all it's attributes
        book.setTitle(addBookRequestDto.getTitle());
        book.setPages(addBookRequestDto.getPages());
        book.setPrice(addBookRequestDto.getPrice());
        book.setGenre(addBookRequestDto.getGenre());
        book.setIssued(false);
        book.setAuthor(author);

        //add this book to the author's booksWrittenList

        author.getBooksWritten().add(book);
        //save author and book will be saved automatically by Cascading Effect
        authorRepository.save(author);

        return "Book added Successfully";
    }
}
