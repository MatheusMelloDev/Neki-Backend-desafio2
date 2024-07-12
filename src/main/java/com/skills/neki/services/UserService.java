package com.skills.neki.services;

import com.skills.neki.dto.UserDTO;
import com.skills.neki.model.User;
import com.skills.neki.repositores.UserRepository;
import com.skills.neki.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UserDTO registerUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getLogin());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        User user = new User();
        user.setNome(userDTO.getNome());
        user.setEmail(userDTO.getLogin());
        user.setSenha(passwordEncoder.encode(userDTO.getSenha()));
        user.setCargo(userDTO.getCargo());
        userRepository.save(user);

        String token = tokenService.generateToken(user);
        return new UserDTO(user.getId(), user.getNome(), user.getEmail(), null, user.getCargo());
    }

    public String loginUser(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getLogin())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(userDTO.getSenha(), user.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        return tokenService.generateToken(user);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UserDTO(user.getId(), user.getNome(), user.getEmail(), null, user.getCargo());
    }
}
