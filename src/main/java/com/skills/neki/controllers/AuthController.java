package com.skills.neki.controllers;

import com.skills.neki.model.User;
import com.skills.neki.dto.LoginDTO;
import com.skills.neki.dto.RegisterDTO;
import com.skills.neki.dto.ResponseDTO;
import com.skills.neki.security.TokenService;
import com.skills.neki.repositores.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO body) {
        User user = this.repository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User não encontrado"));
        if (passwordEncoder.matches(body.senha(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getEmail(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO body) {
        Optional<User> user = this.repository.findByEmail(body.email());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setNome(body.nome());
            newUser.setEmail(body.email());
            newUser.setSenha(passwordEncoder.encode(body.senha()));
            newUser.setCargo(body.cargo());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    public AuthController(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        super();
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }
}
