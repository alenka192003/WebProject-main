package com.example.webwork.services.Impl;

import com.example.webwork.except.RoleConflictException;
import com.example.webwork.except.RoleNotFoundException;
import com.example.webwork.dto.RoleDTO;
import com.example.webwork.models.Role;
import com.example.webwork.repo.OfferRepository;
import com.example.webwork.repo.RoleRepository;
import com.example.webwork.services.RoleService;
import com.example.webwork.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final ModelMapper modelMapper;
    private RoleRepository roleRepository;
    private final ValidationUtil validationUtil;
    @Autowired
    public RoleServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public RoleDTO registerRole(RoleDTO role) {

        if(!this.validationUtil.isValid(role)) {
            this.validationUtil
                    .violations(role)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("не подходит");
        }

        Role r = modelMapper.map(role, Role.class);
        String roleId = r.getId();
        if (roleId == null || roleRepository.findById(roleId).isEmpty()) {
            return modelMapper.map(roleRepository.save(r), RoleDTO.class);
        } else {
            throw new RoleConflictException("уже существует с таким id");
        }
    }

    @Override
    public List<RoleDTO> getAll() {
        return roleRepository.findAll().stream().map((s) -> modelMapper.map(s, RoleDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDTO> findById(String id) {
        return Optional.ofNullable(modelMapper.map(roleRepository.findById(id), RoleDTO.class));
    }

    @Override
    public void expel(String id) {
        if (roleRepository.findById(id).isPresent()) {
            roleRepository.deleteById(id);
        } else {
            throw new RoleNotFoundException(id);
        }
    }

    @Override
    public RoleDTO update(RoleDTO role) {

        if(!this.validationUtil.isValid(role)) {
            this.validationUtil
                    .violations(role)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("не подходит");
        }

        if (roleRepository.findById(role.getId()).isPresent()) {
            return modelMapper.map(roleRepository.save(modelMapper.map(role, Role.class)), RoleDTO.class);
        } else {
            throw new RoleNotFoundException(role.getId());
        }
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
