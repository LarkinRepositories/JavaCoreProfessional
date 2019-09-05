package Lessons.Lesson_6.HomeWork;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class Lesson_6Test {
    private Lesson_6 homework;
    private Integer[] array;
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
        );
    }

    @Before
    public void init() {
        homework = new Lesson_6();
        array = new Integer[] {1,1,4,1,7};
    }

    @After
    public void tearDown() {
        homework = null;
    }

    @Test
    public void task1() {
        assertEquals("[1, 7]", Arrays.toString(homework.task1(array)));
    }

    @Test
    public void task2() {
    }
}