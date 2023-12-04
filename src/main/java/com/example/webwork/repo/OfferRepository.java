package com.example.webwork.repo;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.OfferDTO;
import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;
import com.example.webwork.dto.dtoss.ShowDetailedOfferDto;
import com.example.webwork.dto.dtoss.ShowInfoOffer;
import com.example.webwork.models.Model;
import com.example.webwork.models.Offer;
import com.example.webwork.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, String> {

    Optional<ShowInfoOffer> findOfferById(String id);

    Optional<Offer> findOfferByUsersId(String id);
}
