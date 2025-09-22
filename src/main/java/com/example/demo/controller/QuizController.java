package com.example.demo.controller;

import com.example.demo.dto.CreateQuizRequest;
import com.example.demo.dto.QuizResponse;
import com.example.demo.dto.QuizResultForCreatorDTO;
import com.example.demo.dto.QuizSubmission;
import com.example.demo.dto.ScoreResponse;
import com.example.demo.model.Quiz;
import com.example.demo.model.QuizResult;
import com.example.demo.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")

public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // ✅ Create quiz
    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(@RequestBody CreateQuizRequest req) {
        Quiz saved = quizService.createQuiz(req);
        return ResponseEntity.ok(quizService.toResponse(saved));
    }

    // ✅ Get all quizzes
    @GetMapping
    public List<QuizResponse> getAll() {
        return quizService.getAll()
                .stream()
                .map(quizService::toResponse)
                .toList();
    }

    // ✅ Get quiz by ID
    @GetMapping("/{id}")
    public QuizResponse getOne(@PathVariable Long id) {
        return quizService.toResponse(quizService.getById(id));
    }

    // ✅ Get quizzes by domain (return DTO)
    @GetMapping("/domain/{domain}")
    public List<QuizResponse> byDomain(@PathVariable String domain) {
        return quizService.getByDomain(domain)
                .stream()
                .map(quizService::toResponse)
                .toList();
    }

    // ✅ Get quizzes created by a user
    @GetMapping("/myquizzes/{username}")
    public List<QuizResponse> getMyQuizzes(@PathVariable String username) {
        return quizService.getMyQuizzes(username);
    }

    // ✅ Update quiz
    @PutMapping("/{id}")
    public QuizResponse update(@PathVariable Long id, @Valid @RequestBody CreateQuizRequest req) {
        Quiz updated = quizService.updateQuiz(id, req);
        return quizService.toResponse(updated);
    }

    // ✅ Delete quiz
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }

    // ✅ Submit quiz & get score
    @PostMapping("/{id}/submit")
    public ResponseEntity<?> submit(@PathVariable Long id, @RequestBody QuizSubmission submission) {
        String username = submission.getUsername();

        if (quizService.hasAttempted(id, username)) {
            return ResponseEntity.status(403)
                    .body("You have already attempted this quiz.");
        }

        ScoreResponse result = quizService.score(id, submission);
        return ResponseEntity.ok(result);
    }
   

 // ✅ Get quiz results for creator
    @GetMapping("/{id}/creator-results")
    public List<QuizResultForCreatorDTO> getCreatorResults(@PathVariable Long id) {
        return quizService.getResultsForCreator(id);
    }




}
