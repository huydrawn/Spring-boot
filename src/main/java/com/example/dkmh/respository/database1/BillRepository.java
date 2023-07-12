package com.example.dkmh.respository.database1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dkmh.model.bill.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

}
