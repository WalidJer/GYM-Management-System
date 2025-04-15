package com.gym.workoutclasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.database.DBConnection;

/**
 * DAO (Data Access Object) for performing CRUD operations on workout_classes table.
 * Supports trainer-owned class creation, updates, deletions, and member viewing.
 */

public class WorkoutClassDAO {

    /**
     * Inserts a new workout class into the database.
     *
     * @param workoutClass the WorkoutClass object to insert.
     */


    public void addWorkoutClass(WorkoutClass workoutClass) {
        String query = "INSERT INTO workout_classes (workout_class_type, workout_class_description, trainer_id) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, workoutClass.getType());
            statement.setString(2, workoutClass.getDescription());
            statement.setInt(3, workoutClass.getTrainerID());

            int addedRows = statement.executeUpdate();
            System.out.println("Workout class added! Rows affected: " + addedRows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Retrieves all workout classes created by a specific trainer.
     *
     * @param trainerID the ID of the trainer.
     * @return list of WorkoutClass objects.
     */


    public List<WorkoutClass> getWorkoutClassesByTrainer(int trainerID) {
        List<WorkoutClass> list = new ArrayList<>();
        String query = "SELECT * FROM workout_classes WHERE trainer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setInt(1, trainerID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                list.add(new WorkoutClass(
                        rs.getInt("workout_id"),
                        rs.getString("workout_class_type"),
                        rs.getString("workout_class_description"),
                        rs.getInt("trainer_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    /**
     * Retrieves all workout classes in the system.
     *
     * @return list of WorkoutClass objects.
     */

    public List<WorkoutClass> getAllWorkoutClasses() {
        List<WorkoutClass> list = new ArrayList<>();
        String query = "SELECT * FROM workout_classes";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                list.add(new WorkoutClass(
                        rs.getInt("workout_id"),
                        rs.getString("workout_class_type"),
                        rs.getString("workout_class_description"),
                        rs.getInt("trainer_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    /**
     * Updates a workout class if it belongs to the trainer.
     *
     * @param workoutClass the class with updated values.
     */


     public void updateWorkoutClass(WorkoutClass workoutClass) {
        String query = "UPDATE workout_classes SET workout_class_type = ?, workout_class_description = ? WHERE workout_id = ? AND trainer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, workoutClass.getType());
            statement.setString(2, workoutClass.getDescription());
            statement.setInt(3, workoutClass.getWorkoutID());
            statement.setInt(4, workoutClass.getTrainerID());

            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Workout class updated successfully.");
            } else {
                System.out.println("No workout class updated (check permissions or ID).");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a workout class by ID and trainer ownership check.
     *
     * @param workoutID the class ID to delete.
     * @param trainerID the trainer who owns the class.
     */


     public void deleteWorkoutClass(int workoutID, int trainerID) {
        String query = "DELETE FROM workout_classes WHERE workout_id = ? AND trainer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setInt(1, workoutID);
            statement.setInt(2, trainerID);

            int deletedrows = statement.executeUpdate();
            if (deletedrows > 0) {
                System.out.println("Workout class deleted.");
            } else {
                System.out.println("No class deleted (check ID or ownership).");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean deleteWorkoutClassesByTrainer(int trainerId) {
        String query = "DELETE FROM workout_classes WHERE trainer_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
    
            statement.setInt(1, trainerId);
            int rows = statement.executeUpdate();
            return rows >= 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
}
