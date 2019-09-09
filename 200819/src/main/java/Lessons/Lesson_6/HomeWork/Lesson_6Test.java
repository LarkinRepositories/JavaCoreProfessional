package Lessons.Lesson_6.HomeWork;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class Lesson_6Test {
    private Lesson_6 homework;
    private Integer[] test;
    private Integer[] expected;
    private boolean task2Expected;

    public Lesson_6Test(Integer[] test, Integer[] expected) {
        this.test = test;
        this.expected = expected;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new Integer[]{1,1,1,1,4,1,7}, new Integer[]{1,7}},
                {new Integer[]{1,4,1,1,1,7}, new Integer[]{1,1,1,7}},
                {new Integer[]{4,1,1,7,7,1,7}, new Integer[]{1,1,7,7,1,7}},
                {new Integer[]{1,1,4,4,4,4,4,1}, new Integer[]{1}}
        });
    }

    @Before
    public void init() {
        homework = new Lesson_6();
    }

    @After
    public void tearDown() {
        homework = null;
    }

    @Test
    public void task1() {
        Assert.assertArrayEquals(expected, homework.task1(test));
    }

    @Test
    public void task2() {
        Assert.assertTrue("true", homework.task2(test));
    }
    @Test
    public void task2False() {
        Assert.assertFalse("false", homework.task2(new Integer[]{1,1,1,1,1,1}));
    }

    @Test
    public void task2False1() {
        Assert.assertFalse("false", homework.task2(new Integer[]{4,4,4,4,4,4}));
    }

}