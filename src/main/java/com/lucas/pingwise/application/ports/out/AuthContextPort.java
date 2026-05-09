package com.lucas.pingwise.application.ports.out;

import com.lucas.pingwise.domain.model.User;

public interface AuthContextPort {

    String getUserId();
    User getUser();

}
