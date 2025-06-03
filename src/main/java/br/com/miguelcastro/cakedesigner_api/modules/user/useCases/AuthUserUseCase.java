package br.com.miguelcastro.cakedesigner_api.modules.user.useCases;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.miguelcastro.cakedesigner_api.modules.user.UserRepository;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.AuthUserRequestDTO;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.AuthUserResponseDTO;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.TokenDTO;
import br.com.miguelcastro.cakedesigner_api.modules.user.dtos.ViewUserResponseDTO;

@Service
public class AuthUserUseCase {

    @Value("${security.token.secret}")
    public String secretKey;

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    AuthUserUseCase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUserResponseDTO execute(AuthUserRequestDTO dto) {
        var user = this.userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User or password is invalid");
                });
        var password = this.passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!password) {
            throw new BadCredentialsException("User or password is invalid");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        var token = JWT.create()
                .withIssuer("cakedesigner")
                .withSubject(user.getId().toString())
                .withClaim("roles", Arrays.asList(user.getRole().name()))
                .withExpiresAt(expiresIn)
                .sign(algorithm);
        var formattedExpireDate = LocalDateTime.ofInstant(expiresIn, ZoneId.systemDefault());

        var userData = ViewUserResponseDTO
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .id(user.getId())
                .role(user.getRole().name())
                .build();

        var tokenData = TokenDTO.builder()
                .token(token)
                .expires_in(formattedExpireDate)
                .build();

        var authUserResponse = AuthUserResponseDTO.builder()
                .access_token(tokenData)
                .user(userData)
                .build();

        return authUserResponse;
    }

}
