package com.example.familytree.Data.ReaderAPIs.auth;

public class AuthToken {

    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "{" +
                "jwt='" + jwt + '\'' +
                '}';
    }
}
