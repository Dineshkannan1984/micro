package com.example.admin.Authenticate.controller;
import com.example.admin.Authenticate.model.JwtRequest;
import com.example.admin.Authenticate.model.JwtResponse;
import com.example.admin.Authenticate.service.AuthenticateService;
import com.example.admin.Authenticate.service.JwtUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/admin")
public class AuthenticateController {
    // private static Logger logger = LoggerFactory.getLogger(AuthenticateController.class);
    
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthenticateService authenticateService;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAccessToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
				
		return ResponseEntity.ok(authenticateService.generteTokens(userDetails));
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<?> createAccessTokenByRefreshToken(@RequestBody JwtResponse refreshRequest) throws Exception {
		return ResponseEntity.ok(authenticateService.generteTokensByRefreshToken(refreshRequest));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
