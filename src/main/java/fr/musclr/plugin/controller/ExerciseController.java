package fr.musclr.plugin.controller;

import fr.musclr.plugin.entity.exercise.Exercise;
import fr.musclr.plugin.entity.exercise.ExerciseGroup;
import fr.musclr.plugin.entity.workout.Workout;
import fr.musclr.plugin.service.exercise.ExerciseService;
import fr.musclr.plugin.service.workout.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercise/")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping(value = "/groups/{group}", method = RequestMethod.GET)
    public List<Exercise> getExercisesByGroup(@PathVariable("group") String group) {
        ExerciseGroup exerciseGroup = ExerciseGroup.valueOf(group.toUpperCase());
        return exerciseService.findAllByGroup(exerciseGroup);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public List<ExerciseGroup> getAllExerciseGroup() {
        return exerciseService.getAllExerciseGroupList();
    }
}
