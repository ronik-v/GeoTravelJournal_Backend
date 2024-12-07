package com.geotraveljournal.app.controller.auth;

import com.geotraveljournal.app.dto.auth.UserDto;
import com.geotraveljournal.app.dto.auth.UserLoginRequest;
import com.geotraveljournal.app.dto.auth.UserRegisterRequest;
import com.geotraveljournal.app.responses.core.GoodResponse;
import com.geotraveljournal.app.service.auth.UserAuthServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "User Auth Controller", description = "Контроллер для авторизации")
public class UserAuthController {
    private final UserAuthServiceImpl userAuthServiceImpl;

    public UserAuthController(UserAuthServiceImpl userAuthServiceImpl) {
        this.userAuthServiceImpl = userAuthServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<GoodResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        UserDto userDto = userAuthServiceImpl.add(request.getEmail(), request.getPassword());

        GoodResponse response = new GoodResponse(true, userDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<GoodResponse> login(@Valid @RequestBody UserLoginRequest request) {
        UserDto userDto = userAuthServiceImpl.getByEmail(request.getEmail(), request.getPassword());

        GoodResponse response = new GoodResponse(true, userDto);
        return ResponseEntity.ok(response);
    }
}
