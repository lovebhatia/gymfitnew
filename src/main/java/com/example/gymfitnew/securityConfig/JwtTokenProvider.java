package com.example.gymfitnew.securityConfig;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.gymfitnew.entity.GymUser;
import com.example.gymfitnew.repository.GymRefreshTokenRepository;
import com.example.gymfitnew.repository.GymUserRepository;


@Component
public class JwtTokenProvider {
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.expiration}")
	private long jwtExpirationDate;
	
	@Value("${jwt.refreshExpiration}")
	private long jwtRefreshTokenExpirationDate;
	
	@Autowired
	GymRefreshTokenRepository gymRefreshTokenRepository;
	
	@Autowired
	GymUserRepository gymUserRepository;
	
	public String generateToken(UserDetails userDetails) {
		System.out.println("in generate token");
		Map<String, Object> claims = new HashMap<String, Object>();
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		if(roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			claims.put("isAdmin", true);
		}
		if(roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			claims.put("isUser", true);
		}
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	public String generateRefreshToken(UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<String, Object>();
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		if(roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			claims.put("isAdmin", true);
		}
		if(roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			claims.put("isUser", true);
		}
		GymUser gymUser = gymUserRepository.findByUsernameOrEmail(userDetails.getUsername(),
				userDetails.getUsername()).get();
		int res = gymRefreshTokenRepository.deleteByGymUser(gymUser);
		System.out.println(res);
		return doGenerateRefreshToken(claims, userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		
		Date issuedAt = new Date(System.currentTimeMillis());
		Date expirationAt = new Date(System.currentTimeMillis() + jwtExpirationDate);
		return Jwts.builder()
				.claims(claims)
				.subject(subject)
				.issuedAt(issuedAt)
				.expiration(expirationAt)
				.signWith(key())
				.compact();
	}
	private String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
		Date issuedAt = new Date(System.currentTimeMillis());
		Date refreshExpirationAt = new Date(System.currentTimeMillis() + jwtRefreshTokenExpirationDate);
		return Jwts.builder()
				.claims(claims)
				.subject(subject)
				.issuedAt(issuedAt)
				.expiration(refreshExpirationAt)
				.signWith(key())
				.compact();	
	}
	private Key key() {
		byte[] keyBytes = new byte[32]; // For example, 256-bit key
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "HmacSHA256");
		return keySpec;//Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	public String getUserName(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey) key())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public Boolean validateToken(String token) {
		System.out.println("in validate");
		try {
		Jwts.parser()
			.verifyWith((SecretKey) key())
			.build()
			.parse(token);
		}catch (Exception e) {
			System.out.println(e);
		}
		return true;	
	}
	public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload();
		System.out.println(claims);

		List<SimpleGrantedAuthority> roles = null;

		Boolean isAdmin = claims.get("isAdmin", Boolean.class);
		Boolean isUser = claims.get("isUser", Boolean.class);

		if (isAdmin != null && isAdmin) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		if (isUser != null && isUser) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return roles;
	}
	
	private void deletePreviousRefreshTokens(Long userId) {
	   gymRefreshTokenRepository.deleteById(userId);
	}
	
}
