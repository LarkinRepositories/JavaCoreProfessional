package Lessons.Lesson_5.HomeWork.Cars;

import Lessons.Lesson_5.HomeWork.HomeWorkMain;
import Lessons.Lesson_5.HomeWork.Race;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            HomeWorkMain.START.countDown();
            HomeWorkMain.START.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        race.getStages().forEach(stage -> stage.go(this));
        synchronized (Race.MONITOR) {
            int racePlace = HomeWorkMain.racePlace.incrementAndGet();
            System.out.printf(racePlace == 1 ? "%s WIN!\n" : "%s занял %s место\n", this.name, racePlace);
        }
        try {
            HomeWorkMain.FINISH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

