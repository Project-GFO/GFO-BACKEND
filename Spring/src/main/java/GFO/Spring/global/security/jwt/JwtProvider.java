package GFO.Spring.global.security.jwt;

import GFO.Spring.global.exception.exceptioncollection.TokenExpirationException;
import GFO.Spring.global.exception.exceptioncollection.TokenNotValidException;
import GFO.Spring.global.security.jwt.properties.JwtProperties;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Getter
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000*60*120;
    private final long REFRESH_TOKEN_EXPIRE_TIME = ACCESS_TOKEN_EXPIRE_TIME*12*7;

    @AllArgsConstructor
    private enum TokenType {
        ACCESS_TOKEN("accessToken"),
        REFRESH_TOKEN("refreshToken");
        String value;
    }

    @AllArgsConstructor
    private enum TokenClaimName {
        USER_EMAIL("userEmail"),
        TOKEN_TYPE("tokenType");
        String value;
    }

    private Key getSignInKey(String secretKey) {
        byte[] bytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

//    public TokenResponse createTokensByLogin(UserResponse userResponse) throws JsonProcessingException {
//        Subject atkSubject = Subject.accessToken(
//                userResponse.getEmail(),
//                userResponse.getName(),
//                userResponse.getDuty(),
//                userResponse.getClassNum());
//        String accessToken = createToken(atkSubject, atkLive);
//        return new TokenResponse(accessToken, null);
//    }
//    private String createToken(Subject subject, Long tokenLive) throws JsonProcessingException {
//        String subjectStr = objectMapper.writeValueAsString(subject);
//        Claims claims = Jwts.claims()
//                .setSubject(subjectStr);
//        Date date = new Date();
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(date)
//                .setExpiration(new Date(date.getTime()+tokenLive))
//                .signWith(SignatureAlgorithm.HS256, key)
//                .compact();
//    }

    private String generateToken(String userEmail, TokenType tokenType, String secret, long expireTime) {
        final Claims claims = Jwts.claims();
        claims.put(TokenClaimName.USER_EMAIL.value, userEmail);
        claims.put(TokenClaimName.TOKEN_TYPE.value, tokenType);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .signWith(getSignInKey(secret), SignatureAlgorithm.ES256)
                .compact();
    }

    public Claims extractAllClaims(String token, String secret) {
        token = token.replace("Bearer", "");
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey(secret))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenExpirationException("The token has expired");
        } catch (JwtException e) {
            throw new TokenNotValidException("토큰이 올바르지 않습니다.");
        }
    }

    public ZonedDateTime getExpiredAtToken(String token, String secret) {
        return ZonedDateTime.now().plusSeconds(ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String getUserEmail(String token, String secret) {
        return extractAllClaims(token, secret).get(TokenClaimName.USER_EMAIL.value, String.class);
    }

    public String getTokenType(String token, String secret) {
        return extractAllClaims(token, secret).get(TokenClaimName.TOKEN_TYPE.value, String.class);
    }

    public String generatedAccessToken(String email) {
        return generateToken(email, TokenType.ACCESS_TOKEN, jwtProperties.getAccessSecret(), ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String generatedRefreshToken(String email) {
        return generateToken(email, TokenType.REFRESH_TOKEN, jwtProperties.getRefreshSecret(), REFRESH_TOKEN_EXPIRE_TIME);
    }
}
