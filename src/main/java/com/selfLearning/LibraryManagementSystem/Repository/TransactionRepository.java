package com.selfLearning.LibraryManagementSystem.Repository;

import com.selfLearning.LibraryManagementSystem.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
