package fr.musclr.plugin.entity.workout;

import fr.musclr.plugin.entity.exercise.Exercise;

public class Routine {

    private Exercise exercise;

    private Integer repetition;

    public Routine(Exercise exercise, Integer repetition) {
        this.exercise = exercise;
        this.repetition = repetition;
    }

    @Override
    public String toString() {
        return "Routine{" +
                "exercise=" + exercise +
                ", repetition=" + repetition +
                '}';
    }

    public Integer getRepetition() {
        return repetition;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
