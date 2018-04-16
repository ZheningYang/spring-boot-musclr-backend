package fr.musclr.plugin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "workouts")
public class Workout {
    @Id
    private String id;
    private String name;
    private WorkoutType type;
    private WorkoutLevel level;
    private boolean equipment;
    private boolean cardio;
    private WorkoutGroup group;
    private String link;
    private String description;

    @Override
    public String toString() {
        return "Workout{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", equipment=" + equipment +
                ", cardio=" + cardio +
                ", group=" + group +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Workout(String name, WorkoutType type, WorkoutLevel level, boolean equipment, boolean cardio, WorkoutGroup group, String link, String description) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.equipment = equipment;
        this.cardio = cardio;
        this.group = group;
        this.link = link;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkoutType getType() {
        return type;
    }

    public void setType(WorkoutType type) {
        this.type = type;
    }

    public WorkoutLevel getLevel() {
        return level;
    }

    public void setLevel(WorkoutLevel level) {
        this.level = level;
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

    public WorkoutGroup getGroup() {
        return group;
    }

    public void setGroup(WorkoutGroup group) {
        this.group = group;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
