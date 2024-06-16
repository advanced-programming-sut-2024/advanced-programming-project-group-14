package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.view.LoginMenuView;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenuView.run();
    }
}