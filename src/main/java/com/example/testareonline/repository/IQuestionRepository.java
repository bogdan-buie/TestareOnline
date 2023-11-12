package com.example.testareonline.repository;

import com.example.testareonline.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepository extends JpaRepository<Question, Long> {

}
