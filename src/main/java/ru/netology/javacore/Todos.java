package ru.netology.javacore;

import com.google.gson.JsonObject;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private static final int LIMIT_TASKS = 7;
    private TreeSet<String> tasks;
    protected LinkedList<JsonObject> log;
    public Todos() {
        tasks = new TreeSet<>();
        log = new LinkedList<>();
    }

    public TreeSet<String> getTasks() {
        return tasks;
    }
    public void addTask(String task) {
        if (tasks.size() < LIMIT_TASKS) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public void restoreTask() {

        if (!log.isEmpty() && (log.getLast().get("type").getAsString()).equals("ADD")) {
            removeTask(log.getLast().get("task").getAsString());
            log.remove(log.getLast());
        } else if (!log.isEmpty() && (log.getLast().get("type").getAsString()).equals("REMOVE")) {
            addTask(log.getLast().get("task").getAsString());
            log.remove(log.getLast());
        }
    }

    public String getAllTasks() {
        StringBuilder allTasks = new StringBuilder();
        for(String task : tasks) {
            allTasks.append(task).append(" ");
        }
        return allTasks.toString();
    }

    public LinkedList<JsonObject> getLog() {
        return log;
    }

    public void setOneLog(JsonObject request) {
        log.add(request);
    }
}
