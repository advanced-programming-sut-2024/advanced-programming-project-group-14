package model;

import java.util.ArrayList;

public class Question {
    private int number;
    private String questionText;
    private String answer;
    private static ArrayList<Question> questions = new ArrayList<>();

    public Question(int number, String question) {
        this.number = number;
        this.questionText = question;
        questions.add(this);
    }

    public Question(Question question, String answer) {
        this.questionText = question.getQuestionText();
        this.answer = answer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestionText() {
        return questionText;
    }

    public static ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestionText(String question) {
        this.questionText= question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static Question getQuestionByText(String questionText){
        for (Question question: questions) {
            if (question.getQuestionText().equals(questionText))
                return question;
        }
        return null;
    }
}
