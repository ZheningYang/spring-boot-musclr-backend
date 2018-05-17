package fr.musclr.plugin.service.workout;

import fr.musclr.plugin.entity.exercise.Exercise;
import fr.musclr.plugin.entity.exercise.ExerciseGroup;
import fr.musclr.plugin.entity.exercise.ExerciseLevel;
import fr.musclr.plugin.entity.exercise.ExerciseType;
import fr.musclr.plugin.entity.rating.Rating;
import fr.musclr.plugin.entity.user.User;
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
    
    private User[] users = new User[] {
    		new User("5af84f93bfc2682f887fb0ee","Julia", "Scarlett.jpg"),
    		new User("5af8532a08dfd90184cabd24","Roméo", "Chris.jpeg"),
    		new User("5af8642008dfd90184cabd25","Benedict","Benedict.jpg"),
    		new User("5af8643808dfd90184cabd26","Elizabeth", "Elizabeth.png"),
    		new User("5af8645a08dfd90184cabd27","Gwyneth", "Gwyneth.jpg"),
    		new User("5af8648c08dfd90184cabd28","Karen", "Karen.jpg"),
    		new User("5af864aa08dfd90184cabd29","Mark", "Mark.jpg"),
    		new User("5af864cb08dfd90184cabd2a","Robert", "Robert.jpeg"),
    		new User("5af864e408dfd90184cabd2b","Tom", "Tom.png"),
    		new User("5af864fe08dfd90184cabd2c","Zoe", "Zoe.jpg")
    };
    
    private String[] workoutsName = new String[] {
    		"Batman daily workout",
    		"SuperMan or Girl workout",
    		"Get as fast as Flash !",
    		"Wonder Who's that man",
    		"Swim like Aquaman",
    		"Become a Cyborg",
    		"Workout for bat'girls",
    		"Vibe your muscle",
    		"Hardcore workout",
    		"Power your lift",
    		"StrongLifts 5x5",
    		"1 Week Cutting Plan",
    		"Putting on lean muscle",
    		"Mens fitness",
    		"6 Days Bulking Split",
    		"Women's Body Toning",
    		"Be a beast",
    		"Hypertrophy week plan",
    		"Get Fat",
    		"Get skinny",
    		"Never get strong",
    		"Swiss no pain no gain",
    		"I don't have muscles",
    		"Armstrong workout"
    };

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutDao workoutDao;

//    @PostConstruct
//    public void initDatabase() {
//        workoutDao.dropCollectionIfExist();
//
//        for (int i = 0; i < 80; i++) {
//            randomizedWorkout();
//        }
//    }

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
        
        User user = users[random.nextInt(users.length)];
        
        List<Rating> ratings = generateRandomListRatings(random);
        
        Integer ratingMoy = getAverageRating(ratings);
        
        String workoutName = workoutsName[random.nextInt(workoutsName.length)];
        
        WorkoutType group = WorkoutType.randomWorkoutType();
        return workoutRepository.insert(new Workout(workoutName, randomRoutines, randomPause, group, ratings, ratingMoy, user, new Date()));
    }

    @Override
    public Workout generateWorkout(User creator, String name, ExerciseLevel level, int duration, ExerciseType type, boolean equipment, WorkoutType workoutType) {

        List<Exercise> filterExercises;

        if (type.equals(ExerciseType.TOTAL)) {
            filterExercises = exerciseService.findAllByLevelAndEquipment(level, equipment);
        } else {
            filterExercises = exerciseService.findAllByLevelAndTypeAndEquipment(level, type, equipment);
        }

        int repetition;
        int pause;
        switch (workoutType) {
            case BULKING:
                repetition = MAX_REPETITIONS / 3;
                pause = MAX_PAUSE;
                filterExercises = filterExercises.stream().filter(exercise -> !exercise.isCardio()).collect(Collectors.toList());
                break;

            case FITNESS:
                repetition = MAX_REPETITIONS;
                pause = MAX_PAUSE / 3;
                filterExercises = filterExercises.stream().filter(Exercise::isCardio).collect(Collectors.toList());
                break;

            case CUTTING:
            default:
                repetition = MAX_REPETITIONS / 3 * 2;
                pause = MAX_PAUSE / 2;
                break;
        }

        List<Routine> routines = new ArrayList<>();
        for (int i = 0; i < duration; i += 10) {
            routines.addAll(generateRoutines(filterExercises, type, repetition));
        }
        return workoutRepository.insert(new Workout(name, routines, pause, workoutType, null, null, creator, new Date()));

    }
    
    private List<Rating> generateRandomListRatings(Random random){
    	List<Rating> ratings = new ArrayList<>();
    	int randomRatingVotes = random.nextInt(10);
    	for(int i=0; i<randomRatingVotes; i++) {
    		User user = users[random.nextInt(users.length)];
    		Rating rating = new Rating(random.nextInt(5), null, user.getUsername(), null);
    		ratings.add(rating);
    	}
    	return ratings;
    }
    
    private Integer getAverageRating(List<Rating> ratings) {
    	int ratingMoy = 0;
    	if(ratings.size() != 0) {
    		for(Rating rating: ratings) {
        		ratingMoy += rating.getScore();
        	}
        	ratingMoy = ratingMoy/ratings.size();
        	if(ratingMoy < 5) {
        		ratingMoy += 1;
        	}
    	}
    	return ratingMoy;
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

    @Override
    public List<Workout> getAllByType(WorkoutType type) {
        return workoutRepository.findAllByType(type);
    }

    @Override
    public List<WorkoutType> getAllWorkoutTypeList() {
        return Arrays.asList(WorkoutType.values());
    }

}
