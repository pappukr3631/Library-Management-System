package com.selfLearning.LibraryManagementSystem.Entities;

import com.selfLearning.LibraryManagementSystem.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date transactionDate;

    private boolean isIssueOperation;

    //Object Relational Mapping
    //1. to LibraryCard
    @ManyToOne
    @JoinColumn
    private LibraryCard libraryCard;

    //2. to Book
    @ManyToOne
    @JoinColumn
    private Book book;
}
