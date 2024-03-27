package com.example.gymfitnew.securityConfig;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gymfitnew.dto.GymUserDto;
import com.example.gymfitnew.model.GymUser;
import com.example.gymfitnew.repository.GymUserRepository;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private GymUserRepository gymUserRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		System.out.println("in username");
		GymUser gymUser = gymUserRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or email"));
		System.out.println("in - 1");
		Set<GrantedAuthority> authorities = gymUser.getGymRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
		System.out.println("in - 2");
		return new User(
                usernameOrEmail,
                gymUser.getPassword(),
                authorities
        );
	}
	
	public GymUser save(GymUserDto user) {
		GymUser newUser = new GymUser();
		newUser.setUsername(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setMobileNumber(user.getMobileNumber());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setConfirmPassword(user.getConfirmPassword());
		newUser.setGymRoles(user.getGymRoles());
		return gymUserRepository.save(newUser);
	}
}
