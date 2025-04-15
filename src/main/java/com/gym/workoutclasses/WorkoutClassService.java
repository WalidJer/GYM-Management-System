package com.gym.workoutclasses;

import java.util.List;

/**
 * Service class for handling business logic related to workout classes.
 * Used by Trainers and Members to manage and view classes.
 */

public class WorkoutClassService {

    private WorkoutClassDAO workoutClassDAO;

    /**
     * Constructor to initialize the DAO.
     */

    public WorkoutClassService() {
        this.workoutClassDAO = new WorkoutClassDAO();
    }

    /**
     * Allows a trainer to create a new workout class.
     *
     * @param workoutClass the class to be added
     */


    public void createWorkoutClass(WorkoutClass workoutClass) {
        if (workoutClass == null) {
            System.out.println("Workout class is null.");
            return;
        }

        workoutClassDAO.addWorkoutClass(workoutClass);
    }


    /**
     * Retrieves and displays all workout classes created by the given trainer.
     *
     * @param trainerID the trainer's user ID
     */


    public void viewMyWorkoutClasses(int trainerID) {
        List<WorkoutClass> myClasses = workoutClassDAO.getWorkoutClassesByTrainer(trainerID);

        if (myClasses.isEmpty()) {
            System.out.println("You have no workout classes.");
        } else {
            System.out.println("\n╔══════╦══════════════════════╦══════════════════════════════════════════╦════════════╗");
            System.out.printf("║ %-4s ║ %-20s ║ %-40s ║ %-10s ║%n", "ID", "Type", "Description", "Trainer ID");
            System.out.println("╠══════╬══════════════════════╬══════════════════════════════════════════╬════════════╣");
    
            for (WorkoutClass wc : myClasses) {
                System.out.printf("║ %-4d ║ %-20s ║ %-40s ║ %-10d ║%n",
                        wc.getWorkoutID(),
                        wc.getType(),
                        wc.getDescription(),
                        wc.getTrainerID());
            }
    
            System.out.println("╚══════╩══════════════════════╩══════════════════════════════════════════╩════════════╝");
        }
    }

    /**
     * Displays all workout classes in the system (available for members).
     */


    public void viewAllWorkoutClasses() {
        List<WorkoutClass> allClasses = workoutClassDAO.getAllWorkoutClasses();

        if (allClasses.isEmpty()) {
            System.out.println("No workout classes found.");
        } else {
            System.out.println("\n╔══════╦══════════════════════╦══════════════════════════════════════════╦════════════╗");
            System.out.printf("║ %-4s ║ %-20s ║ %-40s ║ %-10s ║%n", "ID", "Type", "Description", "Trainer ID");
            System.out.println("╠══════╬══════════════════════╬══════════════════════════════════════════╬════════════╣");
    
            for (WorkoutClass wc : allClasses) {
                System.out.printf("║ %-4d ║ %-20s ║ %-40s ║ %-10d ║%n",
                        wc.getWorkoutID(),
                        wc.getType(),
                        wc.getDescription(),
                        wc.getTrainerID());
            }
    
            System.out.println("╚══════╩══════════════════════╩══════════════════════════════════════════╩════════════╝");
        }
    }



    /**
     * Allows a trainer to update a class they created.
     *
     * @param updatedClass the updated class details
     */


    public void updateWorkoutClass(WorkoutClass updatedClass) {
        if (updatedClass == null) {
            System.out.println("Invalid class update request.");
            return;
        }

        workoutClassDAO.updateWorkoutClass(updatedClass);
    }



    /**
     * Allows a trainer to delete a class they created.
     *
     * @param workoutID the ID of the class to delete
     * @param trainerID the trainer's user ID (for authorization)
     */

     
    public void deleteWorkoutClass(int workoutID, int trainerID) {
        workoutClassDAO.deleteWorkoutClass(workoutID, trainerID);
    }

    /**
     * Calls the DAO to delete all workout classes associated with a given trainer.
     *
     * @param trainerId the ID of the trainer whose classes are to be removed
     * @return {@code true} if the deletion was successful or no classes existed,
     *         {@code false} if an error occurred during deletion
     */


    public boolean deleteWorkoutClassesByTrainer(int trainerId) {
        return workoutClassDAO.deleteWorkoutClassesByTrainer(trainerId);
    }
    

    
}
