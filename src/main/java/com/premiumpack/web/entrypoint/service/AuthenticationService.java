package com.premiumpack.web.entrypoint.service;

import com.premiumpack.web.domain.request.SignUpRq;
import com.premiumpack.web.domain.response.JwtRs;
import com.premiumpack.web.domain.response.SignUpRs;
import jakarta.servlet.http.HttpServletResponse;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthenticationService {

    JwtRs signIn(String requestAuthHeader, HttpServletResponse httpServletResponse) throws NoSuchAlgorithmException, InvalidKeySpecException;

    SignUpRs signUp(SignUpRq request);
}
