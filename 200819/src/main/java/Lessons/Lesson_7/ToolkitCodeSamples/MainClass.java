package Lessons.Lesson_7.ToolkitCodeSamples;

import java.lang.reflect.Method;

public class MainClass {
    @AdvancedAnnotation(value = 20.0f)
    private void advAnnotatedMethod() {
        System.out.println("...");
    }

    public static void main(String[] args) {
        try {
            Method m = MainClass.class.getDeclaredMethod("advAnnotatedMethod", null);
            AdvancedAnnotation annotation = m.getAnnotation(AdvancedAnnotation.class);
            System.out.println("value: "+annotation.value());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
