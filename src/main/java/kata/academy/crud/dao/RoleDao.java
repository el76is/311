package kata.academy.crud.dao;

import kata.academy.crud.entities.Role;

import java.util.Set;

public interface RoleDao {

    Set<Role> getAllRoles();

    Role getByName(String roleName);

    void create(Role role);
}

