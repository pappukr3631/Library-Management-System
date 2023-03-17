package com.selfLearning.LibraryManagementSystem.Service;

import com.selfLearning.LibraryManagementSystem.DTOs.RequestDtos.AddAuthorRequestDto;
import com.selfLearning.LibraryManagementSystem.DTOs.ResponseDtos.AuthorResponseDto;
import com.selfLearning.LibraryManagementSystem.DTOs.ResponseDtos.BookResponseDto;
import com.selfLearning.LibraryManagementSystem.Entities.Author;
import com.selfLearning.LibraryManagementSystem.Entities.Book;
import com.selfLearning.LibraryManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(AddAuthorRequestDto addAuthorRequestDto) {
        //We cannot save author Dto object directly in the DB so, convert it to author object
        //Making new author object
        Author author = new Author();
        //Set it's attributes
        author.setName(addAuthorRequestDto.getName());
        author.setAge(addAuthorRequestDto.getAge());
        author.setEmail(addAuthorRequestDto.getEmail());

        //final step  Save the entity
        authorRepository.save(author);
        return "Author added successfully";
    }

    public List<AuthorResponseDto> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();
        //No Author in the repo
        if(authorList.size() == 0)
            return null;

        List<AuthorResponseDto> authorResponseDtoList = new ArrayList<>();
        //Conversion of author to authorDto
        for(Author author : authorList) {
            AuthorResponseDto authorResponseDto = new AuthorResponseDto();
            //set all the attributes
            authorResponseDto.setName(author.getName());
            authorResponseDto.setAge(author.getAge());
            authorResponseDto.setEmail(author.getEmail());
            authorResponseDto.setNoOfBooks(author.getBooksWritten().size());
            //add it to the list
            authorResponseDtoList.add(authorResponseDto);
        }
        return authorResponseDtoList;
    }

    public AuthorResponseDto findAuthor(int authorId) {
        Author author;
        try {
            author = authorRepository.findById(authorId).get();
        }catch (Exception e) {
//            throw new Exception("Invalid Author Id");
            System.out.println("Invalid Author Id. " + e.getMessage());
            return null;
        }

        //we have the author object
        //convert it to dto and return
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        //set it's attributes
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setEmail(author.getEmail());
        authorResponseDto.setNoOfBooks(author.getBooksWritten().size());

        return authorResponseDto;
    }

    public List<BookResponseDto> getAllBooksByAuthor(int id) {
        Author author;
        try {
            author = authorRepository.findById(id).get();
        }
        catch (Exception e) {
            System.out.println("Invalid authorId. " + e.getMessage());
            return null;
        }

        List<Book> bookList = author.getBooksWritten();

        //we need list of bookResponseDto
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        for(Book currentBook : bookList) {
            BookResponseDto bookResponseDto = new BookResponseDto();
            //set attributes
            bookResponseDto.setTitle(currentBook.getTitle());
            bookResponseDto.setPages(currentBook.getPages());
            bookResponseDto.setPrice(currentBook.getPrice());
            bookResponseDto.setGenre(currentBook.getGenre());
            bookResponseDto.setIssued(currentBook.isIssued());
            bookResponseDto.setAuthorName(author.getName());
            //add it to the list
            bookResponseDtoList.add(bookResponseDto);
        }

        return bookResponseDtoList;
    }

    public AuthorResponseDto updateEmail(int authorId, String newEmail) {
        Author author;
        try {
            author = authorRepository.findById(authorId).get();
        }catch (Exception e) {
            System.out.println("Invalid AuthorId." + e.getMessage());
            return null;
        }

        //update emailId
        author.setEmail(newEmail);
        //save
        author = authorRepository.save(author);

        //convert it to dto and return
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        //setting attributes
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setEmail(author.getEmail());
        authorResponseDto.setNoOfBooks(author.getBooksWritten().size());

        return authorResponseDto;
    }
}
