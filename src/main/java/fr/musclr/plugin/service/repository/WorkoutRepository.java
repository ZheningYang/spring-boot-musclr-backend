package fr.musclr.plugin.service.repository;

import fr.musclr.plugin.entity.Workout;
import fr.musclr.plugin.entity.WorkoutGroup;
import fr.musclr.plugin.entity.WorkoutLevel;
import fr.musclr.plugin.entity.WorkoutType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkoutRepository extends MongoRepository<Workout, String> {

    List<Workout> findAll();

    List<Workout> findAllByLevelAndTypeAndEquipment(WorkoutLevel level,
                                                             WorkoutType type,
                                                             boolean equipment);


}
