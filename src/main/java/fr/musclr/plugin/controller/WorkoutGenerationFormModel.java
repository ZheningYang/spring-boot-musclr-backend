package fr.musclr.plugin.controller;

import fr.musclr.plugin.entity.ExerciseLevel;
import fr.musclr.plugin.entity.ExerciseType;

public class WorkoutGenerationFormModel {

    private ExerciseLevel level;
    private int duration;
    private ExerciseType type;
    private boolean equipment;
    private boolean cardio;


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

    public boolean isCardio() {
        return cardio;
    }

    public void setCardio(boolean cardio) {
        this.cardio = cardio;
    }
}
