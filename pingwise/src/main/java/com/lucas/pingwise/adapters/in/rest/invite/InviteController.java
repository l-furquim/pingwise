package com.lucas.pingwise.adapters.in.rest.invite;

import com.lucas.pingwise.adapters.in.rest.invite.dto.InviteResponse;
import com.lucas.pingwise.application.ports.in.invite.AcceptInviteUseCase;
import com.lucas.pingwise.application.ports.in.invite.ListTenantInvitesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/${api.version}/invites")
public class InviteController {

   private final AcceptInviteUseCase acceptInviteUseCase;
   private final ListTenantInvitesUseCase listTenantInvitesUseCase;

   @PostMapping("/{token}/accept")
   public ResponseEntity<Void> accept(
           @PathVariable("token") String token
   ) {
       this.acceptInviteUseCase.execute(token);

       return ResponseEntity.ok().build();
   }

   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping
   public ResponseEntity<List<InviteResponse>> get() {
      final var response = listTenantInvitesUseCase.execute();
      return ResponseEntity.ok(response);
   }
}
