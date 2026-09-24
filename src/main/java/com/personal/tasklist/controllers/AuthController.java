package com.personal.tasklist.controllers;

import com.personal.tasklist.config.security.TokenJson;
import com.personal.tasklist.config.security.TokenService;
import com.personal.tasklist.dto.auth.LoginDTO;
import com.personal.tasklist.entitites.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity doLogin(@RequestBody LoginDTO loginDTO) {

       var token = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
       var auth = authenticationManager.authenticate(token);

        User user = (User) auth.getPrincipal();

        var tokenJWT = tokenService.generateToken(user.getEmail());
        return ResponseEntity.ok().body(new TokenJson(tokenJWT));
    }

}
