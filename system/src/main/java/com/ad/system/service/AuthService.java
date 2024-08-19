package com.ad.system.service;

import com.ad.system.entity.User;

public interface AuthService {
    User signInAndReturnJWT(User signInRequest);
}
