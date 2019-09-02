package Lessons.Lesson_3;

import java.io.*;

public class StudentMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //сериализация
        Student s = new Student(1, "Bob");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stud.ser"));
        oos.writeObject(s);
        oos.close();
        //десериализация
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stud.ser"));
        Student s2 = (Student) ois.readObject();
        s2.info();
        ois.close();

    }
}
