package com.lucas.pingwise.adapters.in.rest.auth;

import com.lucas.pingwise.adapters.in.rest.auth.dto.AuthRequest;
import com.lucas.pingwise.adapters.in.rest.auth.dto.AuthResponse;
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



   @PostMapping
   public ResponseEntity<AuthResponse> auth (
           @RequestBody AuthRequest request
   ) {

    }

}
