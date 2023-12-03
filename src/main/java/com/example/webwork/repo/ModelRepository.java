package com.example.webwork.repo;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.ModelDTO;
import com.example.webwork.enums.CategoryEnum;
import com.example.webwork.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, String> {

    @Query("SELECT m from Model m JOIN m.name WHERE m.name =:name")
    List<ModelDTO> findAllByName(@Param("name") String name);

    Optional<Model> findByName(String value);
    @Modifying
    @Transactional
    Optional<Model> deleteByName(String modelName);

}
