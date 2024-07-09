package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
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
            objectMapper.writeValue(new File(USERS_JSON), users);
            System.out.println("Users and decks saved to " + USERS_JSON);
        } catch (IOException e) {
            System.err.println("Error saving users and decks: " + e.getMessage());
        }
    }

    public void loadUsers() {
        try {
            User.getAllUsers().addAll(objectMapper.readValue(new File(USERS_JSON), objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class)));
        } catch (IOException e) {
            System.err.println("Error loading users and decks: " + e.getMessage());
        }
    }
    public void saveStayLoggedInUser(User user) {
        try {
            objectMapper.writeValue(new File(USER_JSON), user);
            System.out.println("Stay logged in user saved to " + USER_JSON);
        } catch (IOException e) {
            System.err.println("Error saving stay logged in user: " + e.getMessage());
        }
    }
    public void loadStayLoggedInUser() {
        try {
            User.setLoggedInUser(objectMapper.readValue(new File(USER_JSON), User.class));
        } catch (IOException e) {
            System.err.println("Error loading stay logged in user: " + e.getMessage());
        }
    }
}

