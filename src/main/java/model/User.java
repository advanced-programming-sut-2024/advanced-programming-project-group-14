package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User {

    private static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Question question;
    private int maxScore;
    private int rank;
    private int numOfGamePlayed;
    private int numOfDraw;
    private int numOfWin;
    private int numOfLose;
    private ArrayList<GameTable> gamePlayed;
    private ArrayList<Card> deck;
    private static User loggedInUser;

    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.gamePlayed = new ArrayList<>();
        this.deck = new ArrayList<>();
        allUsers.add(this);
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getRank() {
        return rank;
    }

    public int getNumOfGamePlayed() {
        return numOfGamePlayed;
    }

    public void increaseNumOfGamePlayed() {
        this.numOfGamePlayed++;
    }

    public int getNumOfDraw() {
        return numOfDraw;
    }

    public void increaseNumOfDraw() {
        this.numOfDraw++;
    }

    public int getNumOfWin() {
        return numOfWin;
    }

    public void increaseNumOfWin() {
        this.numOfWin++;
    }

    public int getNumOfLose() {
        return numOfLose;
    }

    public void increaseNumOfLose() {
        this.numOfLose++;
    }

    public ArrayList<GameTable> getGamePlayed() {
        return gamePlayed;
    }

    public void addGamePlayed(GameTable gameTable) {
        this.gamePlayed.add(gameTable);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void addToDeck(Card card) {
        this.deck.add(card);
    }
    public void deleteFromDeck(Card card) {
        this.deck.remove(card);
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }
    public void saveDeckByFileAddress(String fileAddress) {
        try (FileWriter fileWriter = new FileWriter(fileAddress)) {
            for (Card card : deck) {
                fileWriter.write(card + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveDeckByDeckName(String deckName){
        //todo
    }
    public int numberOfSpecificCardInDeck() {
        int count = 0;
        for (Card card : deck) {
            if (card.getType().equals("weather") || card.getType().equals("spell")) {
                count++;

            }
        }
        return count;
    }

    public void loadDeckByFileAddress(String fileAddress) {
        //todo
    }
    public void loadDeckByDeckName(String deckName){
        //todo
    }
}
