package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UsersManager {
    private static final String DATA_FILE_PATH = "users.json";
    private ObjectMapper objectMapper;

    public UsersManager() {
        this.objectMapper = new ObjectMapper();
    }

    public void saveUsers(ArrayList<User> users) {
        try {
            objectMapper.writeValue(new File(DATA_FILE_PATH), users);
            System.out.println("Users and decks saved to " + DATA_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving users and decks: " + e.getMessage());
        }
    }

    public void loadUsers() {
        try {
            User.getAllUsers().addAll(objectMapper.readValue(new File(DATA_FILE_PATH), objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class)));
        } catch (IOException e) {
            System.err.println("Error loading users and decks: " + e.getMessage());
        }
    }
}

