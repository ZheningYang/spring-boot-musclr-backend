package fr.musclr.plugin.entity.rating;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ratings")
public class Rating {
    @Id
    private String id;

    private double score;

    private String comment;

    private String ratedBy;

    private String ratedFor;

    public Rating(double score, String comment, String ratedBy, String ratedFor) {
        this.score = score;
        this.comment = comment;
        this.ratedBy = ratedBy;
        this.ratedFor = ratedFor;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id='" + id + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", ratedBy='" + ratedBy + '\'' +
                ", ratedFor='" + ratedFor + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(String ratedBy) {
        this.ratedBy = ratedBy;
    }

    public String getRatedFor() {
        return ratedFor;
    }

    public void setRatedFor(String ratedFor) {
        this.ratedFor = ratedFor;
    }
}
