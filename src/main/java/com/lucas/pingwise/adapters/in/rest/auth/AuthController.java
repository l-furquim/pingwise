package com.lucas.pingwise.adapters.in.rest.auth;

import com.lucas.pingwise.adapters.in.rest.auth.dto.AuthRequest;
import com.lucas.pingwise.adapters.in.rest.auth.dto.AuthResponse;
import com.lucas.pingwise.application.mappers.AuthApplicationMapper;
import com.lucas.pingwise.application.ports.in.auth.AuthUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/${api.version}/auth")
public class AuthController {

    private final AuthUserUseCase authUserUseCase;
    private final AuthApplicationMapper authApplicationMapper;

    @PostMapping
    public ResponseEntity<AuthResponse> auth (
            @RequestBody AuthRequest request
    ) {
       final var response = this.authUserUseCase.execute(
               this.authApplicationMapper.toAuthUserCommand(request)
       );

       return ResponseEntity.ok(response);
    }

}
