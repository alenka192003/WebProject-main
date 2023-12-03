package com.example.webwork.services;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.OfferDTO;
import com.example.webwork.dto.UsersDTO;
import com.example.webwork.dto.dtoss.*;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    OfferDTO registerOffer(OfferDTO offer);
    List<OfferDTO> getAll();
    Optional<OfferDTO> findById(String id);
    void expel(String id);
    OfferDTO update(OfferDTO offer);
    List<ShowInfoOffer> allOffers();

    ShowDetailedOfferDto offerDetails(ModelDTO model);
    void addOffer(AddOfferDto offerModel);

}
