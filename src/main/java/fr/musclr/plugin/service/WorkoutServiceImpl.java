package fr.musclr.plugin.service;

import fr.musclr.plugin.controller.WorkoutGenerationFormModel;
import fr.musclr.plugin.entity.Workout;
import fr.musclr.plugin.entity.WorkoutGroup;
import fr.musclr.plugin.entity.WorkoutLevel;
import fr.musclr.plugin.entity.WorkoutType;
import fr.musclr.plugin.service.dao.WorkoutDao;
import fr.musclr.plugin.service.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private static final int TOTAL_WORKOUTS_NUM = 6;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutDao workoutDao;

    @PostConstruct
    public void initDatabase() {
        workoutDao.dropCollectionIfExist();
        Workout[] workouts = new Workout[]{
                //Upper body - chest
                new Workout("Kneeling Push-up", WorkoutType.UPPER, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/wc-W05Gi9hU", "A scaled version of the normal push-up that works primarily the chest and triceps."),
                new Workout("Regular Push-up", WorkoutType.UPPER, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/v9LABVJzv8A", "A classic calisthenic exercise that works the chest, triceps, shoulders, and core by raising and lowering the body towards the ground."),
                new Workout("Diamond Push-up", WorkoutType.UPPER, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/SwoNNo4W1OU", "A more challenging version of the push-up that works the tricps more than the regular version."),
                new Workout("Bench Press", WorkoutType.UPPER, WorkoutLevel.BEGINNER, true, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/wzq57DB5Ppg", "Increases upper-body pushing strength, explosiveness, and muscle growth. Can be done with a barbell or dumbbells."),
                new Workout("Standing Flys", WorkoutType.UPPER, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/iA2UnDfRMOw", "Focus on isolating the chest muscles by bringing weights in and squeezing the chest. Can be done laying on a bench if available."),
                new Workout("Weighted Push-up", WorkoutType.UPPER, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/22I56NRZ3cc", "Make push-ups even more challenging by adding weight to your back. This can be done with a plate, backpack, or weighted vest."),
                new Workout("Close Grip Bench Press", WorkoutType.UPPER, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/J2tcpxNCuPY", "Bring your hands in to establish a narrow grip, and push up like a normal bench press. This will work your arms more than the normal version."),
                new Workout("Single Leg Pushup", WorkoutType.UPPER, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/sEP82yp3vjo", "Normal pushup position, except on alternating pushups, keep one leg raised in the air."),
                new Workout("Incline Bench Press", WorkoutType.UPPER, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/oe7N2GUzHIw", "Lay on a bench angled at 30-45 deg. Push up dumbbells or barbell using your chest muscles."),
                new Workout("Decline Bench Press", WorkoutType.UPPER, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/Kxt_5D2Rpgg", "From a decline position, push the dumbbells or a barbell towards the ceiling using your chest muscles and squeezing at the top."),
                new Workout("Alternating Shuffle Pushup", WorkoutType.UPPER, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.CHEST, "https://www.youtube.com/embed/mbka9fCaS1U", "Do a standard push up, then at the top position, release your left hand, and plant it next to your right hand. Release your right hand and readjust to the right so your hands are shoulder width apart again."),

                // Upper body - shoulder/arms
                new Workout("Shoulder Taps", WorkoutType.UPPER, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/gWHQpMUd7vw", "Works the deltoids, triceps, and core by forcing you to stabilize your body and touch your hand to the opposite shoulder."),
                new Workout("Supermans", WorkoutType.UPPER, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/67rgxYNKbZY", "Simultaneously lift your arms, legs, and chest, and contract at the top of the movement. This exercise can be a good compliment to core movements."),
                new Workout("Dips", WorkoutType.UPPER, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/vhXqTx7JYSs", "Dips work your shoulders, arms, and chest, and can be done with a dip bar, bench, or chair."),
                new Workout("Handstand Push-up", WorkoutType.UPPER, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/hvoQiF0kBI8", "A very difficult gymnastics and strength move. Descend until the head touches the ground, brace the core, and push with the shoulders and arms."),
                new Workout("Curls", WorkoutType.UPPER, WorkoutLevel.BEGINNER, true, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/av7-8igSXTs", "Increase arm strength by doing curls with a barbell or dumbbells, bending at the elbow and concentrating on not swinging your body."),
                new Workout("Shoulder Press", WorkoutType.UPPER, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/xe19t2_6yis", "Can be performed either seated or standing, and can be done with a barbell or dumbbells. A great test over overhead pressing strength."),
                new Workout("Pull-ups", WorkoutType.UPPER, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/aAggnpPyR6E", "A compound upper-body exercise with palms facing forward on the bar."),
                new Workout("Triceps Push Down", WorkoutType.UPPER, WorkoutLevel.BEGINNER, true, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/zYdzpuxpQqg", "Start with your torso straight, and bring the upper arms close to your body and perpendicular to the floor. Using the triceps, bring the rope down or bar down towards your sides."),
                new Workout("Incline Dumbell Curl", WorkoutType.UPPER, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/34gKm21dfuE", "Rest your arms on a bench angled at 45 degrees, then curl the dumbbells up, bending the elbows and bringing both weights to your shoulders."),
                new Workout("Cross Body Hammer Curl", WorkoutType.UPPER, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/BBKddrjlxWw", "Keep your palms facing in and without twisting your arm, and curl the dumbbell of the right arm up towards your left shoulder. Repeat with each arm."),
                new Workout("Overhead Press", WorkoutType.UPPER, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/YPVFUjApHjM", "Press arms upward until fully extended.  Be sure to engage the shoulders and triceps muscles."),
                new Workout("Single Arm Resisted Curl", WorkoutType.UPPER, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/PwBOPb13BfE", "Place your left hand over your right fist, your left arm will provide the resistance for your right bicep. To perform a rep, simply try to bring your right fist up towards your right shoulder while you push down with your left arm. "),
                new Workout("Feet Elevated Pike Pushup", WorkoutType.UPPER, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/pel8eS42Lxc", "Get into pushup position and rest your feet on a bench or box. Bend your hips, raising your butt toward the ceiling so that your torso is vertical. Lower your body to the floor."),
                new Workout("Crab Walk", WorkoutType.UPPER, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.SHOULDER_ARMS, "https://www.youtube.com/embed/luOfiPHbQyc", "Sit on the floor and plant your hands under your shoulders. Place your feet flat and bend your knees. Extend your hips so they rise an inch or so above the floor—this is crab position. Next, walk forward on your hands and feet"),

                //Lower body - glutes
                new Workout("Glute Bridge", WorkoutType.LOWER, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/N48d7sm8dbU", "Strengthens the glutes, and increases glute activation with no equipment necessary."),
                new Workout("Donkey Kickbacks", WorkoutType.LOWER, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/SJ1Xuz9D-ZQ", "Targets each glute, and forced you to stabilize your core through the movement."),
                new Workout("Calf Raises", WorkoutType.LOWER, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/UV8gOrHmuKc", "This builds muscle and tones the calves by lifting as high as you can onto your toes."),
                new Workout("Air Squats", WorkoutType.LOWER, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/C_VtOYc6j5c", "A classic athletic exercise that works most of the lower body, and is the foundation for any workout program."),
                new Workout("Single-leg Deadlifts", WorkoutType.LOWER, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/HtHxnWmMgzM", "A great move that develops balance, as well as hamstring and glute strength."),
                new Workout("Goblet Squats", WorkoutType.LOWER, WorkoutLevel.BEGINNER, true, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/lyvMNn9e2d4", "Another  iation of the squat that works most of the lower body, and can be done with a  iety of different styles of weights."),
                new Workout("Suitcase Deadlifts", WorkoutType.LOWER, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/0WPVOzTwCi4", "A great move that works the hamstrings and glutes, while forcing you to stabilize the core."),
                new Workout("Hamstring Drop", WorkoutType.LOWER, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/pncd_eGzQ7U", "A glute and hamstring workout that also tests your core strength."),
                new Workout("Elbow Plank Leg Raises", WorkoutType.LOWER, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/Akf3IP0H9fA", "A glute and leg workout that has the benefit of giving your core a good push as well."),
                new Workout("Squat Hold Calf Raises", WorkoutType.LOWER, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/wYoE11arXIw", "Full leg workout that specifically targets your calves while still giving you a complete leg workout."),
                new Workout("Side Kick with Bent Knee", WorkoutType.LOWER, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/9hKsATwrmzY", "A glute workout that also targets your hip flexors and core. Make sure to concentrate on your form."),
                new Workout("Good Mornings", WorkoutType.LOWER, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/XzD1_jz0si4", "This workout targets your hamstrings, glutes, and lower back."),
                new Workout("Split Squat", WorkoutType.LOWER, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.GLUTES, "https://www.youtube.com/embed/6wIId6pQzHw", "A tough single-leg move that will add size to your legs and improve balance."),

                //Lower body - quad
                new Workout("Lunge", WorkoutType.LOWER, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.QUADS, "https://www.youtube.com/embed/UpyDdQjBTa0", "Single-leg exercise that works the quadriceps, but also works the glutes, hamstrings, and core muscles."),
                new Workout("Box Jump", WorkoutType.LOWER, WorkoutLevel.INTERMEDIATE, false, true, WorkoutGroup.QUADS, "https://www.youtube.com/embed/52r_Ul5k03g", "Great for improving athleticism and muscle that can become a challenging cardiovascular workout."),
                new Workout("Jumping Lunges", WorkoutType.LOWER, WorkoutLevel.INTERMEDIATE, false, true, WorkoutGroup.QUADS, "https://www.youtube.com/embed/Kq5lZ4o26Ho", "Bring lunges to the next level by explosively jumping when transtioning between legs."),
                new Workout("Pistol Squat", WorkoutType.LOWER, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.QUADS, "https://www.youtube.com/embed/qDcniqddTeE", "This advanced move requires flexibility, strength, balance, and coordination. This move can help build tremendous lower body strength."),
                new Workout("Weighted Lunge", WorkoutType.LOWER, WorkoutLevel.BEGINNER, true, false, WorkoutGroup.QUADS, "https://www.youtube.com/embed/UpyDdQjBTa0", "Single-leg exercise that works the quadriceps, but also works the glutes, hamstrings, and core muscles. Use dumbells, kettlebells, or whatever is available to you to add weight."),
                new Workout("Weighted Side Lunges", WorkoutType.LOWER, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.QUADS, "https://www.youtube.com/embed/qDcniqddTeE", "Works the quads, glutes, adductors, and other smaller stabilizer muscles. Focus on collapsing at the hip and sitting into the lunge."),
                new Workout("Weighted Jumping Lunge", WorkoutType.LOWER, WorkoutLevel.ADVANCED, true, true, WorkoutGroup.QUADS, "https://www.youtube.com/embed/EmCJp5fLec8", "Explode out of each lunge by jumping and switching legs, keeping the back straight, and controlling the descent of your rear knee to the ground."),
                new Workout("Straight Leg Bounds", WorkoutType.LOWER, WorkoutLevel.BEGINNER, false, true, WorkoutGroup.QUADS, "https://www.youtube.com/embed/NYH6L2doDm4", "Leg exercise that works the quadriceps and has the benefit of being a cardio workout."),
                new Workout("Jump Squats", WorkoutType.LOWER, WorkoutLevel.INTERMEDIATE, false, true, WorkoutGroup.QUADS, "https://www.youtube.com/embed/bv7as8mDXLQ", "Full leg exercise that mainly targets the quadriceps and gives you a good cardiovascular workout as well."),
                new Workout("Reverse Plank Kicks", WorkoutType.LOWER, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.QUADS, "https://www.youtube.com/embed/3eIPmCV_1HM", "A full body exercise that targets your quadriceps and hip flexors."),
                new Workout("Single Leg Squats", WorkoutType.LOWER, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.QUADS, "https://www.youtube.com/embed/plHQfHhzW0M", "Single-leg exercise that targets all leg muscles, but specifically your quadriceps."),

                //Core
                new Workout("Crunches", WorkoutType.CORE, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/HiRsmHH7psA", "A beginner exercise that helps strengthen the core."),
                new Workout("Leg Raises", WorkoutType.CORE, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/_OQaO65Vdzs", "This exercise targets the abdominals and hip flexors, and will help to sculpt the core."),
                new Workout("Plank", WorkoutType.CORE, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/pSHjTRCQxIw", "This static exercise works just about every muscle in the body, and helps to sculpt the core. Try 30 seconds if you're just starting out, or 1 minute if you're more experienced."),
                new Workout("V-ups", WorkoutType.CORE, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/-JIwvMSk4vo", "Keep your legs straight and bring your upper body off of the floor to try and touch your toes. Great for targeting your middle and upper abs."),
                new Workout("Russian Twist", WorkoutType.CORE, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/l2XsG9W5rYo", "This exercise strengthens the obliques, and can be performed with or without weight."),
                new Workout("3 point Plank", WorkoutType.CORE, WorkoutLevel.INTERMEDIATE, false, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/1jZDOKr9NjE", "Start in a plank position with your body in a straight line. Lift your left leg up off the ground so that you are balancing on your right leg and both forearms. Hold for the desired amount of time and then switch legs so that your left leg is on the floor and your right is up in the air."),
                new Workout("Dead Bug", WorkoutType.CORE, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/mKUvoquc54Y", "Lie on your back and bend your hips and knees to a 90-degree angle. Extend your left leg toward the floor and bring your right arm overhead. Keep your abs tight and don't let your lower back arch. Return your arm and leg to the starting position."),
                new Workout("Hanging Leg Raise", WorkoutType.CORE, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/uaxEVt2z4CE", "With your legs completely straight the entire time, tighten your core and use your abs to raise your feet toward your shoulders. Pause when your thighs reach your chest."),
                new Workout("Cable Crunch", WorkoutType.CORE, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/W-frrCtNmic", "Keeping your weight on your knees and shins, let the cable pull on your arms and torso so that you feel a light stretch in your abdominal muscles. From there, crunch your body, bringing your forearms down to your knees and your head to the floor. Slowly return to the starting position. "),
                new Workout("Seated Barbell Twist", WorkoutType.CORE, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/pXmaiD4NIdg", "Place weight on your shoulders, keeping your back straight and upright. Twist your torso to the left, pause for a moment and then move back to the center."),
                new Workout("Decline Crunch", WorkoutType.CORE, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/g6Q81FYhRf0", "Place your hands on either side of your head, without locking your fingers. Raise your body slowly while you contract your abs. Use a true decline bench, or something like a bench if you're at home."),
                new Workout("Dumbbell Side Bend", WorkoutType.CORE, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.CORE, "https://www.youtube.com/embed/als3uIJaa8Q", "Keeping your back straight and your eyes facing forwards, bend down to the right as far as you can, then back up again. Without pausing at the top, bend down to the left."),

                //Total body
                new Workout("Jumping Jack", WorkoutType.TOTAL, WorkoutLevel.BEGINNER, false, true, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/gG2Z1siSvkk", "A classic performed by jumping from a position with legs together, and arms at the sides, to a position with legs apart and arms over head."),
                new Workout("Bear Crawl", WorkoutType.TOTAL, WorkoutLevel.BEGINNER, false, true, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/bfT5TaRFKQw", "Increase your heart rate and stabilize your core by doing this fun exercise."),
                new Workout("Mountain Climber", WorkoutType.TOTAL, WorkoutLevel.INTERMEDIATE, false, true, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/w2iTOneGPdU", "A great total body exercise that heavily utilizes the core, and can be a difficult cardiovascular exercise."),
                new Workout("Spiderman Push-up", WorkoutType.TOTAL, WorkoutLevel.ADVANCED, false, false, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/hWVf7gt5dQ8", "An advanced version of the push-up that will work the chest, triceps, shoulders, core, and hip flexors. Go slow and focus on the form."),
                new Workout("Burpee", WorkoutType.TOTAL, WorkoutLevel.ADVANCED, false, true, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/E-Oc0zjeqWo?list PLQSMS0J6JbrKdSOSbyJXaQ_zN_HSSp7zZ", "A great total body exercise that is performed in four steps, and can be a challenging cardiovascular exercise."),
                new Workout("KettleBell Swings", WorkoutType.TOTAL, WorkoutLevel.BEGINNER, true, true, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/OPcG_thX6Dc", "Develops power in your hamstrings, glutes, core, and will get your heart rate up in no time. Focus on the hip hinge and being explosive."),
                new Workout("Thrusters", WorkoutType.TOTAL, WorkoutLevel.INTERMEDIATE, true, true, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/M5gEwLTtWbg", "Combines a front squat with an overhead press. A great compound exercise that works the whole body."),
                new Workout("Single-arm Snatch", WorkoutType.TOTAL, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/R0mhHuVrLHA", "An advanced exercise that requires one fluid movement to take a dumbbell, kettlebell, or barbell from the ground to overhead."),
                new Workout("Halo", WorkoutType.TOTAL, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/Zy6bgAxPeks", "While holding onto a kettlebell with both hands move it around your head."),
                new Workout("Spider Walk", WorkoutType.TOTAL, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/TVQj8cc9ePA", "Walk froward while in raised push up position."),
                new Workout("Lunge with overhead press", WorkoutType.TOTAL, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/E5_YxmOyjiE", "Hold weights at your sholders then go into a lunge pressing the weights up."),
                new Workout("Renegade Row", WorkoutType.TOTAL, WorkoutLevel.ADVANCED, true, false, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/PJpTBj4ilZw", "While in raised push up position lift weights."),
                new Workout("Figure Eight", WorkoutType.TOTAL, WorkoutLevel.INTERMEDIATE, true, false, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/xpHWMC2_e3c", "Move kettlebell between legs in figure eight alternating the hand that holds it."),
                new Workout("Back bow crossover", WorkoutType.TOTAL, WorkoutLevel.BEGINNER, false, false, WorkoutGroup.TOTAL_BODY, "https://www.youtube.com/embed/JBJmsE6xGsA", "While facing the floor, raise legs and arms moving both left and right touching down at the end of each motion.")

        };
        workoutDao.insertAll(workouts);
    }

    @Override
    public List<Workout> randomizedWorkout() {
        List<Workout> allWorkouts = workoutRepository.findAll();
        List<Workout> randomWorkouts = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < TOTAL_WORKOUTS_NUM; i++) {
            Random rand = new Random();
            int n = rand.nextInt(allWorkouts.size());
            if (!temp.contains(n)) {
                randomWorkouts.add(allWorkouts.get(n));
                temp.add(n);
            } else i--;
        }
        return randomWorkouts;
    }

    @Override
    public List<Workout> generateWorkouts(WorkoutGenerationFormModel formModel) {
        List<Workout> result = workoutRepository.findAllByLevelAndTypeAndEquipment(
                formModel.getLevel(), formModel.getType(), formModel.isEquipment());

        return result;
    }
}
