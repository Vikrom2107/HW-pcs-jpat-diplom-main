package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TodosTests {
    Todos todos = new Todos();
    @Test
    void addTask() {
        todos.addTask("Бег");

        String actual = todos.getTasks().get(0);
        String expect = "Бег";
        Assertions.assertEquals(expect,actual);
    }

    @Test
    void removeTask() {
        todos.removeTask("Бег");

        boolean actual = todos.getTasks().isEmpty();
        boolean expect = true;
        Assertions.assertEquals(expect,actual);
    }

    @Test
    void getAllTasks() {
        todos.addTask("Прогулка");
        String actual = todos.getAllTasks();
        String expect = "Прогулка ";
        Assertions.assertEquals(expect,actual);
    }
}