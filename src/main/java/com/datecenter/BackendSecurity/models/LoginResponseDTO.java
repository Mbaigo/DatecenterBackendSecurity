package com.datecenter.BackendSecurity.models;

public class LoginResponseDTO {
    private UserApplication user;
    private String jwt;

    public UserApplication getUser() {
        return this.user;
    }

    public void setUser(UserApplication user) {
        this.user = user;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public LoginResponseDTO(UserApplication user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public LoginResponseDTO() {
        super();
    }
}
