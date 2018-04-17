package fr.musclr.plugin.service.internal.repository;

import fr.musclr.plugin.entity.workout.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkoutRepository extends MongoRepository<Workout, String> {

    List<Workout> findAll();

}
