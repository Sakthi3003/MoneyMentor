package model;

import java.util.List;

public class Quiz {
    private Long id;
    private String question;
    private List<String> choices;
    private String correctAnswer;

    public Quiz(Long id, String question, List<String> choices, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
