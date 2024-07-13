package client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.*;
import view.ChatView;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final String CLIENT_ID = "Client" + new Random().nextInt(0, 10000);  // Unique identifier for this client
    private static Gson gson = new Gson();
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ChatView chatView;

    public Client() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Result getResult(JsonObject jsonRequest) {
        jsonRequest.addProperty("clientId", CLIENT_ID);  // Add client ID to the request
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(gson.toJson(jsonRequest));

            String response = in.readLine();
            return gson.fromJson(response, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "Could not connect to the server.");
        }
    }

    public static <T> ArrayList<T> getArrayList(JsonObject jsonRequest) {
        jsonRequest.addProperty("clientId", CLIENT_ID);  // Add client ID to the request
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(gson.toJson(jsonRequest));

            String response = in.readLine();
            return gson.fromJson(response, ArrayList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static Player getPlayer(JsonObject jsonRequest) {
        jsonRequest.addProperty("clientId", CLIENT_ID);  // Add client ID to the request
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(gson.toJson(jsonRequest));

            String response = in.readLine();
            return gson.fromJson(response, Player.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setChatWindow(ChatView chatView) {
        this.chatView = chatView;
    }

    public void sendMessage(String message) {
        JsonObject jsonMessage = new JsonObject();
        jsonMessage.addProperty("action", "sendMessage");
        jsonMessage.addProperty("clientId", CLIENT_ID);
        jsonMessage.addProperty("message", message);
        jsonMessage.addProperty("timestamp", String.valueOf(LocalTime.now()));

        out.println(gson.toJson(jsonMessage));
    }

    public void listenForMessages() {
        new Thread(() -> {
            String response;
            try {
                while ((response = in.readLine()) != null) {
                    JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
                    handleResponse(jsonResponse);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleResponse(JsonObject response) {
        String type = response.get("type").getAsString();
        if (type.equals("message")) {
            String sender = response.get("clientId").getAsString();
            String message = response.get("message").getAsString();
            String timestamp = response.get("timestamp").getAsString();
            String formattedMessage = "[" + timestamp + "] " + sender + ": " + message;
            if (chatView != null) {
                chatView.displayMessage(formattedMessage);
            }
        }
    }
}
