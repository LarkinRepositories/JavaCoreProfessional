package Lessons.Lesson_6;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;

public class LambdaSamples {

    public static void main(String[] args) {
        String[] planets = new String[] {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("Printed by length");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(planets));

        Timer t = new Timer(100, event -> System.out.println("This time is "+new Date()));
        t.start();
        // выполнять программу до тех пор, пока пользователь не выберет кнопку "Ok"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);

    }
}
