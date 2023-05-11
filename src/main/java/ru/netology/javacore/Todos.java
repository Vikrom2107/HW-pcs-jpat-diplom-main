package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private static final int LIMIT_TASKS = 7;
    private List<String> tasks = new ArrayList<>();

    public List<String> getTasks() {
        return tasks;
    }
    public void addTask(String task) {
        if (tasks.size() < LIMIT_TASKS) {
            tasks.add(task);
            Collections.sort(tasks);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
        Collections.sort(tasks);
    }

    public String getAllTasks() {
        StringBuilder allTasks = new StringBuilder();
        for(String task : tasks) {
            allTasks.append(task + " ");
        }
        return allTasks.toString();
    }

}
