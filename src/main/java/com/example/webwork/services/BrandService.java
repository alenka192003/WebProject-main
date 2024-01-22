package com.example.webwork.services;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.ShowDetailedBrandInfoDto;
import com.example.webwork.dto.ShowModelInfoDto;
import com.example.webwork.dto.UpdateBrandDto;
import com.example.webwork.models.Brand;
import com.example.webwork.dto.AddBrandDto;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    ShowDetailedBrandInfoDto brandDetails(String brandName);
    List<BrandDTO> getAll();
    List<ShowModelInfoDto> getModelsByBrand(String brandName);
    Optional<Brand> findById(String id);
    void addBrand(AddBrandDto brandModel);
    void expel(String id);

    List<ShowDetailedBrandInfoDto> allBrands();
    void removeBrand(String brandName);
    void updateBrand(String name, UpdateBrandDto updateBrandDto);
}
