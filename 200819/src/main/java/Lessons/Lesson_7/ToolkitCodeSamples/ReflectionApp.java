package Lessons.Lesson_7.ToolkitCodeSamples;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionApp {
    @MarkingAnnotation
    public void markedMethod() {
        System.out.println("Java");
    }

    public static void main(String[] args) {
         List<Method> methodList = new ArrayList<>(Arrays.asList(MyClass.class.getDeclaredMethods()));
         methodList.forEach(method -> {
             if (method.getAnnotation(MarkingAnnotation.class) != null )
                 System.out.println(method);
             });

    }
}
