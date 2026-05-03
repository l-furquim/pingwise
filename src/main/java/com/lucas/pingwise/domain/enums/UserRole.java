package com.lucas.pingwise.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {

   ADMIN("admin"),
   MEMBER("member");

   private final String value;

   UserRole(String value) {
       this.value = value;
   }

}
