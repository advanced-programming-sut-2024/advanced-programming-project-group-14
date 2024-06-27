import model.GameTable;
import model.Player;
import model.Question;
import model.User;
import view.LoginMenuView;

import java.util.Date;
import java.util.Scanner;

public class Main{
    //For debug
    static {
        User newUser = new User("amir","1","ahy","amir@gmail.com");
        User user2 = new User("ali","1","amb","ali@gmail.com");
        Question question = new Question(1,"alooo?");
        question.setAnswer("salam");
        newUser.setQuestion(question);
        newUser.addGamePlayed(new GameTable(new Date(),new Player(newUser),new Player(newUser)));
        newUser.addGamePlayed(new GameTable(new Date(),new Player(user2),new Player(newUser)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenuView.run();
    }
}