package com.example.webwork.services.Impl;

import com.example.webwork.dto.dtoss.AddBrandDto;
import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;
import com.example.webwork.except.BrandConflictException;
import com.example.webwork.except.BrandNotFoundException;
import com.example.webwork.dto.BrandDTO;
import com.example.webwork.models.Brand;
import com.example.webwork.repo.BrandRepository;
import com.example.webwork.services.BrandService;
import com.example.webwork.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final ModelMapper modelMapper;
    private  BrandRepository brandRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }
    @Override
    public BrandDTO registerBrand(BrandDTO brand) {
        if (!this.validationUtil.isValid(brand)) {
            this.validationUtil
                    .violations(brand)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("не подходит");
        }
        Brand b = modelMapper.map(brand, Brand.class);
        String brandId = b.getId();
        if (brandId == null || brandRepository.findById(brandId).isEmpty()) {
            return modelMapper.map(brandRepository.save(b), BrandDTO.class);
        } else {
            throw new BrandConflictException("уже существует с таким id");
        }
    }

    @Override
    public List<BrandDTO> getAll() {
        return brandRepository.findAll().stream().map((s) -> modelMapper.map(s, BrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<BrandDTO> findById(String id) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findById(id), BrandDTO.class));
    }


    @Override
    public void expel(String id) {
        if (brandRepository.findById(id).isPresent()) {
            brandRepository.deleteById(id);
        } else {
            throw new BrandNotFoundException(id);
        }
    }

    @Override
    public BrandDTO update(BrandDTO brand) {

        if(!this.validationUtil.isValid(brand)) {
            this.validationUtil
                    .violations(brand)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("не подходит");
        }

        if (brandRepository.findById(brand.getId()).isPresent()) {
            return modelMapper.map(brandRepository.save(modelMapper.map(brand, Brand.class)), BrandDTO.class);
        } else {
            throw new BrandNotFoundException(brand.getId());
        }
    }
    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public BrandDTO registerBrand_1(AddBrandDto brand) {
        if (!this.validationUtil.isValid(brand)) {
            this.validationUtil
                    .violations(brand)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("не подходит");
        }
        Brand b = modelMapper.map(brand, Brand.class);
        String brandId = b.getId();
        if (brandId == null || brandRepository.findById(brandId).isEmpty()) {
            return modelMapper.map(brandRepository.save(b), BrandDTO.class);
        } else {
            throw new BrandConflictException("уже существует с таким id");
        }
    }

    public List<ShowDetailedBrandInfoDto> allBrands() {
        return brandRepository.findAll().stream().map(company -> modelMapper.map(company, ShowDetailedBrandInfoDto.class))
                .collect(Collectors.toList());
    }

    public ShowDetailedBrandInfoDto brandDetails(String brandName) {
        return modelMapper.map(brandRepository.findByName(brandName).orElse(null), ShowDetailedBrandInfoDto.class);
    }

    public void removeBrand(String brandName) {
        brandRepository.deleteByName(brandName);}

    public void addBrand(AddBrandDto brandDto) {
        brandDto.setCreated(LocalDateTime.now());
        brandDto.setModified(LocalDateTime.now());
        Brand brand = modelMapper.map(brandDto,Brand.class);
        brandRepository.saveAndFlush(modelMapper.map(brandDto, Brand.class));
    }
}
