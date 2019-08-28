package Lessons.Lesson_3;

import java.io.Serializable;

public class Student implements Serializable {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void info() {
        System.out.println(id +" "+ name);
    }
}
