package fr.musclr.plugin.controller;

import fr.musclr.plugin.entity.WorkoutGroup;
import fr.musclr.plugin.entity.WorkoutLevel;
import fr.musclr.plugin.entity.WorkoutType;

public class WorkoutGenerationFormModel {

    private WorkoutLevel level;
    private int duration;
    private WorkoutType type;
    private boolean equipment;
    private boolean cardio;


    public WorkoutLevel getLevel() {
        return level;
    }

    public void setLevel(WorkoutLevel level) {
        this.level = level;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public WorkoutType getType() {
        return type;
    }

    public void setType(WorkoutType type) {
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
