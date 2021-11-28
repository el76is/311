package kata.academy.crud.config;

import kata.academy.crud.entities.Role;
import kata.academy.crud.entities.User;
import kata.academy.crud.service.RoleService;
import kata.academy.crud.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class LoaderConfiguration {

    private final UserService userService;

    private final RoleService roleService;

    public LoaderConfiguration(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void fillDb() {
        Role roleAdm = new Role();
        roleAdm.setRole("ROLE_ADMIN");
        roleService.create(roleAdm);
        Role roleUsr = new Role();
        roleUsr.setRole("ROLE_USER");
        roleService.create(roleUsr);

        User user1 = new User();
        user1.setName("user1");
        user1.setPassword("user1");
        user1.setRoles(Set.of(roleAdm));
        user1.setFirstName("FirstName1");
        user1.setLastName("LastName1");
        user1.setEmail("user1@somewhere.net");
        userService.create(user1);

        User user2 = new User();
        user2.setName("user2");
        user2.setPassword("user2");
        user2.setRoles(Set.of(roleUsr));
        user2.setFirstName("FirstName2");
        user2.setLastName("LastName2");
        user2.setEmail("user2@somewhere.net");
        userService.create(user2);

        User user3 = new User();
        user3.setName("user3");
        user3.setPassword("user3");
        user3.setRoles(Set.of(roleAdm, roleUsr));
        user3.setFirstName("FirstName3");
        user3.setLastName("LastName3");
        user3.setEmail("user3@somewhere.net");
        userService.create(user3);
    }
}
