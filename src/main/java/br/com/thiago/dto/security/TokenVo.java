package br.com.thiago.dto.security;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private Boolean authenticated;
    private Date create;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public TokenVo(){
    }

    public TokenVo(String username, Boolean authenticated, Date create, Date expiration, String accessToken, String refreshToken) {
        this.username = username;
        this.authenticated = authenticated;
        this.create = create;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenVo tokenVo)) return false;
        return Objects.equals(getUsername(), tokenVo.getUsername()) && Objects.equals(getAuthenticated(), tokenVo.getAuthenticated()) && Objects.equals(getCreate(), tokenVo.getCreate()) && Objects.equals(getExpiration(), tokenVo.getExpiration()) && Objects.equals(getAccessToken(), tokenVo.getAccessToken()) && Objects.equals(getRefreshToken(), tokenVo.getRefreshToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getAuthenticated(), getCreate(), getExpiration(), getAccessToken(), getRefreshToken());
    }
}
