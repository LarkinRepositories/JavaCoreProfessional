package Lessons.Lesson_7.Homework;

import Lessons.Lesson_7.Homework.Annotations.AfterSuite;
import Lessons.Lesson_7.Homework.Annotations.BeforeSuite;
import Lessons.Lesson_7.Homework.Annotations.Test;
import Lessons.Lesson_7.Homework.TestClasses.MyClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/*
Создать класс, который может выполнять «тесты». В качестве тестов выступают классы с наборами методов с аннотациями @Test.
Для этого у него должен быть статический метод start(), которому в качестве параметра передается или объект типа Class,
или имя класса. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется.
Далее запущены методы с аннотациями @Test, а по завершении всех тестов – метод с аннотацией @AfterSuite.
К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет выбираться порядок
их выполнения. Если приоритет одинаковый, то порядок не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite
должны присутствовать в единственном экземпляре, иначе необходимо бросить RuntimeException при запуске «тестирования».
 */
public class Lesson_7 {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        try {
            start(myClass);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void start(String klassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        start(Class.forName(klassName));
    }
    public static void start(Object klass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        startTests(klass.getClass());
    }

    private static Comparator<Method> methodPriorityComparator = (firstMethod, secondMethod) -> {
        Integer firstMethodPriority = firstMethod.getAnnotation(Test.class).priority();
        Integer secondMethodPriority = secondMethod.getAnnotation(Test.class).priority();
        if (firstMethodPriority.compareTo(secondMethodPriority) == 0)
            return firstMethod.getName().compareTo(secondMethod.getName());
        return firstMethodPriority.compareTo(secondMethodPriority);
    };

    private static <T> void startTests(Class<T> klass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (Collections.frequency(new ArrayList<Annotation>(Arrays.asList(klass.getAnnotations())), BeforeSuite.class) > 1)
            throw new RuntimeException("@BeforeSuite > 1");
        else {
            final T klassInstance;
            final Method[] beforeAfter = {null, null};
            Set<Method> testMethods = new TreeSet<>(methodPriorityComparator);
            klassInstance = klass.newInstance();
            new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods())).forEach(method -> {
                method.setAccessible(true);
                if (method.isAnnotationPresent(BeforeSuite.class))
                    beforeAfter[0] = method;
                else if (method.isAnnotationPresent(AfterSuite.class))
                    beforeAfter[1] = method;
                else if (method.isAnnotationPresent(Test.class))
                    testMethods.add(method);
            });
            if (beforeAfter[0] !=null) beforeAfter[0].invoke(klassInstance);
            testMethods.forEach(method -> {
                try {
                    method.invoke(klassInstance);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            if (beforeAfter[1] != null)
                beforeAfter[1].invoke(klassInstance);
        }
    }
}
