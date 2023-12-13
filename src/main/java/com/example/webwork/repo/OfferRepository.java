package com.example.webwork.repo;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.OfferDTO;
import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;
import com.example.webwork.dto.dtoss.ShowDetailedOfferDto;
import com.example.webwork.dto.dtoss.ShowInfoOffer;
import com.example.webwork.models.Brand;
import com.example.webwork.models.Model;
import com.example.webwork.models.Offer;
import com.example.webwork.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, String> {
    List<Offer> findByUsersId(String id);
    @Query("SELECT o.model.brand, COUNT(o) " +
            "FROM Offer o " +
            "GROUP BY o.model.brand " +
            "ORDER BY COUNT(o) DESC")
    List<Object[]> findBestSellingBrand();
    Optional<Offer> findOfferByUsersId(String id);
}
