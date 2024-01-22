package com.example.webwork.repo;

import com.example.webwork.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
    List<Offer> findByUsersId(String id);
    @Query("SELECT o.model.brand, COUNT(o) " +
            "FROM Offer o " +
            "GROUP BY o.model.brand " +
            "ORDER BY COUNT(o) DESC")
    List<Object[]> findBestSellingBrand();
    @Query("SELECT SUM(o.price) FROM Offer o")
    List<BigDecimal> findTotalProfit();}
