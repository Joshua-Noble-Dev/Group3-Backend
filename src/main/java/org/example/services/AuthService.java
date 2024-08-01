package org.example.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.daos.AuthDao;
import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.models.User;

import java.security.Key;
import java.util.Date;


import java.sql.SQLException;


public class AuthService {

    private final AuthDao authDao;
    private final Key key;


    public AuthService(final AuthDao authDao, final Key key) {
        this.authDao = authDao;
        this.key = key;
    }

    public String login(final LoginRequest loginRequest)
            throws SQLException, InvalidException {
        User user = authDao.getUser(loginRequest);

        if (user == null) {
            throw new InvalidException(Entity.USER, ": Invalid Credentials");
        }

        return generateJwtToken(user);
    }

    public String generateJwtToken(final User user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 28800000))
                .claim("Role", user.getUserRoleID())
                .subject(user.getUsername())
                .issuer("Agile and Fragile")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}
