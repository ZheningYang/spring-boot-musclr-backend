package fr.musclr.plugin.service.workout;

import fr.musclr.plugin.entity.exercise.Exercise;
import fr.musclr.plugin.entity.exercise.ExerciseGroup;
import fr.musclr.plugin.entity.exercise.ExerciseLevel;
import fr.musclr.plugin.entity.exercise.ExerciseType;
import fr.musclr.plugin.entity.workout.Routine;
import fr.musclr.plugin.entity.workout.Workout;
import fr.musclr.plugin.entity.workout.WorkoutType;
import fr.musclr.plugin.service.exercise.ExerciseService;
import fr.musclr.plugin.service.internal.dao.WorkoutDao;
import fr.musclr.plugin.service.internal.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private static final int TOTAL_EXERCISES = 8;
    private static final int MAX_REPETITIONS = 15;
    private static final int MAX_PAUSE = 60;


    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutDao workoutDao;

    @PostConstruct
    public void initDatabase() {
        workoutDao.dropCollectionIfExist();
    }

    @Override
    public Workout randomizedWorkout() {

        List<Exercise> allExercises = exerciseService.findAll();
        List<Routine> randomRoutines = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < TOTAL_EXERCISES; i++) {
            // pick a random exercise in allExercises
            int randomIndex = random.nextInt(allExercises.size());
            if (!temp.contains(randomIndex)) {
                int randomRep = random.nextInt(MAX_REPETITIONS);
                randomRoutines.add(new Routine(allExercises.get(randomIndex), randomRep));
                temp.add(randomIndex);
            } else i--;
        }

        int randomPause = random.nextInt(MAX_PAUSE);
        WorkoutType group = WorkoutType.randomWorkoutType();
        return workoutRepository.insert(new Workout(randomRoutines, randomPause, group));
    }

    @Override
    public Workout generateWorkout(ExerciseLevel level, int duration, ExerciseType type, boolean equipment, boolean cardio, int pause, WorkoutType typeWorkout) {

        List<Exercise> filterExercises;

        if (type.equals(ExerciseType.TOTAL)) {
            filterExercises = exerciseService.findAllByLevelAndEquipment(level, equipment);
        } else {
            filterExercises = exerciseService.findAllByLevelAndTypeAndEquipment(level, type, equipment);
        }

        if (cardio) {
            filterExercises = filterExercises.stream().filter(Exercise::isCardio).collect(Collectors.toList());
        } else {
            filterExercises = filterExercises.stream().filter(exercise -> !exercise.isCardio()).collect(Collectors.toList());
        }

        int repetition;
        switch (level) {
            case BEGINNER:
                repetition = MAX_REPETITIONS / 3;
                break;
            case INTERMEDIATE:
            default:
                repetition = MAX_REPETITIONS / 3 * 2;
                break;
            case ADVANCED:
                repetition = MAX_REPETITIONS;
                break;
        }

        List<Routine> routines = new ArrayList<>();
        for (int i = 0; i < duration; i += 10) {
            routines.addAll(generateRoutines(filterExercises, type, repetition));
        }
        return workoutRepository.insert(new Workout(routines, pause, typeWorkout));

    }

    private List<Routine> generateRoutines(List<Exercise> exercises, ExerciseType type, int repetition) {
        List<Routine> routines = new ArrayList<>();
        switch (type) {
            case UPPER:
                routines = Arrays.asList(new Routine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.CHEST), repetition * 2),
                        new Routine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.SHOULDER_ARMS), repetition * 2),
                        new Routine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.CORE), repetition * 2));
                break;
            case LOWER:
                routines = Arrays.asList(new Routine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.GLUTES), repetition * 2),
                        new Routine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.QUADS), repetition * 2),
                        new Routine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.CORE), repetition * 2));
                break;
            case TOTAL:
                routines = Arrays.asList(
                        createRoutine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.CHEST), repetition),
                        createRoutine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.SHOULDER_ARMS), repetition),
                        createRoutine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.CORE), repetition),
                        createRoutine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.GLUTES), repetition),
                        createRoutine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.QUADS), repetition),
                        createRoutine(exerciseService.pickRandomExercise(exercises, ExerciseGroup.TOTAL_BODY), repetition)
                );
                break;
            default:
                break;
        }
        return routines.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private Routine createRoutine(Exercise exercise, int rep) {
        if (exercise != null) {
            return new Routine(exercise, rep);
        } else {
            return null;
        }
    }

	@Override
	public List<Workout> getAll() {
		return workoutRepository.findAll();
	}
}
