package com.geotraveljournal.app.dao.journal;

import com.geotraveljournal.app.dto.journal.JournalOutPreviewDto;
import com.geotraveljournal.app.model.journal.Journal;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalDao extends JpaRepository<Journal, Long> {

    @Query("SELECT jl FROM Journal jl WHERE jl.id = :id")
    Optional<Journal> getDetail(@Param("id") Long id);

    @Query("SELECT new com.geotraveljournal.app.dto.journal.JournalOutPreviewDto(jl.id, jl.title, jl.createdAt) " +
            "FROM Journal jl WHERE jl.userId = :userId")
    List<JournalOutPreviewDto> getUserHistory(@Param("userId") Long userId, Pageable pageable);

    @Query("DELETE FROM Journal jl WHERE jl.userId = :userId")
    void clearHistory(@Param("userId") Long userId);
}
