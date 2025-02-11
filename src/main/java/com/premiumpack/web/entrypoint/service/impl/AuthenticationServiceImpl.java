package com.premiumpack.web.entrypoint.service.impl;

import com.premiumpack.web.crosscutting.mapper.SignUpRsMapper;
import com.premiumpack.web.dataprovider.jpa.entity.RolEntity;
import com.premiumpack.web.dataprovider.jpa.entity.UserEntity;
import com.premiumpack.web.dataprovider.jpa.entity.UserRolEntity;
import com.premiumpack.web.dataprovider.jpa.entity.UserRolKey;
import com.premiumpack.web.dataprovider.jpa.repository.RolRepository;
import com.premiumpack.web.dataprovider.jpa.repository.UserRepository;
import com.premiumpack.web.dataprovider.jpa.repository.UserRolRepository;
import com.premiumpack.web.domain.request.JwtRq;
import com.premiumpack.web.domain.request.SignUpRq;
import com.premiumpack.web.domain.response.JwtRs;
import com.premiumpack.web.domain.response.SignUpRs;
import com.premiumpack.web.entrypoint.service.AuthenticationService;
import com.premiumpack.web.entrypoint.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final UserRolRepository userRolRepository;

    @Override
    public JwtRs signIn(String requestAuthHeader, HttpServletResponse httpServletResponse) throws NoSuchAlgorithmException, InvalidKeySpecException {
        JwtRq jwtRequest = parseBasicAuth(requestAuthHeader);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String newToken = jwtService.generateToken(userDetails);
        return JwtRs.builder()
                .expirationDate(jwtService.extractDate(newToken))
                .userName(jwtRequest.getUsername())
                .token(newToken)
                .build();
    }

    @Transactional
    @Override
    public SignUpRs signUp(SignUpRq request) {
        String passwordEncoded = passwordEncoder.encode(request.getPassword());
        Set<RolEntity> roles = request.getRoles().stream().map(rol -> rolRepository.findByRolName(rol.getRolName())).collect(Collectors.toSet());
        UserEntity user = UserEntity.builder()
                .password(passwordEncoded)
                .userName(request.getUsername())
                .email(request.getEmail())
                .status(Boolean.TRUE)
                .build();
        UserEntity savedUser = userRepository.save(user);
        List<UserRolEntity> userRoles = roles.stream().map(rol -> {
            UserRolKey userRolKey = UserRolKey.builder().idUser(savedUser.getId()).idRol(rol.getId()).build();
            return UserRolEntity.builder().id(userRolKey).user(savedUser).rol(rol).build();
        }).toList();
        List<UserRolEntity> savedUserRoles = userRolRepository.saveAll(userRoles);
        return SignUpRsMapper.INSTANCE.toSignUpRs(savedUser, savedUserRoles);
    }

    private JwtRq parseBasicAuth(String authHeader) {
        String basicAuth = authHeader.substring(6);
        byte[] decodeBasicAuth = Base64.getDecoder().decode(basicAuth);
        String[] splitBasicAuth = (new String(decodeBasicAuth)).split(":");
        return JwtRq.builder()
                .username(splitBasicAuth[0])
                .password(splitBasicAuth[1])
                .build();

    }
}
