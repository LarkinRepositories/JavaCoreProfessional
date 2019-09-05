package Lessons.Lesson_5;

import java.util.concurrent.Semaphore;

public class Lesson_5CodeSamples {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            final int w = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                    System.out.println("Поток" + w + " перед семафором");
                    semaphore.acquire();
                    System.out.println("Поток" + w + " получил доступ к ресурсу");
                    Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("Поток" + w + " освободил ресурс");
                        semaphore.release();
                    }
                }
            }).start();
        }
    }
}
