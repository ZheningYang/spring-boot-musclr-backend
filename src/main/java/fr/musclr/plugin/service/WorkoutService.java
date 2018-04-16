package fr.musclr.plugin.service;

import fr.musclr.plugin.controller.WorkoutGenerationFormModel;
import fr.musclr.plugin.entity.Workout;

import java.util.List;

public interface WorkoutService {

    List<Workout> randomizedWorkout();

    List<Workout> generateWorkouts(WorkoutGenerationFormModel formModel);
}
