// src/client/Client.java
package client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.*;
import org.bytedeco.opencv.presets.opencv_core;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final String CLIENT_ID = "Client" + new Random().nextInt(0, 10000);  // Unique identifier for this client
    private static Gson gson = new Gson();

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

    public static <T> List<T> getArrayList(JsonObject jsonRequest, TypeToken<List<T>> typeToken) {
        jsonRequest.addProperty("clientId", CLIENT_ID);  // Add client ID to the request
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(gson.toJson(jsonRequest));

            String response = in.readLine();
            return gson.fromJson(response, typeToken.getType());
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

    public static String getFactionName(JsonObject jsonRequest) {
        jsonRequest.addProperty("clientId", CLIENT_ID);  // Add client ID to the request
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(gson.toJson(jsonRequest));

            String response = in.readLine();
            return gson.fromJson(response, String.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
