package dev.anthonygpm.pets.service;

import dev.anthonygpm.pets.model.Users;
import dev.anthonygpm.pets.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public List<Users> listAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public Users getUserByPhone(String phone) {
        return usersRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found with phone: " + phone));
    }

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public Users updateUser(Long id, Users updatedUser) {
        Users existingUser = getUserById(id);

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());

        return usersRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
