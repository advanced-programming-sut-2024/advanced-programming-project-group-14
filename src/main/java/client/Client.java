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

    public static Result getResponse(JsonObject jsonRequest) {
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
}