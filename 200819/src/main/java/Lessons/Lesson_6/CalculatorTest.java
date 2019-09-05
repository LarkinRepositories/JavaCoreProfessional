package Lessons.Lesson_6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @After
    public void tearDown() throws Exception {
        calculator = null;
    }

    @Test
    public void addition() {
        assertEquals(4, calculator.addition(2,2));
    }

    @Test
    public void substraction() {
        assertEquals(2, calculator.substraction(4,2));
    }

    @Test
    public void multiplication() {
        assertEquals(8, calculator.multiplication(4,2));
    }

    @Test(timeout = 10000, expected = ArithmeticException.class)
    //@Ignore ("Пока тестировать не нужно")
    public void division() {
        assertEquals(10, calculator.division(100,0));
    }
}