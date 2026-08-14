package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.Purchase;
import com.tss.URL_Shortening.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {



    List<Purchase> findByUser(User user);

}
