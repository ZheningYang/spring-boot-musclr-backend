package fr.musclr.plugin.service;

import fr.musclr.plugin.controller.WorkoutGenerationFormModel;
import fr.musclr.plugin.entity.Exercise;

import java.util.List;

public interface ExerciseService {

    List<Exercise> randomizedWorkout();

    List<Exercise> generateWorkouts(WorkoutGenerationFormModel formModel);
}
