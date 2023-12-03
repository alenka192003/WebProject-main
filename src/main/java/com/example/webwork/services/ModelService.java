package com.example.webwork.services;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.UsersDTO;
import com.example.webwork.dto.dtoss.*;
import com.example.webwork.enums.CategoryEnum;
import com.example.webwork.models.Model;

import java.util.List;
import java.util.Optional;

public interface ModelService {
    ModelDTO registerModel(ModelDTO model);

    List<ModelDTO> getAll();

    Optional<ModelDTO> findById(String id);

    void expel(String id);

    ModelDTO update(ModelDTO model);
    List<ModelDTO> findAllByName(String name);

    ModelDTO registerModel_1(AddModelDto model);
    List<ShowModelInfoDto> allModels();

    ShowModelInfoDto modelDetails(String modelName);
    void removeModel(String modelName);
}
