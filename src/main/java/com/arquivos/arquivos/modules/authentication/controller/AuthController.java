package com.arquivos.arquivos.modules.authentication.controller;

import com.arquivos.arquivos.modules.authentication.dto.AuthenticationRequest;
import com.arquivos.arquivos.modules.authentication.dto.TokenRefreshRequest;
import com.arquivos.arquivos.modules.authentication.dto.ValidateResponse;
import com.arquivos.arquivos.modules.authentication.model.User;
import com.arquivos.arquivos.modules.authentication.service.AuthService;
import com.arquivos.arquivos.modules.authentication.service.RefreshTokenService;
import com.arquivos.arquivos.modules.authentication.service.UserService;
import com.arquivos.arquivos.modules.authentication.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          UserService userService,
                          RefreshTokenService refreshTokenService,
                          AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        return authService.getToken(authenticationRequest);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        return authService.refreshToken(request);
    }

    @PostMapping("/assign-role")
    public ResponseEntity<?> assignRoleToUser(@RequestParam String username, @RequestParam String roleName) {
        User user = userService.assignRoleToUser(username, roleName);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/validate")
    public ValidateResponse validateToken(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);

        if (username != null && jwtUtil.validateToken(jwt, username)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return new ValidateResponse(
                true,
                username,
                userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList())
            );
        }
        return new ValidateResponse(false, null, null);
    }

}
