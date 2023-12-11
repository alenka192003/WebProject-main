package com.example.webwork.services.Impl;

import com.example.webwork.dto.dtoss.*;
import com.example.webwork.except.ModelConflictException;
import com.example.webwork.except.ModelNotFoundException;
import com.example.webwork.dto.ModelDTO;
import com.example.webwork.models.Model;
import com.example.webwork.repo.BrandRepository;
import com.example.webwork.repo.ModelRepository;
import com.example.webwork.services.ModelService;
import com.example.webwork.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, BrandRepository brandRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public ModelDTO registerModel(ModelDTO model) {

        if (!this.validationUtil.isValid(model)) {
            this.validationUtil
                    .violations(model)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllformedLocaleException("не подходит");
        }

        Model m = modelMapper.map(model, Model.class);
        String modelId = m.getId();
        if (modelId == null || modelRepository.findById(modelId).isEmpty()) {
            return modelMapper.map(modelRepository.save(m), ModelDTO.class);
        } else {
            throw new ModelConflictException("уже существует с таким id");
        }
    }

    @Override
    public List<ModelDTO> getAll() {
        return modelRepository.findAll().stream().map((s) -> modelMapper.map(s, ModelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<ModelDTO> findById(String id) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findById(id), ModelDTO.class));
    }


    @Override
    public void expel(String id) {
        if (modelRepository.findById(id).isPresent()) {
            modelRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException(id);
        }
    }

    @Override
    public ModelDTO update(ModelDTO model) {

        if (!this.validationUtil.isValid(model)) {
            this.validationUtil
                    .violations(model)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllformedLocaleException("не подходит");
        }

        if (modelRepository.findById(model.getId()).isPresent()) {
            return modelMapper.map(modelRepository.save(modelMapper.map(model, Model.class)), ModelDTO.class);
        } else {
            throw new ModelNotFoundException(model.getId());
        }
    }

    @Override
    public List<ModelDTO> findAllByName(String name){
        return modelRepository.findAllByName(name).stream().map((m)->modelMapper.map(m,ModelDTO.class)).collect(Collectors.toList());
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Cacheable("models")
    public List<ShowModelInfoDto> allModels() {
        return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ShowModelInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShowModelInfoDto modelDetails(String modelName) {
        return modelMapper.map(modelRepository.findByName(modelName).orElse(null), ShowModelInfoDto.class);
    }
    public void removeModel(String modelName) {
        modelRepository.deleteByName(modelName);
    }
    @CacheEvict(cacheNames = "models", allEntries = true)
    public void addModel(AddModelDto modelModel) {
        modelModel.setCreated(LocalDateTime.now());
        modelModel.setModified(LocalDateTime.now());
        Model model = modelMapper.map(modelModel,Model.class);
        model.setBrand(brandRepository.findByName(modelModel.getBrandName()).orElse(null));
        model.setCategoryEnum(modelModel.getCategory());
        modelRepository.saveAndFlush(model);
    }

}
