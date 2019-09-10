package Lessons.Lesson_5.HomeWork.Stages;

import Lessons.Lesson_5.HomeWork.Cars.Car;

public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car car);

}
