package fr.musclr.plugin.service.workout;

import fr.musclr.plugin.controller.WorkoutFormModel;
import fr.musclr.plugin.entity.exercise.ExerciseLevel;
import fr.musclr.plugin.entity.exercise.ExerciseType;
import fr.musclr.plugin.entity.workout.Workout;

public interface WorkoutService {

    Workout randomizedWorkout();

    Workout generateWorkout(ExerciseLevel level, int duration, ExerciseType type, boolean equipment, boolean cardio, int pause);

}
