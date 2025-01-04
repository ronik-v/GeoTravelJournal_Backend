package com.geotraveljournal.app.service.journal;

import com.geotraveljournal.app.dto.journal.JournalInDto;
import com.geotraveljournal.app.dto.journal.JournalOutDto;
import com.geotraveljournal.app.dto.journal.JournalOutPreviewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JournalService {
    JournalOutDto add(JournalInDto journalDto);
    JournalOutDto update(Long id, JournalInDto journalDto);
    JournalOutDto getDetail(Long id);
    List<JournalOutPreviewDto> getUserHistory(Long userId, Pageable pageable);
    void deleteById(Long id, Long userId);
    void clearHistory(Long userId);
}