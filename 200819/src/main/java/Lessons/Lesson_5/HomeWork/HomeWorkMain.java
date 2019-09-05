package Lessons.Lesson_5.HomeWork;

import Lessons.Lesson_5.HomeWork.Cars.Car;
import Lessons.Lesson_5.HomeWork.Stages.Road;
import Lessons.Lesson_5.HomeWork.Stages.Tunnel;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
Организуем гонки:
Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого их них уходит разное время.
В тоннель не может заехать одновременно больше половины участников (условность).
Попробуйте все это синхронизировать.
Только после того, как все завершат гонку, нужно выдать объявление об окончании.
Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent.
 */
public class HomeWorkMain {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch START = new CountDownLatch(CARS_COUNT);
    public static final CyclicBarrier FINISH = new CyclicBarrier(CARS_COUNT, () ->  System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!"));
    public static AtomicInteger racePlace =  new AtomicInteger(0);


    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            START.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

