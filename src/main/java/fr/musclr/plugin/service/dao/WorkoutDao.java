package fr.musclr.plugin.service.dao;

import fr.musclr.plugin.entity.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkoutDao {
    void dropCollectionIfExist();

    void insertAll(Workout[] workouts);

}
