package fr.musclr.plugin.service.internal.repository;

import fr.musclr.plugin.entity.exercise.Exercise;
import fr.musclr.plugin.entity.exercise.ExerciseLevel;
import fr.musclr.plugin.entity.exercise.ExerciseType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExerciseRepository extends MongoRepository<Exercise, String> {

    List<Exercise> findAll();

    List<Exercise> findAllByLevelAndEquipment(ExerciseLevel level,
                                              boolean equipment);

    List<Exercise> findAllByLevelAndTypeAndEquipment(ExerciseLevel level,
                                                     ExerciseType type,
                                                     boolean equipment);

}
