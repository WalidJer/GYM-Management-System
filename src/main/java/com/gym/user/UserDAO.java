package com.gym.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gym.database.DBConnection;


/**
 * Data Access Object (DAO) for performing database operations related to users.
 */


public class UserDAO {

    /**
     * Retrieves all users from the database.
     * 
     * @return List of all users in the system.
     */
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("address"),
                    rs.getString("role")
                );
                users.add(user);
                
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
}

    /**
     * Registers a new user in the system.
     * 
     * @param user The user object to insert into the database.
     */

    public boolean registerUser(User user) {
        String query = "INSERT INTO users (username, email, password_hash, phone_number, address, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getRole());

            int rows =statement.executeUpdate();
            return rows > 0;
            // System.out.println("User registered successfully! Rows affected: " + addedrow);

        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }


    /**
     * Fetches a user from the database by their username.
     * 
     * @param username The username to search for.
     * @return User object if found, otherwise null.
     */

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("address"),
                    rs.getString("role")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Checks if a given username or email already exists in the system.
     * 
     * @param username The username to check.
     * @param email    The email to check.
     * @return true if username or email is taken, false otherwise.
     */

    public boolean isUsernameOrEmailTaken(String username, String email) {
        String query = "SELECT 1 FROM users WHERE LOWER(username) = ? OR LOWER(email) = ?";
    
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
    
                statement.setString(1, username.toLowerCase().trim());
                statement.setString(2, email.toLowerCase().trim());
    
            ResultSet rs = statement.executeQuery();
            return rs.next(); // true if a record exists
    
        } catch (SQLException e) {
            e.printStackTrace();
            return true; 
        }
    }

    /**
     * Deletes a user from the system by their ID.
     * 
     * @param userId The ID of the user to delete.
     * @return true if the user was deleted successfully, false otherwise.
     */

    public boolean deleteUserById(int userId) {
        String checkMembershipsQuery = "SELECT 1 FROM memberships WHERE member_id = ?";
        String deleteUserQuery = "DELETE FROM users WHERE user_id = ?";
    
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(checkMembershipsQuery)) {
    
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
    
            if (rs.next()) {
                
                return false; 
            }
    
           
            try (PreparedStatement deleteStmt = con.prepareStatement(deleteUserQuery)) {
                deleteStmt.setInt(1, userId);
                int rows = deleteStmt.executeUpdate();
                return rows > 0;
            }
    
        } catch (SQLException e) {
            System.out.println("Unable to delete user. Possible constraint: active memberships or workout classes.");
        }
        return false;
    }
}
