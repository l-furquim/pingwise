package com.lucas.pingwise.application.ports.out;

public interface TokenPort {

    String generate();
    String hash(String token);

}
