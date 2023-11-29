package com.example.webwork.controllers;

import com.example.webwork.dto.RoleDTO;
import com.example.webwork.except.RoleNotFoundException;
import com.example.webwork.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) {this.roleService = roleService;}

    @GetMapping()
    Iterable<RoleDTO> getAllRoles() {
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    RoleDTO get(@PathVariable String id) {return roleService.findById(id).orElseThrow(() -> new RoleNotFoundException(id));}

    @PostMapping
    RoleDTO createRole(@RequestBody RoleDTO role) {
        return roleService.registerRole(role);
    }

    @DeleteMapping("/{id}")
    void deleteRole(@PathVariable String id) {
        roleService.expel(id);
    }

    @PutMapping()
    RoleDTO updateRole(@RequestBody RoleDTO role) {
        return roleService.update(role);
    }
}
