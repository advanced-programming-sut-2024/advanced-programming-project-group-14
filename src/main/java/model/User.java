package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {

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
    private static HashMap<String,User> loggedInUsers = new HashMap<>();
    private static HashMap<User,User> currentMatches = new HashMap<>();


    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.gamePlayed = new ArrayList<>();
        this.deck = new ArrayList<>();

        User.addUser(this);
    }
    public User(){

    }

    private static void addUser(User user) {
        if (User.getUserByUsername(user.getUsername()) == null)
            allUsers.add(user);
    }

    public static void addLoggedInUser(String clientId, User loggedInUser) {
        User.loggedInUsers.put(clientId,loggedInUser);
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void resetUsers() {
        allUsers.clear();
        loggedInUsers = new HashMap<>();
    }

    public static HashMap<User, User> getCurrentMatches() {
        return currentMatches;
    }

    public static void addCurrentMatches(User user1, User user2) {
        User.currentMatches.put(user1, user2);
    }

    public static User getUserByClientId(String clientId) {
        return loggedInUsers.get(clientId);
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


    public void setNumOfWin(int numOfWin) {
        this.numOfWin = numOfWin;
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

    public void setGamePlayed(ArrayList<GameTable> gamePlayed){
        this.gamePlayed = gamePlayed;
    }

    public void addGamePlayed(GameTable gameTable) {
        System.out.println("P"+gamePlayed.size());
        this.gamePlayed.add(gameTable);
        System.out.println(gamePlayed.size());
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void addToDeck(Card card) {
        this.deck.add(card);
    }

    public void deleteFromDeck(Card card) {
        this.deck.remove(card);
    }

    public static User getUserByUsername(String username) {
        if (allUsers == null) return null;
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public Card getCardInDeck(String name){
        for (Card card: deck) {
            if (card.getName().equals(name))
                return card;
        }
        return null;
    }
}
