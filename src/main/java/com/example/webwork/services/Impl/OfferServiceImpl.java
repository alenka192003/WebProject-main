package com.example.webwork.services.Impl;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.dtoss.*;
import com.example.webwork.except.OfferConflictException;
import com.example.webwork.except.OfferNotFoundException;
import com.example.webwork.dto.OfferDTO;
import com.example.webwork.models.Brand;
import com.example.webwork.models.Model;
import com.example.webwork.models.Offer;
import com.example.webwork.models.Users;
import com.example.webwork.repo.ModelRepository;
import com.example.webwork.repo.OfferRepository;
import com.example.webwork.repo.UsersRepository;
import com.example.webwork.services.OfferService;
import com.example.webwork.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final ModelMapper modelMapper;
    private OfferRepository offerRepository;
    private ModelRepository modelRepository;
    private UsersRepository usersRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository, UsersRepository usersRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.usersRepository = usersRepository;
        this.validationUtil = validationUtil;
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

        if (!this.validationUtil.isValid(offer)) {
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

    public List<ShowInfoOffer> allOffers() {
        return offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, ShowInfoOffer.class))
                .collect(Collectors.toList());
    }

    public ShowDetailedOfferDto offerDetails(String id) {
        return modelMapper.map(offerRepository.findById(id).orElse(null), ShowDetailedOfferDto.class);
    }

    public void addOffer(AddOfferDto offerModel) {
        Offer offer = modelMapper.map(offerModel, Offer.class);
        offer.setCreated(LocalDateTime.now());
        offer.setModel(modelRepository.findByName(offerModel.getModelName()).orElse(null));
        offer.setUsers(usersRepository.findByUserName(offerModel.getUn()).orElse(null));
        offer.setTransmissionEnum(offerModel.getTransmissionEnum());
        offer.setEngineEnum(offerModel.getEngineEnum());
        offerRepository.saveAndFlush(offer);
    }

    public void removeOffer(String id) {offerRepository.deleteById(id);}

    public BrandDTO getBestSellingBrand() {
        List<Object[]> result = offerRepository.findBestSellingBrand();
        if (result != null && !result.isEmpty()) {
            Object[] brandData = result.get(0);
            Brand brand = (Brand) brandData[0];

            BrandDTO bestSellingBrand = new BrandDTO();
            bestSellingBrand.setId(brand.getId());
            bestSellingBrand.setName(brand.getName());
            bestSellingBrand.setDescription(brand.getDescription());

            return bestSellingBrand;
        }
        return null;
    }
}
