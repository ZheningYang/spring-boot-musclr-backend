package fr.musclr.plugin.service.internal.dao;

import fr.musclr.plugin.entity.exercise.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class ExerciseDaoImpl implements ExerciseDao {

    private static final String EXERCISE_COLLECTION = "exercises";

    @Autowired
    private MongoOperations mongoOps;

    @Override
    public void dropCollectionIfExist() {
        if (mongoOps.collectionExists(EXERCISE_COLLECTION)) {
            mongoOps.dropCollection(EXERCISE_COLLECTION);
            System.out.println("dropped collection");
        }
    }

    @Override
    public void insertAll(Exercise[] workouts) {
        mongoOps.insert(Arrays.asList(workouts), EXERCISE_COLLECTION);

    }
}
