package fr.musclr.plugin.controller;

import fr.musclr.plugin.entity.workout.Workout;
import fr.musclr.plugin.entity.workout.WorkoutType;
import fr.musclr.plugin.service.workout.WorkoutService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout/")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @RequestMapping("/random")
    public Workout randomizedWorkout() {
        return workoutService.randomizedWorkout();
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public Workout generate(@RequestBody WorkoutFormModel formModel) {
        return workoutService.generateWorkout(formModel.getName(),
                formModel.getLevel(), formModel.getDuration(), formModel.getType(),
                formModel.isEquipment(), formModel.getWorkoutType());
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Workout> getAll() {
        return workoutService.getAll();
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public List<WorkoutType> getAllWorkoutTypes() {
        return workoutService.getAllWorkoutTypeList();
    }
}
