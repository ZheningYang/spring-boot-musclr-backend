package fr.musclr.plugin.entity.workout;

import fr.musclr.plugin.entity.rating.Rating;
import fr.musclr.plugin.entity.user.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "workouts")
public class Workout {
    @Id
    private String id;

    private String name;

    private List<Routine> routines;

    private Integer pause;
    
    private WorkoutType type;

    private List<Rating> ratings;
    
    private Integer ratingMoy;

    private User createdBy;

    private Date createdOn;

	public Workout(String name, List<Routine> routines, Integer pause, WorkoutType type, List<Rating> ratings,
		Integer ratingMoy, User createdBy, Date createdOn) {
	super();
	this.name = name;
	this.routines = routines;
	this.pause = pause;
	this.type = type;
	this.ratings = ratings;
	this.ratingMoy = ratingMoy;
	this.createdBy = createdBy;
	this.createdOn = createdOn;
}
	
	

	 @Override
	 public String toString() {
	return "Workout{" +
	"id='" + id + '\'' +
	", name='" + name + '\'' +
	", routines=" + routines +
	", pause=" + pause +
	", type=" + type +
	", ratings=" + ratings +
	", ratingMoy=" + ratingMoy +
	", createdBy='" + createdBy + '\'' +
	", createdOn=" + createdOn +
	'}';
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }

    public Integer getPause() {
        return pause;
    }

    public void setPause(Integer pause) {
        this.pause = pause;
    }

	public WorkoutType getType() {
		return type;
	}

	public void setType(WorkoutType type) {
		this.type = type;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

	public Integer getRatingMoy() {
		return ratingMoy;
	}

	public void setRatingMoy(Integer ratingMoy) {
		this.ratingMoy = ratingMoy;
	}  
}
