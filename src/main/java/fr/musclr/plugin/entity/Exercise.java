package fr.musclr.plugin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exercises")
public class Exercise {
    @Id
    private String id;
    private String name;
    private ExerciseType type;
    private ExerciseLevel level;
    private boolean equipment;
    private boolean cardio;
    private ExerciseGroup group;
    private String link;
    private String description;

    @Override
    public String toString() {
        return "Exercise{" +
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

    public Exercise(String name, ExerciseType type, ExerciseLevel level, boolean equipment, boolean cardio, ExerciseGroup group, String link, String description) {
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

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }

    public ExerciseLevel getLevel() {
        return level;
    }

    public void setLevel(ExerciseLevel level) {
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

    public ExerciseGroup getGroup() {
        return group;
    }

    public void setGroup(ExerciseGroup group) {
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
