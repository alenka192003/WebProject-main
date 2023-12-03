package com.example.webwork.repo;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;
import com.example.webwork.models.Brand;
import com.example.webwork.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, String> {
    Optional<Brand> findByName(String value);
    @Modifying
    @Transactional
    Optional<Brand> deleteByName(String brandName);
}
