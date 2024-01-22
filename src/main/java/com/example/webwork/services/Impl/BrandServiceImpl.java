package com.example.webwork.services.Impl;

import com.example.webwork.dto.ShowDetailedBrandInfoDto;
import com.example.webwork.dto.ShowModelInfoDto;
import com.example.webwork.dto.UpdateBrandDto;
import com.example.webwork.except.BrandNotFoundException;
import com.example.webwork.dto.BrandDTO;
import com.example.webwork.models.Brand;
import com.example.webwork.models.Model;
import com.example.webwork.repo.BrandRepository;
import com.example.webwork.repo.ModelRepository;
import com.example.webwork.services.BrandService;
import com.example.webwork.util.ValidationUtil;
import com.example.webwork.dto.AddBrandDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private ModelRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

    private final ValidationUtil validationUtil;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public List<BrandDTO> getAll() {
        return brandRepository.findAll().stream().map((s) -> modelMapper.map(s, BrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<Brand> findById(String id) {
        return Optional.empty();
    }


    @Override
    public List<ShowModelInfoDto> getModelsByBrand(String brandName) {
        Brand brand = brandRepository.findByName(brandName).orElseThrow(() -> new BrandNotFoundException("Brand not found with name: " + brandName));

        List<Model> models = modelRepository.findByBrand(brand);
        return models.stream()
                .map(model -> modelMapper.map(model, ShowModelInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void expel(String id) {
        if (brandRepository.findById(id).isPresent()) {
            brandRepository.deleteById(id);
        } else {
            throw new BrandNotFoundException(id);
        }
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
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

    @Override
    public void updateBrand(String name, UpdateBrandDto updateBrandDto) {
        brandRepository.findByName(name).ifPresent(brand -> {
            brand.setName(updateBrandDto.getName());
            brand.setDescription(updateBrandDto.getDescription());
            brand.setModified(LocalDateTime.now());
            brandRepository.save(brand);
            logger.info("User {} updated successfully", brand);
        });
    }
}
