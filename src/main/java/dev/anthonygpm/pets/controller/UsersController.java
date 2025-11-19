package dev.anthonygpm.pets.controller;

import dev.anthonygpm.pets.model.Users;
import dev.anthonygpm.pets.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public List<Users> getUsers() {
        return usersService.listAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @GetMapping("/{email}")
    public Users getUserByEmail(@PathVariable String email) {
        return usersService.getUserByEmail(email);
    }

    @GetMapping("/{phone}")
    public Users getUserById(@PathVariable String phone) {
        return usersService.getUserByPhone(phone);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersService.saveUser(user);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return usersService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }
}
