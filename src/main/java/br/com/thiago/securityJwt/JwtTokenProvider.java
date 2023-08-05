package br.com.thiago.securityJwt;

import br.com.thiago.dto.security.TokenVo;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("security.jwt.token.secret-key:secret")
    private String secretKey = "secret";
    @Value("security.jwt.token.expire-length:3600000")
    private final long validityInMilliseconds = 3600000; // 1h

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes()); // Cryptographic a chave
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVo createAccessToken(String username, List<String> roles) {
        Date agora = new Date();
        Date validity = new Date(agora.getTime() + validityInMilliseconds); // hora de agora + 1h

        var accessToken = getAccessToken(username, roles, agora, validity);
        var refreshToken = getAccessToken(username, roles, agora);

        return new TokenVo(username,true, agora, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date agora) {
        return null;
    }

    private String getAccessToken(String username, List<String> roles, Date agora, Date validity) {
        return null;
    }

}
