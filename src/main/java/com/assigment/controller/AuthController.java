package com.assigment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assigment.constant.AuthProvider;
import com.assigment.model.User;
import com.assigment.payload.ApiResponse;
import com.assigment.payload.AuthResponse;
import com.assigment.payload.LoginRequest;
import com.assigment.payload.SignUpRequest;
import com.assigment.repository.UserRepository;
import com.assigment.security.TokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TokenProvider tokenProvider;

	@PostMapping("/login")
	public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		User user = userRepository.findByEmailIgnoreCase(loginRequest.getEmail());
		Map<String, String> result = new HashMap<String, String>();
		if (user == null) {
			result.put("value", "email does not exist");
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			result.put("value",
					"wrong password. in case forget password goto forget password tab and update your password");
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = tokenProvider.createToken(authentication);
		return ResponseEntity.ok(new AuthResponse(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		Map<String, String> result = new HashMap<>();
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			result.put("value", "Email already exist");
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setName(signUpRequest.getName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(signUpRequest.getPassword());
		user.setProvider(AuthProvider.local);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			userRepository.save(user);
		} catch (Exception e) {
			result.put("value", "Internal Server Error");
			return new ResponseEntity<Object>(new ApiResponse(false, "Opps! Unable to register"),
					HttpStatus.BAD_REQUEST);
		}

		result.put("value", "Registration Successfull.");
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

}
