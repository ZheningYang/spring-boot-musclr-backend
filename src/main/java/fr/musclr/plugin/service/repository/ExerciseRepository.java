package fr.musclr.plugin.service.repository;

import fr.musclr.plugin.entity.Exercise;
import fr.musclr.plugin.entity.ExerciseLevel;
import fr.musclr.plugin.entity.ExerciseType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExerciseRepository extends MongoRepository<Exercise, String> {

    List<Exercise> findAll();

    List<Exercise> findAllByLevelAndTypeAndEquipment(ExerciseLevel level,
                                                     ExerciseType type,
                                                     boolean equipment);


}
