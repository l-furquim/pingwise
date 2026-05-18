package com.lucas.pingwise.adapters.in.rest.user;

import com.lucas.pingwise.adapters.in.rest.user.dto.CreateUserRequest;
import com.lucas.pingwise.adapters.in.rest.user.dto.UserResponse;
import com.lucas.pingwise.application.mappers.UserApplicationMapper;
import com.lucas.pingwise.application.ports.in.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/${api.version}/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UserApplicationMapper userApplicationMapper;

   @PostMapping
   public ResponseEntity<UserResponse> create(
           @RequestBody CreateUserRequest request
   ) {
        final var response = this.createUserUseCase.execute(
            this.userApplicationMapper.toCreateUserCommand(request)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }

}
