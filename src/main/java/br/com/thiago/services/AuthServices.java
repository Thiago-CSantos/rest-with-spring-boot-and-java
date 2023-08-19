package br.com.thiago.services;

import br.com.thiago.dto.security.AccountCredentialsVo;
import br.com.thiago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    public ResponseEntity signin(AccountCredentialsVo data) {

        try {
            var username = data.getUsername();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

//            var tokenResponse = new TokenVo();
//            if (user != null) {
//                tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());
//
//            } else {
//                throw new UsernameNotFoundException("Username " + username + " not found");
//            }
//            return ResponseEntity.ok(tokenResponse);

        } catch (Exception e) {
            throw new BadCredentialsException("username/password invalidos");
        }

        return ResponseEntity.ok("signin");
    }
}
