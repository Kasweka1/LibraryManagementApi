package com.example.LibraryManagmentApi.security.restapi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.example.LibraryManagmentApi.security.model.UserPrinciple;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import com.example.LibraryManagmentApi.security.model.User;
import com.example.LibraryManagmentApi.security.repository.UserRepository;
import com.example.LibraryManagmentApi.security.restapi.jwtconfig.JwtService;
import com.example.LibraryManagmentApi.security.restapi.token.Token;
import com.example.LibraryManagmentApi.security.restapi.token.TokenRepository;
import com.example.LibraryManagmentApi.security.restapi.token.TokenType;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()));

    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();

    UserPrinciple userPrinciple = new UserPrinciple(user);

    var jwtToken = jwtService.generateToken(userPrinciple);
    var refreshToken = jwtService.generateRefreshToken(userPrinciple);

    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);

    return AuthenticationResponse.builder()
        .token(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
          .orElseThrow();
      // Convert User to UserPrinciple
      UserPrinciple userPrinciple = new UserPrinciple(user);
      if (jwtService.isTokenValid(refreshToken, userPrinciple)) {
        var accessToken = jwtService.generateToken(userPrinciple);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
            .token(accessToken)
            .refreshToken(refreshToken)
            .build();
        // new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }

}
