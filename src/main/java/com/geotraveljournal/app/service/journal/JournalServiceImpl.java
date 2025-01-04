package com.geotraveljournal.app.service.journal;

import com.geotraveljournal.app.dao.journal.JournalDao;
import com.geotraveljournal.app.dto.journal.JournalInDto;
import com.geotraveljournal.app.dto.journal.JournalOutDto;
import com.geotraveljournal.app.dto.journal.JournalOutPreviewDto;
import com.geotraveljournal.app.model.journal.Journal;

import com.geotraveljournal.app.responses.core.CustomException;
import com.geotraveljournal.app.responses.core.messages.JournalErrors;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JournalServiceImpl implements JournalService {
    @Autowired
    private final JournalDao journalDao;

    public JournalServiceImpl(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

    @Override
    public JournalOutDto add(JournalInDto journalDto) {
        if (journalDto == null || journalDto.getUserId() == null || journalDto.getTitle() == null) {
            throw new CustomException(false, JournalErrors.INVALID_JOURNAL_DATA);
        }

        Journal journal = new Journal();
        journal.setTitle(journalDto.getTitle());
        journal.setDescription(journalDto.getDescription());
        journal.setDistance(journalDto.getDistance());
        journal.setRoute(journalDto.getRoute());
        journal.setUserId(journalDto.getUserId());

        journal = journalDao.save(journal);
        return mapToDto(journal);
    }

    @Override
    public JournalOutDto update(Long id, JournalInDto journalDto) {
        if (id == null || journalDto == null) {
            throw new CustomException(false, JournalErrors.INVALID_ID_OR_DATA);
        }

        Optional<Journal> optionalJournal = journalDao.findById(id);
        if (optionalJournal.isEmpty()) {
            throw new CustomException(false, JournalErrors.JOURNAL_NOT_FOUND);
        }

        Journal journal = optionalJournal.get();
        if (journalDto.getTitle() != null) {
            journal.setTitle(journalDto.getTitle());
        }
        if (journalDto.getDescription() != null) {
            journal.setDescription(journalDto.getDescription());
        }
        if (journalDto.getDistance() != null) {
            journal.setDistance(journalDto.getDistance());
        }
        if (journalDto.getRoute() != null) {
            journal.setRoute(journalDto.getRoute());
        }

        journal = journalDao.save(journal);
        return mapToDto(journal);
    }

    @Override
    public JournalOutDto getDetail(Long id) {
        if (id == null) {
            throw new CustomException(false, JournalErrors.INVALID_ID_OR_DATA);
        }

        Optional<Journal> optionalJournal = journalDao.findById(id);
        if (optionalJournal.isEmpty()) {
            throw new CustomException(false, JournalErrors.JOURNAL_NOT_FOUND);
        }

        return mapToDto(optionalJournal.get());
    }

    @Override
    public List<JournalOutPreviewDto> getUserHistory(Long userId, Pageable pageable) {
        return journalDao.getUserHistory(userId, pageable);
    }

    @Override
    public void deleteById(Long id, Long userId) {
        if (id == null || userId == null) {
            throw new CustomException(false, JournalErrors.INVALID_ID_OR_USER_ID);
        }

        Optional<Journal> optionalJournal = journalDao.findById(id);
        if (optionalJournal.isEmpty()) {
            throw new CustomException(false, JournalErrors.JOURNAL_NOT_FOUND);
        }

        Journal journal = optionalJournal.get();
        if (!journal.getUserId().equals(userId)) {
            throw new CustomException(false, JournalErrors.USER_MISMATCH);
        }

        journalDao.deleteById(id);
    }

    @Override
    public void clearHistory(Long userId) {
        if (userId == null) {
            throw new CustomException(false, JournalErrors.INVALID_USER_ID);
        }

        journalDao.clearHistory(userId);
    }

    private JournalOutDto mapToDto(Journal journal) {
        JournalOutDto dto = new JournalOutDto();
        dto.setId(journal.getId());
        dto.setTitle(journal.getTitle());
        dto.setDescription(journal.getDescription());
        dto.setDistance(journal.getDistance());
        dto.setRoute(journal.getRoute());
        dto.setUserId(journal.getUserId());
        dto.setCreatedAt(journal.getCreatedAt());
        dto.setUpdatedAt(journal.getUpdatedAt());
        return dto;
    }

    private JournalOutDto mapToDtoFromHistory(Object[] history) {
        JournalOutDto dto = new JournalOutDto();
        dto.setId((Long) history[0]);
        dto.setTitle((String) history[1]);
        dto.setCreatedAt((Instant) history[2]);
        return dto;
    }
}
