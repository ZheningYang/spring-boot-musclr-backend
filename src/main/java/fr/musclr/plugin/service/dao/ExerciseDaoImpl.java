package fr.musclr.plugin.service.dao;

import fr.musclr.plugin.entity.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class ExerciseDaoImpl implements ExerciseDao {

    private static final String WORKOUT_COLLECTION = "workouts";


    @Autowired
    private MongoOperations mongoOps;

    @Override
    public void dropCollectionIfExist() {
        if (mongoOps.collectionExists(WORKOUT_COLLECTION)) {
            mongoOps.dropCollection(WORKOUT_COLLECTION);
            System.out.println("dropped collection");
        }
    }

    @Override
    public void insertAll(Exercise[] workouts) {
        mongoOps.insert(Arrays.asList(workouts), WORKOUT_COLLECTION);

    }
}
