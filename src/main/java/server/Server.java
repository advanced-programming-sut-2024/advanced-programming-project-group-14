package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.*;
import model.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private static final int PORT = 12345;
    private static Gson gson = new Gson();
    private static boolean startedGame = false;
    private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            GameDatabase.initializeDatabase();
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastMessage(JsonObject message) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                client.sendMessage(message);
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        }

        @Override
        public void run() {
            try {
                String request;
                while ((request = in.readLine()) != null) {
                    JsonObject jsonRequest = gson.fromJson(request, JsonObject.class);
                    String action = jsonRequest.get("action").getAsString();
                    String clientId = jsonRequest.get("clientId").getAsString();  // Retrieve the client ID
                    User thisUser = User.getUserByClientId(clientId);
                    Player thisPlayer = null;
                    if (thisUser != null)
                        thisPlayer = Player.getPlayerByName(thisUser.getUsername());


                    Result result = null;


                    switch (action) {
                        case "register" -> {
                            String username = jsonRequest.get("username").getAsString();
                            String password = jsonRequest.get("password").getAsString();
                            String confirmPassword = jsonRequest.get("confirmPassword").getAsString();
                            String nickname = jsonRequest.get("nickname").getAsString();
                            String email = jsonRequest.get("email").getAsString();
                            result = RegisterMenuController.register(username, password, confirmPassword, nickname, email, clientId);
                            out.println(gson.toJson(result));
                        }
                        case "pickQuestion" -> {
                            int number = jsonRequest.get("number").getAsInt();
                            String answer = jsonRequest.get("answer").getAsString();

                            RegisterMenuController.pickQuestion(number, answer, clientId);
                            out.println(gson.toJson(result));
                        }
                        case "login" -> {
                            String username = jsonRequest.get("username").getAsString();
                            String password = jsonRequest.get("password").getAsString();
                            boolean stayLoggedIn = jsonRequest.get("stayLoggedIn").getAsBoolean();

                            result = LoginMenuController.login(username, password, stayLoggedIn, clientId);
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
                        case "loadAll" -> {
                            LoadController.loadAll();
                            out.println(gson.toJson(new Result(true, "")));
                        }
                        case "getFactionName" -> {
                            out.println(gson.toJson(thisPlayer.getFaction().getName()));
                        }
                        case "selectFaction" -> {
                            String factionName = jsonRequest.get("factionName").getAsString();
                            Faction faction = Faction.getFactionByName(factionName);
                            PreGameMenuController.selectFaction(thisPlayer, faction);
                            out.println(gson.toJson(new Result(true, "")));
                        }
                        case "getCommanders" -> {
                            ArrayList<Commander> arrayList = thisPlayer.getFaction().getCommanders();
                            out.println(gson.toJson(arrayList));
                        }
                        case "selectLeader" -> {
                            String leaderName = jsonRequest.get("leaderName").getAsString();
                            Commander commander = thisPlayer.getFaction().getCommanderByName(leaderName);
                            PreGameMenuController.selectLeader(thisPlayer, commander);
                            out.println(gson.toJson(new Result(true, "")));
                        }
                        case "startGame" -> {
                            String opponentUsername = jsonRequest.get("opponentUsername").getAsString();
                            result = MainMenuController.createGame(thisUser, opponentUsername);
                            out.println(gson.toJson(result));
                        }
                        case "logout" -> {
                            MainMenuController.logout(clientId);
                            out.println(gson.toJson(result));
                        }
                        case "checkInGame" -> {
                            result = MainMenuController.checkIsInGame(thisUser);
                            out.println(gson.toJson(result));
                        }
                        case "addToDeck" -> {
                            String cardName = jsonRequest.get("cardName").getAsString();
                            Card card = thisPlayer.getFaction().getCardByName(cardName);
                            result = PreGameMenuController.addToDeck(thisPlayer, card);
                            out.println(gson.toJson(result));
                        }
                        case "getDeck" -> {
                            ArrayList<Card> arrayList = thisPlayer.getDeck();
                            out.println(gson.toJson(arrayList));
                        }
                        case "deleteFromDeck" -> {
                            String cardName = jsonRequest.get("cardName").getAsString();
                            Card card = thisPlayer.getFaction().getCardByName(cardName);
                            PreGameMenuController.deleteFromDeck(thisPlayer, card);
                            out.println(gson.toJson(new Result(true, "")));
                        }
                        case "sendMessage" -> {
                            String message = jsonRequest.get("message").getAsString();
                            String timestamp = jsonRequest.get("timestamp").getAsString();
                            JsonObject jsonMessage = new JsonObject();
                            jsonMessage.addProperty("type", "message");
                            jsonMessage.addProperty("clientId", clientId);
                            jsonMessage.addProperty("message", message);
                            jsonMessage.addProperty("timestamp", timestamp);
                            Server.broadcastMessage(jsonMessage);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clients.remove(this);
            }
        }

        public void sendMessage(JsonObject message){
            out.println(message.toString());
        }
    }
}
