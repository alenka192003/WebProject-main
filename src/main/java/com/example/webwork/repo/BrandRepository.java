package com.example.webwork.repo;

import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;
import com.example.webwork.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, String> {
    Optional<ShowDetailedBrandInfoDto> findByName(String brandName);
}
