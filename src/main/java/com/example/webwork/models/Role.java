package com.example.webwork.models;

import com.example.webwork.enums.RoleEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "created", column = @Column(insertable = false, updatable = false)),
        @AttributeOverride(name = "modified", column = @Column(insertable = false, updatable = false))
})

public class Role extends Base {

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Users> users;
    @Column(name="name", length = 11, nullable = false)
    private RoleEnum roleEnum;

    protected Role() {};

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
