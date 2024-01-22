package com.example.webwork.repo;

import com.example.webwork.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    Optional<Brand> findByName(String name);
    Optional<Brand> findById(String id);
    @Modifying
    @Transactional
    Optional<Brand> deleteByName(String brandName);
}
