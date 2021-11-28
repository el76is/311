package kata.academy.crud.service;

import kata.academy.crud.entities.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();

    Role getByName(String roleName);

    void create(Role role);
}

