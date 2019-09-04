package Lessons.Lesson_5.HomeWork;

import Lessons.Lesson_5.HomeWork.Cars.Car;
import Lessons.Lesson_5.HomeWork.Stages.Road;
import Lessons.Lesson_5.HomeWork.Stages.Tunnel;

import java.util.concurrent.*;

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
    private static CountDownLatch start;
    private static CyclicBarrier cb;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        start = new CountDownLatch(CARS_COUNT);
        cb = new CyclicBarrier(CARS_COUNT+1, () -> System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"));
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), start, cb);
        }
        for (Car car : cars) {
            new Thread(car).start();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

