package fr.musclr.plugin.service.dao;

import fr.musclr.plugin.entity.Exercise;

public interface ExerciseDao {
    void dropCollectionIfExist();

    void insertAll(Exercise[] workouts);

}
