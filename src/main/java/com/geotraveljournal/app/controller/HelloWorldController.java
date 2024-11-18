package com.geotraveljournal.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello Controller", description = "Контроллер для тестирования маршрутов")
public class HelloWorldController {

    @Operation(summary = "Сказать Hello", description = "Возвращает приветственное сообщение")
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}