package com.example.webwork.services;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.dtoss.AddBrandDto;
import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    BrandDTO registerBrand(BrandDTO brand);
    BrandDTO registerBrand_1(AddBrandDto brand);

    ShowDetailedBrandInfoDto brandDetails(String brandName);
    List<BrandDTO> getAll();

    Optional<BrandDTO> findById(String id);

    void expel(String id);

    List<ShowDetailedBrandInfoDto> allBrands();

    BrandDTO update(BrandDTO brand);
}