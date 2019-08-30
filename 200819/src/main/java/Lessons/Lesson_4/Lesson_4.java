package Lessons.Lesson_4;
/*
1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
 */
public class Lesson_4 {
    private static final Object monitor = new Object();
    private static volatile char currentLetterToPrint = 'A';

    public static void main(String[] args) {
        Thread t1 = new Thread(Lesson_4::printA);
        Thread t2 = new Thread(Lesson_4::printB);
        Thread t3 = new Thread(Lesson_4::printC);

        t1.start();
        t2.start();
        t3.start();

    }

    private static void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetterToPrint != 'A') {
                        monitor.wait();
                    }
                    System.out.print(currentLetterToPrint);
                    currentLetterToPrint = 'B';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetterToPrint != 'B') {
                        monitor.wait();
                    }
                    System.out.print(currentLetterToPrint);
                    currentLetterToPrint = 'C';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printC() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetterToPrint != 'C') {
                        monitor.wait();
                    }
                    System.out.print(currentLetterToPrint);
                    currentLetterToPrint = 'A';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
