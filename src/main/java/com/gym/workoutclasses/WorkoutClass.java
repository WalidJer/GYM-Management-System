package com.gym.workoutclasses;

/**
 * Represents a workout class created by a trainer.
 * Each class includes a type (e.g., Yoga, Cardio), description, 
 * and the trainer responsible for conducting it.
 */

public class WorkoutClass {

    //==========================Attributes=========================
    private int workoutID;
    private String type;
    private String description;
    private int trainerID;   // Foreign Key referencing users.user_id (Trainer)

    //==========================Constructors========================

    /**
     * Default constructor. Initializes all fields to default values.
     */


    public WorkoutClass() {

        this.workoutID = 0;
        this.type = null;
        this.description = null;
        this.trainerID = 0;

    }

    /**
     * Constructor used when adding a new workout class (no ID needed).
     *
     * @param type        Type of workout (e.g., Cardio, Strength)
     * @param description Description of the workout class
     * @param trainerID   ID of the trainer who created the class
     */

    public WorkoutClass(String type, String description, int trainerID) {
        this.type = type;
        this.description = description;
        this.trainerID = trainerID;
    }

    /**
     * Constructor used when retrieving a full class from the database.
     *
     * @param workoutID   ID of the workout class
     * @param type        Type of workout
     * @param description Description of the class
     * @param trainerID   Trainer's user ID
     */

    public WorkoutClass(int workoutID, String type, String description, int trainerID) {
        this.workoutID = workoutID;
        this.type = type;
        this.description = description;
        this.trainerID = trainerID;
    }

     //========================Getters and Setters===================
    public int getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(int workoutID) {
        this.workoutID = workoutID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }


    //==========================toString=========================

    @Override
    public String toString() {
        return "WorkoutClass{" +
                "workoutID=" + workoutID +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", trainerID=" + trainerID +
                '}';
    }
}
