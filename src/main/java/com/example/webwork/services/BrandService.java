package com.example.webwork.services;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.dtoss.AddBrandDto;
import com.example.webwork.dto.dtoss.AddUserDto;
import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;
import com.example.webwork.dto.dtoss.ShowModelInfoDto;
import com.example.webwork.models.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    BrandDTO registerBrand(BrandDTO brand);
    ShowDetailedBrandInfoDto brandDetails(String brandName);
    List<BrandDTO> getAll();
    List<ShowModelInfoDto> getModelsByBrand(String brandName);
    Optional<Brand> findById(String id);
    void addBrand(AddBrandDto brandModel);
    void expel(String id);

    List<ShowDetailedBrandInfoDto> allBrands();

    void removeBrand(String brandName);
}
