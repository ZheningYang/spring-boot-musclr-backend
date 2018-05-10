package fr.musclr.plugin.service.internal.dao;

import fr.musclr.plugin.entity.exercise.Exercise;
import fr.musclr.plugin.entity.workout.Workout;

import java.util.List;

public interface WorkoutDao {

    void dropCollectionIfExist();

    void insertAll(List<Workout> workoutList);

}
