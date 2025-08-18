package repo;

import model.Quiz;
import util.InputValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QuizManager {
    private static final Map<Long, Quiz> questions = new HashMap<>();
    private static  Long quizCount = 1L;

    static {
        questions.put(quizCount,new Quiz(
                quizCount++,
                "What’s the difference between a debit and credit card?",
                List.of("Debit draws from your account", "Credit borrows money", "Both", "None"),
                "Both"
        ));

        questions.put(quizCount,new Quiz(
                quizCount++,
                "What does APR mean?",
                List.of("Annual Percentage Rate", "Automatic Payment Reminder", "Average Payment Rate", "All of the above"),
                "Annual Percentage Rate"
        ));
    }

    public static void startQuiz(Scanner scan){
        int score = 0;
        System.out.println("\n===== Financial Literacy Quiz =====");
        for(Quiz quiz : questions.values()){
            System.out.println("\n" + quiz.getQuestion());
            List<String> opts = quiz.getChoices();
            for (int i = 0; i < opts.size(); i++) {
                System.out.println((i + 1) + ". " + opts.get(i));
            }

            int answerIndex = InputValidator.getIntegerInput(scan, "Enter your choice (1-" + opts.size() + "): ");
            String selected = opts.get(answerIndex - 1);

            if (selected.equals(quiz.getCorrectAnswer())) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer: " + quiz.getCorrectAnswer());
            }
        }

        System.out.println("\nYour Score: " + score + "/" + questions.size());
        System.out.println("====================================\n");

        }
    }




