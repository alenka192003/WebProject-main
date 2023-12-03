package com.example.webwork.services.Impl;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.UsersDTO;
import com.example.webwork.dto.dtoss.*;
import com.example.webwork.except.OfferConflictException;
import com.example.webwork.except.OfferNotFoundException;
import com.example.webwork.dto.OfferDTO;
import com.example.webwork.except.UsersConflictException;
import com.example.webwork.models.Offer;
import com.example.webwork.models.Users;
import com.example.webwork.repo.ModelRepository;
import com.example.webwork.repo.OfferRepository;
import com.example.webwork.services.OfferService;
import com.example.webwork.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final ModelMapper modelMapper;
    private  OfferRepository offerRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public OfferDTO registerOffer(OfferDTO offer) {

        if(!this.validationUtil.isValid(offer)) {
            this.validationUtil
                    .violations(offer)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("не подходит");
        }

        Offer o = modelMapper.map(offer, Offer.class);
        String offerId = o.getId();
        if (offerId == null || offerRepository.findById(offerId).isEmpty()) {
            return modelMapper.map(offerRepository.save(o), OfferDTO.class);
        } else {
            throw new OfferConflictException("уже существует с таким id");
        }
    }

    @Override
    public List<OfferDTO> getAll() {
        return offerRepository.findAll().stream().map((s) -> modelMapper.map(s, OfferDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<OfferDTO> findById(String id) {
        return Optional.ofNullable(modelMapper.map(offerRepository.findById(id), OfferDTO.class));
    }

    @Override
    public void expel(String id) {
        if (offerRepository.findById(id).isPresent()) {
            offerRepository.deleteById(id);
        } else {
            throw new OfferNotFoundException(id);
        }
    }

    @Override
    public OfferDTO update(OfferDTO offer) {

        if(!this.validationUtil.isValid(offer)) {
            this.validationUtil
                    .violations(offer)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("не подходит");
        }

        if (offerRepository.findById(offer.getId()).isPresent()) {
            return modelMapper.map(offerRepository.save(modelMapper.map(offer, Offer.class)), OfferDTO.class);
        } else {
            throw new OfferNotFoundException(offer.getId());
        }
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public void addOffer(AddOfferDto offerModel) {
        offerRepository.saveAndFlush(modelMapper.map(offerModel, Offer.class));
    }

    public List<ShowInfoOffer> allOffers() {
        return offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, ShowInfoOffer.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShowDetailedOfferDto offerDetails(ModelDTO model) {
        return modelMapper.map(offerRepository.findByModel(model).orElse(null), ShowDetailedOfferDto.class);
    }


}
