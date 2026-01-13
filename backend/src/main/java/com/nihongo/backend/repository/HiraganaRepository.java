package com.nihongo.backend.repository;

import com.nihongo.backend.model.Hiragana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HiraganaRepository extends JpaRepository<Hiragana, Long> {

    // Custom query to fetch random distractors (wrong answers)
    @Query(value = "SELECT * FROM hiragana_characters WHERE id != :correctId ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Hiragana> findRandomDistractors(Long correctId);
}
