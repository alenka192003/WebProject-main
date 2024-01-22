package com.example.webwork.services;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.OfferDTO;
import com.example.webwork.dto.ShowDetailedOfferDto;
import com.example.webwork.dto.ShowInfoOffer;
import com.example.webwork.dto.AddOfferDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OfferService {
    List<OfferDTO> getAll();
    Optional<OfferDTO> findById(String id);
    void expel(String id);
    OfferDTO update(OfferDTO offer);
    List<ShowInfoOffer> allOffers();
    void removeOffer(String id);
    ShowDetailedOfferDto offerDetails(String id);
    void addOffer(AddOfferDto offerModel);
    BrandDTO getBestSellingBrand();
    BigDecimal getTotalProfit();
}
