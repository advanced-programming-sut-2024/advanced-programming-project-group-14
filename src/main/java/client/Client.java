// src/client/Client.java
package client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Result;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static Gson gson = new Gson();

    public static Result register(String username, String password, String passwordConfirm, String nickname, String email) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            JsonObject jsonRequest = new JsonObject();
            jsonRequest.addProperty("action", "register");
            jsonRequest.addProperty("username", username);
            jsonRequest.addProperty("password", password);
            jsonRequest.addProperty("confirmPassword", passwordConfirm);
            jsonRequest.addProperty("nickname", nickname);
            jsonRequest.addProperty("email", email);

            out.println(gson.toJson(jsonRequest));

            String response = in.readLine();
            return gson.fromJson(response, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "Could not connect to the server.");
        }
    }

    public static Result pickQuestion(int number, String answer) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            JsonObject jsonRequest = new JsonObject();
            jsonRequest.addProperty("action", "pickQuestion");
            jsonRequest.addProperty("number", number);
            jsonRequest.addProperty("answer", answer);

            out.println(gson.toJson(jsonRequest));

            String response = in.readLine();
            return gson.fromJson(response, Result.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}