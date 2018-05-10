package fr.musclr.plugin.entity.workout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum WorkoutType {
	FITNESS,
	BULKING,
	CUTTING;
	
	private static final List<WorkoutType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static WorkoutType randomWorkoutType()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
