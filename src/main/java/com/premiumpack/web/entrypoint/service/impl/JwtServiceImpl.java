package com.premiumpack.web.entrypoint.service.impl;

import com.premiumpack.web.domain.RolDto;
import com.premiumpack.web.entrypoint.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.premiumpack.web.crosscutting.constants.UtilConstants.SCOPES;


@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${token-private-key}")
    private String  tokenPrivateKey;

    @Value("${token-public-key}")
    private String  tokenPublicKey;

    @Override
    public String extractUserName(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    @Override
    public String generateToken(UserDetails userDetails) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(SCOPES, userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return generateToken(extraClaims, userDetails);
    }

    @Override
    public boolean isTokenValid(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningPublicKey()).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RolDto> extractScopes(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = extractAllClaims(token);
        List<String> scopes = (List<String>) claims.get(SCOPES);
        return scopes.stream().map(s -> RolDto.builder().rolName(s).build()).collect(Collectors.toList());
    }

    @Override
    public LocalDateTime extractDate(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = extractAllClaims(token);
        long epoch = (int) claims.get("exp");
        return Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private Claims extractAllClaims(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parserBuilder().setSigningKey(getSigningPublicKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private PrivateKey getSigningKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(tokenPrivateKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(keySpec);
        return privateKey;
    }

    private PublicKey getSigningPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicBytes = Base64.getDecoder().decode(tokenPublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.RS256).compact();
    }

}
