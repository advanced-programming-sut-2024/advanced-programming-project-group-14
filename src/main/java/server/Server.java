package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.LoginMenuController;
import controller.PreGameMenuController;
import controller.RegisterMenuController;
import model.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class Server {
    private static final int PORT = 12345;
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    String request = in.readLine();
                    JsonObject jsonRequest = gson.fromJson(request, JsonObject.class);
                    String action = jsonRequest.get("action").getAsString();
                    String clientId = jsonRequest.get("clientId").getAsString();  // Retrieve the client ID
                    User thisUser = User.getUserByClientId(clientId);
                    Player thisPlayer = Player.getPlayerByName(thisUser.getUsername());


                    Result result = null;
                    switch (action) {
                        case "register" -> {
                        String username = jsonRequest.get("username").getAsString();
                        String password = jsonRequest.get("password").getAsString();
                        String confirmPassword = jsonRequest.get("confirmPassword").getAsString();
                        String nickname = jsonRequest.get("nickname").getAsString();
                        String email = jsonRequest.get("email").getAsString();
                        result = RegisterMenuController.register(username, password, confirmPassword, nickname, email);
                    out.println(gson.toJson(result));
                        }
                        case "pickQuestion" -> {
                            int number = jsonRequest.get("number").getAsInt();
                            String answer = jsonRequest.get("answer").getAsString();

                            RegisterMenuController.pickQuestion(number, answer);
                            out.println(gson.toJson(result));
                        }
                        case "login" -> {
                            String username = jsonRequest.get("username").getAsString();
                            String password = jsonRequest.get("password").getAsString();
                            boolean stayLoggedIn = jsonRequest.get("stayLoggedIn").getAsBoolean();

                            result = LoginMenuController.login(username, password, stayLoggedIn);
                            out.println(gson.toJson(result));
                        }
                        case "checkAnswer" -> {
                            String username = jsonRequest.get("username").getAsString();
                            String answer = jsonRequest.get("answer").getAsString();

                            result = LoginMenuController.checkAnswer(User.getUserByUsername(username), answer);
                            out.println(gson.toJson(result));
                        }
                        case "getEmail" -> {
                            String username = jsonRequest.get("username").getAsString();

                            result = new Result(true, User.getUserByUsername(username).getEmail());
                            out.println(gson.toJson(result));
                        }
                        case "getFactions" -> {
                            ArrayList<Faction> arrayList = Faction.getFactions();
                            out.println(gson.toJson(arrayList));
                        }
                        case "selectFaction" -> {
                            String factionName = jsonRequest.get("factionName").getAsString();
                            Faction faction = Faction.getFactionByName(factionName);
                            PreGameMenuController.selectFaction(thisPlayer,faction);
                            out.println(gson.toJson(new Result(true,"")));
                        }
                        case "getCommanders" -> {
                            ArrayList<Commander> arrayList = thisPlayer.getFaction().getCommanders();
                            out.println(gson.toJson(arrayList));
                        }
                        case "selectLeader" -> {
                            String leaderName = jsonRequest.get("leaderName").getAsString();
                            Commander commander = thisPlayer.getFaction().getCommanderByName(leaderName);
                            PreGameMenuController.selectLeader(thisPlayer,commander);
                            out.println(gson.toJson(new Result(true,"")));
                        }
                        case "addToDeck" -> {
                            String cardName = jsonRequest.get("cardName").getAsString();
                            Card card = thisPlayer.getFaction().getCardByName(cardName);
                            result = PreGameMenuController.addToDeck(thisPlayer,card);
                            out.println(gson.toJson(result));
                        }
                        case "getDeck" -> {
                            ArrayList<Card> arrayList = thisPlayer.getDeck();
                            out.println(gson.toJson(arrayList));
                        }
                        case "deleteFromDeck" -> {
                            String cardName = jsonRequest.get("cardName").getAsString();
                            Card card = thisPlayer.getFaction().getCardByName(cardName);
                            PreGameMenuController.deleteFromDeck(thisPlayer,card);
                            out.println(gson.toJson(new Result(true,"")));
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
