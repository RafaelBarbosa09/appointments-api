package com.infnet.appointmentsapi.application.controllers;

import com.infnet.appointmentsapi.application.services.TokenService;
import com.infnet.appointmentsapi.domain.dto.AuthenticationDTO;
import com.infnet.appointmentsapi.domain.dto.LoginResponseDTO;
import com.infnet.appointmentsapi.domain.dto.RegisterDTO;
import com.infnet.appointmentsapi.domain.repositories.UserRepository;
import com.infnet.appointmentsapi.infrastructure.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO dto) {
        if (this.userRepository.findByLogin(dto.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto.login(), encryptedPassword, dto.role());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<UserDetails> getUserByLogin(@RequestParam String login) {
        UserDetails user = userRepository.findByLogin(login);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
}
