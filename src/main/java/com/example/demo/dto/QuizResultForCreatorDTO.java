package com.example.demo.dto;

public class QuizResultForCreatorDTO {
    private String username;  // participant name
    private int score;        // score obtained
    private int total;        // total questions

    public QuizResultForCreatorDTO() {}

    public QuizResultForCreatorDTO(String username, int score, int total) {
        this.username = username;
        this.score = score;
        this.total = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
