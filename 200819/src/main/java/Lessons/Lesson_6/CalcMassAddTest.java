package Lessons.Lesson_6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalcMassAddTest {
    private int a, b,c;
    private Calculator calculator;
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0,0,0},
                {1,1,2},
                {2,2,4},
                {5,5,10},
                {1,3,4},
                {6,-2,4},
                {-1,5,4}
        });
    }

    public CalcMassAddTest(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Before
    public void init() {
        calculator = new Calculator();
    }

    @After
    public void tearDown() throws Exception {
        calculator = null;
    }

    @Test
    public void massTestAddition() {
        assertEquals(c, calculator.addition(a,b));
    }
}

