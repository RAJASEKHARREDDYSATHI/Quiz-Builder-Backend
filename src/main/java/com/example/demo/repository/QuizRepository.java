package com.example.demo.repository;

import com.example.demo.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
  List<Quiz> findByDomainIgnoreCase(String domain);
  List<Quiz> findByCreatedBy(String createdBy);
}

