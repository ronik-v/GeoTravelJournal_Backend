package com.geotraveljournal.app.controller.journal;

import com.geotraveljournal.app.dao.auth.UserDao;
import com.geotraveljournal.app.dto.journal.JournalInDto;
import com.geotraveljournal.app.dto.journal.JournalOutDto;
import com.geotraveljournal.app.dto.journal.JournalOutPreviewDto;
import com.geotraveljournal.app.model.auth.User;
import com.geotraveljournal.app.responses.core.GoodResponse;
import com.geotraveljournal.app.service.journal.JournalServiceImpl;
import com.geotraveljournal.app.utils.ApiAuth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
@Tag(name = "Journal Controller", description = "Контроллер для истории журнала поездок")
public class JournalController {
    private final JournalServiceImpl journalService;
    private final UserDao userDao;

    public JournalController(JournalServiceImpl journalService, UserDao userDao) {
        this.journalService = journalService;
        this.userDao = userDao;
    }

    @PostMapping
    @Operation(
            summary = "Добавить новый журнал",
            description = "Добавляет новый журнал для авторизованного пользователя.",
            parameters = @Parameter(name = "Authorization", description = "Токен авторизации", in = ParameterIn.HEADER, required = true)
    )
    public ResponseEntity<GoodResponse> addJournal(
            @RequestBody JournalInDto journalData,
            HttpServletRequest request
    ) {
        User currentUser = ApiAuth.getCurrentUser(request, userDao);
        journalData.setUserId(currentUser.getId());
        JournalOutDto createdJournal = journalService.add(journalData);

        return ResponseEntity.ok(new GoodResponse(true, createdJournal));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновить журнал",
            parameters = @Parameter(name = "Authorization", description = "Токен авторизации", in = ParameterIn.HEADER, required = true)
    )
    public ResponseEntity<GoodResponse> updateJournal(
            @PathVariable Long id,
            @RequestBody JournalInDto journalData,
            HttpServletRequest request
    ) {
        User currentUser = ApiAuth.getCurrentUser(request, userDao);
        journalData.setUserId(currentUser.getId());
        JournalOutDto updatedJournal = journalService.update(id, journalData);

        return ResponseEntity.ok(new GoodResponse(true, updatedJournal));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить детали журнала",
            parameters = @Parameter(name = "Authorization", description = "Токен авторизации", in = ParameterIn.HEADER, required = true)
    )
    public ResponseEntity<GoodResponse> getJournalDetail(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        ApiAuth.getCurrentUser(request, userDao);
        JournalOutDto journal = journalService.getDetail(id);
        return ResponseEntity.ok(new GoodResponse(true, journal));
    }

    @GetMapping("/history")
    @Operation(
            summary = "Получить историю пользователя",
            parameters = @Parameter(name = "Authorization", description = "Токен авторизации", in = ParameterIn.HEADER, required = true)
    )
    public ResponseEntity<GoodResponse> getUserHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        User currentUser = ApiAuth.getCurrentUser(request, userDao);
        Pageable pageable = PageRequest.of(page, size);
        List<JournalOutPreviewDto> history = journalService.getUserHistory(currentUser.getId(), pageable);

        return ResponseEntity.ok(new GoodResponse(true, history));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить журнал",
            parameters = @Parameter(name = "Authorization", description = "Токен авторизации", in = ParameterIn.HEADER, required = true)
    )
    public ResponseEntity<GoodResponse> deleteJournal(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        User currentUser = ApiAuth.getCurrentUser(request, userDao);
        journalService.deleteById(id, currentUser.getId());
        return ResponseEntity.ok(new GoodResponse(true, "Журнал успешно удалён."));
    }

    @DeleteMapping("/history")
    @Operation(
            summary = "Очистить историю",
            parameters = @Parameter(name = "Authorization", description = "Токен авторизации", in = ParameterIn.HEADER, required = true)
    )
    public ResponseEntity<GoodResponse> clearHistory(HttpServletRequest request) {
        User currentUser = ApiAuth.getCurrentUser(request, userDao);
        journalService.clearHistory(currentUser.getId());
        return ResponseEntity.ok(new GoodResponse(true, "История успешно очищена."));
    }
}
