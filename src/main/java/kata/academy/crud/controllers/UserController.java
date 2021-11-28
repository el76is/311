package kata.academy.crud.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kata.academy.crud.entities.Role;
import kata.academy.crud.entities.User;
import kata.academy.crud.service.RoleService;
import kata.academy.crud.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("user")
    public String user(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("admin/read")
    public String read(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "read";
    }

    @GetMapping("admin/create")
    public String newUser(@ModelAttribute("user") User user) {
        return "create";
    }

    @PostMapping("admin/create")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam("roleName") String[] role) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        if (userService.getUserByUsername(user.getUsername()) != null) {
            return "create";
        }
        Set<Role> rs = new HashSet<>();
        for (String roles : role) {
            rs.add(roleService.getByName(roles));
        }
        user.setRoles(rs);
        userService.create(user);
        return "redirect:/admin/read";
    }

    @GetMapping("admin/{id}/update")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PutMapping("admin/{id}/update")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam("roleName") String[] role) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        Set<Role> rs = new HashSet<>();
        for (String roles : role) {
            rs.add(roleService.getByName(roles));
        }
        user.setRoles(rs);
        userService.update(user);
        return "redirect:/admin/read";
    }

    @DeleteMapping( "/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/read";
    }
}
