// src/client/Client.java
package client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Commander;
import model.Faction;
import model.Result;
import model.User;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final String CLIENT_ID = "Client1";  // Unique identifier for this client
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

    public static ArrayList<Faction> getFactionArrayList(JsonObject jsonRequest) {
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

    public static ArrayList<Commander> getCommanderArrayList(JsonObject jsonRequest) {
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
}
