package com.example.gymfitnew.securityConfig;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			System.out.println("in filter");
			String jwtToken = extractJwtFromRequest(request);
			System.out.println(jwtToken);
			System.out.println(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken));
			if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
				System.out.println("in filter-1");
				String username = jwtTokenProvider.getUserName(jwtToken);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				System.out.println("in filter-2");
			} else {
				System.out.println("Can-not set the security context");
			}
		} catch (ExpiredJwtException e) {
			System.out.println("Jwt Toke expired");
		} catch (Exception e) {
			System.out.println("bad-->"+e.toString());
			request.setAttribute("exception", e);
		}
		filterChain.doFilter(request, response);
	}
	
	
	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		System.out.println(bearerToken);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			System.out.println("in if");
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
