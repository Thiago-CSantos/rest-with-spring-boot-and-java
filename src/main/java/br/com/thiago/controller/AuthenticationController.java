package br.com.thiago.controller;

import br.com.thiago.dto.LoginResponseDTO;
import br.com.thiago.dto.security.AccountCredentialsVo;
import br.com.thiago.model.User;
import br.com.thiago.securityJwt.TokenService;
import br.com.thiago.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    AuthServices authServices;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsVo data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.genereteToekn((User)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

//        var token = authServices.signin(data);
//        if (token == null) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cliente invalido bad request");
//        }
//        return token;
    }
}
