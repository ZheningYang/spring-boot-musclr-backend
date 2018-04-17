package fr.musclr.plugin.service.internal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class WorkoutDaoImpl implements WorkoutDao {

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
}
