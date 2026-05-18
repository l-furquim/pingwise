package com.lucas.pingwise.adapters.in.rest.tenant;

import com.lucas.pingwise.adapters.in.rest.plan.dto.CreateTenantRequest;
import com.lucas.pingwise.adapters.in.rest.tenant.dto.InviteTenantMemberRequest;
import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantMemberResponse;
import com.lucas.pingwise.adapters.in.rest.tenant.dto.TenantResponse;
import com.lucas.pingwise.application.mappers.TenantApplicationMapper;
import com.lucas.pingwise.application.ports.in.tenant.*;
import com.lucas.pingwise.infrastructure.security.TenantMemberOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/${api.version}/tenants")
@RestController
public class TenantController {

   private final CreateTenantUseCase createTenantUseCase;
   private final GetTenantMembersUseCase getTenantMembersUseCase;
   private final GetCurrentTenantUseCase getCurrentTenantUseCase;
   private final InviteTenantMemberUseCase inviteTenantMemberUseCase;
   private final LeaveTenantUseCase leaveTenantUseCase;
   private final RemoveMemberUseCase removeMemberUseCase;

   private final TenantApplicationMapper tenantApplicationMapper;

   @PostMapping
   public ResponseEntity<TenantResponse> create(
           @RequestBody CreateTenantRequest request
   ) {
        final var response = this.createTenantUseCase.execute(
                this.tenantApplicationMapper.toCommand(request)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }

   @TenantMemberOnly
   @GetMapping
   public ResponseEntity<TenantResponse> get() {
       final var response = this.getCurrentTenantUseCase.execute();

       return ResponseEntity.ok(response);
   }


   @TenantMemberOnly
   @GetMapping("/members")
    public ResponseEntity<List<TenantMemberResponse>> getMembers() {
       final var response = this.getTenantMembersUseCase.execute();

       return ResponseEntity.ok(response);
   }

   @PreAuthorize("hasRole('ADMIN')")
   @PostMapping("/members/invite")
   public ResponseEntity<Void> inviteMember(
           @RequestBody InviteTenantMemberRequest request
   ) {
      inviteTenantMemberUseCase.execute(this.tenantApplicationMapper.toCommand(request));

      return ResponseEntity.ok().build();
   }

   @TenantMemberOnly
   @DeleteMapping("/members/me")
   public ResponseEntity<Void> leaveTenant() {
        this.leaveTenantUseCase.execute();

        return ResponseEntity.ok().build();
   }

   @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/members/{id}")
   public ResponseEntity<Void> removeMember(@PathVariable("id") UUID memberId) {
        this.removeMemberUseCase.execute(memberId);
        return ResponseEntity.ok().build();
   }

}
