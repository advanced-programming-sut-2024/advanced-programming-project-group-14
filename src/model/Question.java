package model;

import java.util.ArrayList;

public class Question {

    private int number;
    private String question;
    private String answer;
    private static ArrayList<Question> questions = new ArrayList<>();

    public Question(int number, String question, String answer) {
        this.number = number;
        this.question = question;
        this.answer = answer;
        questions.add(this);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public static ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }



    static {
        Question question1 = new Question(1,"a", "b");


    }
}
