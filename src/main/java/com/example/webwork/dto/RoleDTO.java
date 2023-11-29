package com.example.webwork.dto;

import com.example.webwork.enums.RoleEnum;


public class RoleDTO {
    private String id;
    private RoleEnum roleEnum;

    public RoleDTO() {};

    public RoleDTO(String id, RoleEnum roleEnum) {
        this.id = id;
        this.roleEnum = roleEnum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", roleEnum=" + roleEnum +
                '}';
    }
}
