package com.selfLearning.LibraryManagementSystem.Entities;

import com.selfLearning.LibraryManagementSystem.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    @CreationTimestamp
    private Date issueDate;

    @UpdateTimestamp
    private Date updatedOn;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    //Object Relational Mapping (ORM)

    //1. to book
    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    List<Book> issuedBookList = new ArrayList<>();

    //2. to Student
    @OneToOne
    @JoinColumn
    private Student student;

    //3. to transaction
    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    private List<Transaction> transactionsByCard = new ArrayList<>();
}
