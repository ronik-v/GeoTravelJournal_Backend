package com.geotraveljournal.app.service.auth;

import com.geotraveljournal.app.dto.auth.UserDto;

public interface UserAuthService {
    UserDto add(String email);
    UserDto getByEmail(String email);
}
