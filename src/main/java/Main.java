import model.Question;
import model.User;
import view.LoginMenuView;

import java.util.Scanner;

public class Main{
    //For debug
    static {
        User newUser = new User("amir","123456yY!","ahy","amir@gmail.com");
        Question question = new Question(1,"alooo?");
        question.setAnswer("salam");
        newUser.setQuestion(question);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenuView.run();
    }
}