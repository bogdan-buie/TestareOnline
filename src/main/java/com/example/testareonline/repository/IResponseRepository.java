package com.example.testareonline.repository;

import com.example.testareonline.entity.Question;
import com.example.testareonline.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResponseRepository extends JpaRepository<Response, Long> {

}
