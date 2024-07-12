package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class UsersManager {
    private static final String USERS_JSON = "users.json";
    private static final String USER_JSON = "stayLoggedInUser.json";
    private ObjectMapper objectMapper;

    public UsersManager() {
        this.objectMapper = new ObjectMapper();
    }

    public void saveUsers(ArrayList<User> users) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(USERS_JSON));
            objectOutputStream.writeObject(users);
            objectOutputStream.close();
            System.out.println("Users and decks saved to " + USERS_JSON);
        } catch (IOException e) {
            System.err.println("Error saving users and decks: " + e.getMessage());
        }
    }

    public void loadUsers() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(USERS_JSON));
            User.getAllUsers().addAll((ArrayList<User>) objectInputStream.readObject());
            objectInputStream.close();
        } catch (IOException e) {
            System.err.println("Error loading users and decks: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveStayLoggedInUser(User user) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(USER_JSON));
            objectOutputStream.writeObject(user);
            objectOutputStream.close();
            System.out.println("Stay logged in user saved to " + USER_JSON);
        } catch (Exception e) {
            System.err.println("Error saving stay logged in user: " + e.getMessage());
        }
    }
    public void loadStayLoggedInUser() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(USER_JSON));
            User user = (User) objectInputStream.readObject();
            User.getAllUsers().add(user);
            User.setLoggedInUser(user);
            objectInputStream.close();
        } catch (IOException e) {
            System.err.println("Error loading stay logged in user: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

