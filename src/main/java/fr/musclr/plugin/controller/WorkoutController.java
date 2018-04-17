package fr.musclr.plugin.controller;

import fr.musclr.plugin.entity.Exercise;
import fr.musclr.plugin.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkoutController {

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping("/random")
    public List<Exercise> randomizedWorkout() {
        return exerciseService.randomizedWorkout();
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public List<Exercise> generate(@RequestBody WorkoutGenerationFormModel formModel) {
        return exerciseService.generateWorkouts(formModel);
    }
}
