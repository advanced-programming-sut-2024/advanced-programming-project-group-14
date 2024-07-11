package controller;

import model.Result;
import model.User;

import java.util.regex.Pattern;

public class ProfileMenuController {

    public static Result changeUsername(String username) {
        if (User.getUserByUsername(username) != null)
            return new Result(false, "Username is already taken!");
        if (!Pattern.matches("[a-zA-Z0-9\\-]+", username))
            return new Result(false, "Username is invalid!");

        User.getLoggedInUser().setUsername(username);
        return new Result(true, "Username changed successfully");
    }

    public static Result changeNickname(String nickname) {
        User.getLoggedInUser().setNickname(nickname);
        return new Result(true, "Nickname changed successfully");
    }

    public static Result changeEmail(String email) {
        if (!Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", email))
            return new Result(false, "Email is invalid!");

        User.getLoggedInUser().setEmail(email);
        return new Result(true, "Email changed successfully");
    }

    public static Result changePassword(String newPassword, String oldPassword) {
        if (!User.getLoggedInUser().getPassword().equals(oldPassword))
            return new Result(false, "Password is incorrect!");

        if (!Pattern.matches("^[a-zA-Z0-9!@#$%^&*]+$", newPassword))
            return new Result(false, "Old password is invalid!");

        if (newPassword.length() < 8)
            return new Result(false, "Password is to short!");

        if (!newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[a-z].*"))
            return new Result(false, "Password should have at least one lowercase letter and one uppercase letter");

        if (!newPassword.matches(".*[0-9].*"))
            return new Result(false, "Password should have at least one number");

        if (!newPassword.matches(".*[!@#$%^&*].*"))
            return new Result(false, "Password should have at least one special character");

        User.getLoggedInUser().setPassword(newPassword);
        return new Result(true, "Password changed successfully");
    }

    public static Result numberOfGameToShow(String number) {
        int numberToShow = 5;
        int numberOfGamePlayed = User.getLoggedInUser().getGamePlayed().size();
        if (number.isEmpty()) {
            if (numberOfGamePlayed < 5)
                numberToShow = numberOfGamePlayed;
        } else if (Integer.parseInt(number) < 1)
            return new Result(false, "The number should be greater than 0");

        if (numberOfGamePlayed == 0)
            return new Result(false, "You haven't played any games yet!");
        else if (numberOfGamePlayed < Integer.parseInt(number))
            numberToShow = numberOfGamePlayed;
        else
            numberToShow = Integer.parseInt(number);


        return new Result(true, String.valueOf(numberToShow));
    }


}
