package com.selfLearning.LibraryManagementSystem.Entities;

import com.selfLearning.LibraryManagementSystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int pages;
    private int price;
    private boolean isIssued;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    //Relational Mappings

    //1. to Author
    @ManyToOne
    @JoinColumn
    private Author author;

    //2. to LibraryCard
    @ManyToOne
    @JoinColumn
    private LibraryCard libraryCard;

    //3. to Transaction
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> transactionsOnBook = new ArrayList<>();
}
