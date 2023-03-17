package com.selfLearning.LibraryManagementSystem.Controller;

import com.selfLearning.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public String issueBook(@RequestParam int cardId, @RequestParam int bookId) {
        return transactionService.issueBook(cardId,bookId);
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam int bookId) throws Exception {
        return transactionService.returnBook(bookId);
    }
}
