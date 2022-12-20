package GFO.Spring.global.security.jwt;

import GFO.Spring.domain.user.presentation.dto.response.UserResponse;
import GFO.Spring.global.security.jwt.dto.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final ObjectMapper objectMapper;
    @Value("${spring.jwt.authkey")
    private String authKey;
    
    @Value("${spring.jwt.key}")
    private String key;

    @Value("${spring.jwt.live.atk}")
    private Long atkLive;

    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public TokenResponse createTokensByLogin(UserResponse userResponse) throws JsonProcessingException {
        Subject atkSubject = Subject.accessToken(
                userResponse.getEmail(),
                userResponse.getName(),
                userResponse.getDuty(),
                userResponse.getClassNum());
        String atk = createToken(atkSubject, atkLive);
        return new TokenResponse(atk, null);
    }

    private String createToken(Subject subject, Long tokenLive) throws JsonProcessingException {
        String subjectStr = objectMapper.writeValueAsString(subject);
        Claims claims = Jwts.claims()
                .setSubject(subjectStr);
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+tokenLive))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }





}
