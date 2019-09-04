package Lessons.Lesson_5.HomeWork.Stages;

import Lessons.Lesson_5.HomeWork.Cars.Car;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = String.format("Дорога %s метров", length);
    }
    @Override
    public void go(Car car) {
        try {
            System.out.println(String.format("%s начал этап %s", car.getName(), description));
            Thread.sleep(length/car.getSpeed() * 1000);
            System.out.println(String.format("%s закончил этап %s", car.getName(), description));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
