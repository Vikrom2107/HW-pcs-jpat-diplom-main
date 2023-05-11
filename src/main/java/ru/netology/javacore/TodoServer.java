package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class TodoServer {
    protected int port;
    protected Todos todos;
    protected LinkedList<JsonObject> log = new LinkedList<>();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Starting server at " + port + "...");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
                    System.out.println("New connection accepted");
                    final String name = in.readLine();
//                    System.out.println(name);
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    JsonObject request = gson.fromJson(name, JsonObject.class);
                    requestProcessing(request);

                    out.println(todos.getAllTasks());

                    System.out.println("log");
                    for (JsonObject object : log) {
                        System.out.println(object.toString());
                    }
                } catch (IOException e) {
                    System.out.println("Не могу стартовать сервер");
                    e.printStackTrace();
                }
            }
        }
    }
    public void requestProcessing(JsonObject request) {
        if ((request.get("type").getAsString()).equals("ADD")) {
            todos.addTask(request.get("task").getAsString());
            log.add(request);
        }
        if ((request.get("type").getAsString()).equals("REMOVE")) {
            todos.removeTask(request.get("task").getAsString());
            log.add(request);
        }
        if ((request.get("type").getAsString()).equals("RESTORE")) {
            if (!log.isEmpty() && (log.getLast().get("type").getAsString()).equals("ADD")) {
                todos.removeTask(log.getLast().get("task").getAsString());
                log.remove(log.getLast());
            } else if (!log.isEmpty() && (log.getLast().get("type").getAsString()).equals("REMOVE")) {
                todos.addTask(log.getLast().get("task").getAsString());
                log.remove(log.getLast());
            }
        }
    }
}
