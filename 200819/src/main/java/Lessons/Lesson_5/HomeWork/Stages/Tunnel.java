package Lessons.Lesson_5.HomeWork.Stages;

import Lessons.Lesson_5.HomeWork.Cars.Car;
import Lessons.Lesson_5.HomeWork.HomeWorkMain;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static final Semaphore semaphore = new Semaphore(HomeWorkMain.CARS_COUNT/2);

    public Tunnel() {
        this.length = 80;
        this.description = String.format("Тоннель %s метров", length);
    }

    @Override
    public void go(Car car) {
        //System.out.println( semaphore.availablePermits() > 0 ? "Туннель заполнен" : "Еще осталось мест"+semaphore.availablePermits() );
        try {
            try {
                System.out.println(String.format("%s готовится к этапу(ждет) %s", car.getName(), description));
                semaphore.acquire();
                System.out.println(String.format("%s начал этап %s", car.getName(), description));
                Thread.sleep(length / car.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(1);
                System.out.println(String.format("%s закончил этап: %s", car.getName(), description));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
