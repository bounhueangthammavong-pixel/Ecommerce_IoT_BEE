package com.example.test.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.example.test.dto.request.IntrospectRequest;
import com.example.test.dto.request.LoginRequest;
import com.example.test.dto.request.LogoutRequest;
import com.example.test.dto.request.RefreshRequest;
import com.example.test.dto.response.AuthenticationResponse;
import com.example.test.dto.response.IntrospectResponse;
import com.example.test.model.User;

import java.text.ParseException;

public interface IAuthenticationService {

    public AuthenticationResponse authenticate(LoginRequest request);

    public String generateToken(User user);

    public SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException;

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;

    public void logout(LogoutRequest request) throws ParseException, JOSEException;

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    public AuthenticationResponse oauth2GoogleAuthenticate(String code);
}
