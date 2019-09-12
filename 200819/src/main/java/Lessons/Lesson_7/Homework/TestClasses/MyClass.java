package Lessons.Lesson_7.Homework.TestClasses;

import Lessons.Lesson_7.Homework.Annotations.AfterSuite;
import Lessons.Lesson_7.Homework.Annotations.BeforeSuite;
import Lessons.Lesson_7.Homework.Annotations.Test;

public class MyClass {
    @BeforeSuite
    public void firstToRun() {
        System.out.println("this is from the method with @BeforeSuite");
    }

    @BeforeSuite
    public void exceptionThrower() {
        System.out.println("This would never been seen");
    }
    @Test(priority = 1)
    public void firstTestMethod() {
        System.out.println("this is from first test method");
    }

    @Test(priority = 3)
    public void thirdTestmethod() {
        System.out.println("this is from third test method");
    }

    @Test(priority = 2)
    public void secondTestmethod() {
        System.out.println("this if from second test method");
    }

    @AfterSuite
    public void lastToRun() {
        System.out.println("this is from the method with @AfterSuite");
    }
}
