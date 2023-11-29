package com.example.webwork.repo;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;
import com.example.webwork.dto.dtoss.ShowDetailedOfferDto;
import com.example.webwork.models.Model;
import com.example.webwork.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, String> {
    Optional<Offer> findByModel(ModelDTO model);

}