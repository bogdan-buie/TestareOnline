package com.example.testareonline.repository;

import com.example.testareonline.entity.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuizSubmission extends JpaRepository<QuizSubmission, Long> {
}
