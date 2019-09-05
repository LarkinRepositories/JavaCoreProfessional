package Lessons.Lesson_5.HomeWork;

import Lessons.Lesson_5.HomeWork.Stages.Stage;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Race {
    private CopyOnWriteArrayList<Stage> stages;
    public static final Object MONITOR = new Object();


    public CopyOnWriteArrayList<Stage> getStages() {
        return stages;
    }


    public Race(Stage... stages) {
        this.stages = new CopyOnWriteArrayList<>(Arrays.asList(stages));
    }
}
