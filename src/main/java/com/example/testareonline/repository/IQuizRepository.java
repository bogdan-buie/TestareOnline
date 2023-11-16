package com.example.testareonline.repository;

import com.example.testareonline.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuizRepository extends JpaRepository<Quiz, Long> {

}
