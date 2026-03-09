package com.example.test.api;


import com.nimbusds.jose.JOSEException;
import com.example.test.dto.request.IntrospectRequest;
import com.example.test.dto.request.LoginRequest;
import com.example.test.dto.request.LogoutRequest;
import com.example.test.dto.request.RefreshRequest;
import com.example.test.dto.response.ApiResponse;
import com.example.test.dto.response.AuthenticationResponse;
import com.example.test.dto.response.IntrospectResponse;
import com.example.test.service.impl.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody @Valid LoginRequest request){

        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .success(true)
                .content(authenticationResponse)
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/oauth/authorization/google")
    public ResponseEntity<ApiResponse<?>> oauth2Google(
            @RequestParam(name = "code") String code
    ){
        AuthenticationResponse authenticationResponse = authenticationService.oauth2GoogleAuthenticate(code);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .success(true)
                .content(authenticationResponse)
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(@RequestBody @Valid LogoutRequest request, @AuthenticationPrincipal Jwt jwt)
            throws ParseException, JOSEException {

        authenticationService.logout(request);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .success(true)
                .content("LOGOUT COMPLETED")
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody @Valid RefreshRequest request)
            throws ParseException, JOSEException {

        AuthenticationResponse authenticationResponse = authenticationService.refreshToken(request);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .success(true)
                .content(authenticationResponse)
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/introspect")
    public ResponseEntity<?> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {

        IntrospectResponse introspectResponse = authenticationService.introspect(request);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .success(true)
                .content(introspectResponse)
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }
}
