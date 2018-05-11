package fr.musclr.plugin.service.internal.repository;

import fr.musclr.plugin.entity.workout.Workout;
import fr.musclr.plugin.entity.workout.WorkoutType;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkoutRepository extends MongoRepository<Workout, String> {

    List<Workout> findAll();
    
    List<Workout> findAllByType(WorkoutType workoutType);

}
