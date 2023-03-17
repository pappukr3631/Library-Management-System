package com.selfLearning.LibraryManagementSystem.Service;

import com.selfLearning.LibraryManagementSystem.Entities.Book;
import com.selfLearning.LibraryManagementSystem.Entities.LibraryCard;
import com.selfLearning.LibraryManagementSystem.Entities.Transaction;
import com.selfLearning.LibraryManagementSystem.Enums.CardStatus;
import com.selfLearning.LibraryManagementSystem.Enums.TransactionStatus;
import com.selfLearning.LibraryManagementSystem.Repository.BookRepository;
import com.selfLearning.LibraryManagementSystem.Repository.CardRepository;
import com.selfLearning.LibraryManagementSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;
    /* Working Fine */
    public String issueBook(int cardId, int bookId) {
        //Make a new transaction
        Transaction transaction = new Transaction();
        //set some independent attributes
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setIssueOperation(true);

        //Getting card and book object
        LibraryCard card;
        try {
            card = cardRepository.findById(cardId).get();
            transaction.setLibraryCard(card);
            //Check if the card is valid
            if(card.getCardStatus() != CardStatus.ACTIVATED) {
                throw new Exception("Card not valid.");
            }
        }catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            System.out.println("Invalid cardId! " + e.getMessage());
            return "Invalid cardId \nIssue operation failed!";
        }
        Book book;
        try {
            book = bookRepository.findById(bookId).get();
            transaction.setBook(book);
            //If Book is already issued
            if(book.isIssued()) {
                transaction.setTransactionStatus(TransactionStatus.FAILED);
                transactionRepository.save(transaction);
                return "Requested book is not available!";
            }
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            System.out.println("Invalid BookId! " + e.getMessage());
            return "Invalid bookId! \nIssue operation failed!";
        }

        //Book can be issued successfully
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);

        //add transaction to the card and book
        book.setIssued(true);
        book.setLibraryCard(card);
        book.getTransactionsOnBook().add(transaction);
        card.getTransactionsByCard().add(transaction);
        card.getIssuedBookList().add(book);

        //save card, -- book and transaction should be saved by itself
        cardRepository.save(card);

        return book.getTitle() + " is successfully issued to " + card.getStudent().getName();
    }

    /* Not Fine Saving transaction twice */
    public String returnBook(int bookId) throws Exception {
        Transaction transaction = new Transaction();

        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssueOperation(false);



        Book book;
        try {
            book = bookRepository.findById(bookId).get();
        }catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            System.out.println("Invalid bookId! " + e.getMessage());
            throw new Exception("Invalid bookId");
        }
        if(!book.isIssued()) {
            return "Book already returned";
        }
        LibraryCard card;
        try {
            card = cardRepository.findById(book.getLibraryCard().getCardId()).get();
        }catch (Exception e) {
            return "Card not found.";
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        transaction.setBook(book);
        transaction.setLibraryCard(card);


        book.setIssued(false);
        book.getTransactionsOnBook().add(transaction);
        card.getTransactionsByCard().add(transaction);
        book.setLibraryCard(null);

//        bookRepository.save(book);
        cardRepository.save(card);
        return book.getTitle() + " returned successfully.";
    }
}
