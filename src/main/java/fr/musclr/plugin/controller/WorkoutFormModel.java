package fr.musclr.plugin.controller;

import fr.musclr.plugin.entity.exercise.ExerciseLevel;
import fr.musclr.plugin.entity.exercise.ExerciseType;
import fr.musclr.plugin.entity.user.User;
import fr.musclr.plugin.entity.workout.WorkoutType;

public class WorkoutFormModel {

    private User creator;
    private String name;
    private WorkoutType workoutType;
    private ExerciseLevel level;
    private int duration;
    private ExerciseType type;
    private boolean equipment;

    public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExerciseLevel getLevel() {
        return level;
    }

    public void setLevel(ExerciseLevel level) {
        this.level = level;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }

    public boolean isEquipment() {
        return equipment;
    }

    public void setEquipment(boolean equipment) {
        this.equipment = equipment;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
    }
}
