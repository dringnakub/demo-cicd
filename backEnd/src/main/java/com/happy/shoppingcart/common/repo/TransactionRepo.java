package com.happy.shoppingcart.common.repo;

import com.happy.shoppingcart.common.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
}
