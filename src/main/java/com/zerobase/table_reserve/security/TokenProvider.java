package com.zerobase.table_reserve.security;

import com.zerobase.table_reserve.member.domain.common.UserType;
import com.zerobase.table_reserve.member.domain.common.UserVo;
import com.zerobase.table_reserve.member.service.MemberService;
import com.zerobase.table_reserve.security.util.Aes256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final String KEY_ROLES = "roles";


    private final MemberService memberService;

    private final Key secretKey = Keys.hmacShaKeyFor("your-very-secure-and-long-secret-key!".getBytes(StandardCharsets.UTF_8));

    private final long tokenValidTime = 1000L * 60 * 60 * 24;

    public String createToken(String userPk, UserType userType) {
        Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPk));
        claims.put(KEY_ROLES, userType);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String jwt) {
        UserDetails userDetails = memberService.loadUserByUsername(getUserVo(jwt).getUserId());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public UserVo getUserVo(String jwtToken) {
        Claims c = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
        return new UserVo(Aes256Util.decrypt(c.getSubject()));
    }
}

