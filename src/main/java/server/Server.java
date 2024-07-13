package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.RegisterMenuController;
import model.Result;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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

                    System.out.println("Received request from client: " + clientId);
                    System.out.println("Request: " + request);

                    Result result = null;
                    if (action.equals("register")) {
                        String username = jsonRequest.get("username").getAsString();
                        String password = jsonRequest.get("password").getAsString();
                        String confirmPassword = jsonRequest.get("confirmPassword").getAsString();
                        String nickname = jsonRequest.get("nickname").getAsString();
                        String email = jsonRequest.get("email").getAsString();
                        result = RegisterMenuController.register(username, password, confirmPassword, nickname, email);
                    } else if (action.equals("pickQuestion")) {
                        int number = jsonRequest.get("number").getAsInt();
                        String answer = jsonRequest.get("answer").getAsString();

                        RegisterMenuController.pickQuestion(number, answer);
                    } else if (action.equals("login")) {
                        int number = jsonRequest.get("number").getAsInt();
                        String answer = jsonRequest.get("answer").getAsString();

                        RegisterMenuController.pickQuestion(number, answer);
                    }

                    String jsonResponse = gson.toJson(result);
                    System.out.println("Response to client " + clientId + ": " + jsonResponse);

                    out.println(jsonResponse);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
