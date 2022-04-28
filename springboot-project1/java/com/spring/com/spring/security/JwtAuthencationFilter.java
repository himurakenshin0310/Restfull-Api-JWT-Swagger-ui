package com.spring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthencationFilter extends OncePerRequestFilter {

	@Autowired
	JsonTokenProvider jwtProvider;

	@Autowired
	UserDetailService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// lay ra jwt trong header
		String jwt = getJwtFromRequest(request);
		log.info("get jwt in header {}", jwt);
		if (StringUtils.hasText(jwt) && jwtProvider.verifyToken(jwt)) {
			String userName = jwtProvider.getUserNameFromToken(jwt);
			UserDetails userDetail = userDetailService.loadUserByUsername(userName);
			if (userDetail != null) {
				// set thong tin user vao security context.
				log.info("get username in userdetail {}", userDetail.getUsername());
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,
						null, userDetail.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest rq) {
		String bearerToken = rq.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
			return bearerToken.substring(7);
		else
			return null;
	}
}
