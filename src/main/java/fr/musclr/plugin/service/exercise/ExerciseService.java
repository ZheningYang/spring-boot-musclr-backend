package fr.musclr.plugin.service.exercise;

import fr.musclr.plugin.entity.exercise.Exercise;
import fr.musclr.plugin.entity.exercise.ExerciseGroup;
import fr.musclr.plugin.entity.exercise.ExerciseLevel;
import fr.musclr.plugin.entity.exercise.ExerciseType;

import java.util.List;

public interface ExerciseService {
    List<Exercise> findAll();

    List<Exercise> findAllByGroup(ExerciseGroup group);

    List<Exercise> findAllByLevelAndEquipment(ExerciseLevel level, boolean equipment);

    List<Exercise> findAllByLevelAndTypeAndEquipment(ExerciseLevel level, ExerciseType type, boolean equipment);

    Exercise pickRandomExercise(List<Exercise> exercises, ExerciseGroup group);

    List<ExerciseGroup> getAllExerciseGroupList();

    List<ExerciseLevel> getAllExerciseLevelList();

    List<ExerciseType> getAllExerciseTypeList();

}
