package fr.musclr.plugin.service.internal.dao;

import fr.musclr.plugin.entity.exercise.Exercise;

public interface ExerciseDao {

    void dropCollectionIfExist();

    void insertAll(Exercise[] workouts);

}
