package br.com.thiago.securityJwt;

import br.com.thiago.dto.security.TokenVo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        var refreshToken = getRefreshToken(username, roles, agora);

        return new TokenVo(username, true, agora, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date agora, Date validity) {
        String emitirUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString(); // pega URL do servidor

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(agora)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(emitirUrl)
                .sign(algorithm);

    }

    private String getRefreshToken(String username, List<String> roles, Date agora) {

        Date validityRefreshToken = new Date(agora.getTime() + (validityInMilliseconds * 3));

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(agora)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .sign(algorithm);
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodedToken(token);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //decodificando o token
    private DecodedJWT decodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());

        JWTVerifier verifier = JWT.require(alg).build(); // vereficando o algoritmo

        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    public String resolveToken(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return "";
    }

    public boolean validationToken(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        try{
            if(decodedJWT.getExpiresAt().before(new Date())){
                return false;
            }
            return true;
        }
        catch(Exception e){
            throw new RuntimeException();
        }
    }

}
