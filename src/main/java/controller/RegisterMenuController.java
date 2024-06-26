package controller;

import model.Question;
import model.Result;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class RegisterMenuController {

    static Random random = new Random();

    public static Result register(String username, String password, String passwordConfirm, String nickname, String email) {
        if (User.getUserByUsername(username) != null)
            return new Result(false, "Username is already taken! Do you want to use this username" + username + "-" + random.nextInt(100));

        if (!Pattern.matches("[a-zA-Z0-9\\-]+", username))
            return new Result(false, "Username is invalid!");

        if (!Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", email))
            return new Result(false, "Email is invalid!");

        if (!Pattern.matches("^[a-zA-Z0-9!@#$%^&*]+$", password))
            return new Result(false, "Password is invalid!");

        if (password.length() < 8)
            return new Result(false, "Password is to short!");

        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*"))
            return new Result(false, "Password should have at least one lowercase letter and one uppercase letter");

        if (!password.matches(".*[0-9].*"))
            return new Result(false, "Password should have at least one number");

        if (!password.matches(".*[!@#$%^&*].*"))
            return new Result(false, "Password should have at least one special character");

        if (!password.equals(passwordConfirm))
            return new Result(false, "Passwords are not same");

        User user = new User(username, password, nickname, email);
        return new Result(true, "Register successful");
    }

    public static String generateRandomPassword() {

        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";
        String SPECIAL_CHARS = "!@#$%^&*";
        int length = 10;

        List<Character> chars = new ArrayList<>();
        chars.add(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
        chars.add(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
        chars.add(NUMBER.charAt(random.nextInt(NUMBER.length())));
        chars.add(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        for (int i = 4; i < length; i++) {
            String charSet = CHAR_LOWER + CHAR_UPPER + NUMBER + SPECIAL_CHARS;
            chars.add(charSet.charAt(random.nextInt(charSet.length())));
        }

        Collections.shuffle(chars);

        StringBuilder password = new StringBuilder();
        for (Character ch : chars)
            password.append(ch);

        return password.toString();
    }

    public static String showQuestion() {
        return User.getLoggedInUser().getQuestion().getQuestionText();
    }

    public static void pickQuestion(int number, String answer) {
        Question question = new Question(Question.getQuestions().get(number), answer);
        User.getLoggedInUser().setQuestion(question);
    }

}

