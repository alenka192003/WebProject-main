package com.example.webwork.services;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.ShowModelInfoDto;
import com.example.webwork.dto.AddModelDto;

import java.util.List;
import java.util.Optional;

public interface ModelService {
    ModelDTO registerModel(ModelDTO model);

    List<ModelDTO> getAll();

    Optional<ModelDTO> findById(String id);

    void expel(String id);
    ModelDTO update(ModelDTO model);
    List<ModelDTO> findAllByName(String name);
    List<ModelDTO> getAllModelsSortedByYear();
    List<ShowModelInfoDto> allModels();
    void addModel(AddModelDto modelModel);

    ShowModelInfoDto modelDetails(String modelName);
    void removeModel(String modelName);
}
