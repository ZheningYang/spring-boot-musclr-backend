package fr.musclr.plugin.entity.workout;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "workouts")
public class Workout {
    @Id
    private String id;

    private List<Routine> routines;

    private Integer pause;

    public Workout(List<Routine> routines, Integer pause) {
        this.routines = routines;
        this.pause = pause;
    }

    @Override
    public String toString() {
        return "workout{" +
                "id='" + id + '\'' +
                ", routines=" + routines +
                ", pause=" + pause +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }

    public Integer getPause() {
        return pause;
    }

    public void setPause(Integer pause) {
        this.pause = pause;
    }

}
