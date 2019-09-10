package Lessons.Lesson_7.WebinarCodeSamples;

import java.io.Serializable;
import java.util.UUID;

@Deprecated
public abstract class Account implements Serializable {

    protected String id;
    public double value;

    public Account() {
        id = UUID.randomUUID().toString();
        value = 0;
    }

    public Account(String id) {
        this.id = id;
        value = 0;
    }
}
