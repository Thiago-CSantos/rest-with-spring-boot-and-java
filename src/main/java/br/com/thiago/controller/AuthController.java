package br.com.thiago.controller;

import br.com.thiago.dto.security.AccountCredentialsVo;
import br.com.thiago.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsVo data) {
        if (data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null || data.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cliente invalido bad request");
        }

        var token = authServices.signin(data);
        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cliente invalido bad request");
        }
        return token;
    }
}
