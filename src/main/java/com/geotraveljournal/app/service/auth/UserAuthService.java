package com.geotraveljournal.app.service.auth;

import com.geotraveljournal.app.dto.auth.UserDto;

public interface UserAuthService {
    UserDto add(String email, String password);
    UserDto getByEmail(String email, String password);
}
