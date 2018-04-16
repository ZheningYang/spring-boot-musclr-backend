package fr.musclr.plugin.controller;

import fr.musclr.plugin.entity.Workout;
import fr.musclr.plugin.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @RequestMapping("/random")
    public List<Workout> randomizedWorkout() {
        return workoutService.randomizedWorkout();
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public List<Workout> generate(@RequestBody WorkoutGenerationFormModel formModel) {
        return workoutService.generateWorkouts(formModel);
    }
}
