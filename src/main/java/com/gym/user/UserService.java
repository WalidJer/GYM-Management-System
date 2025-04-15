package com.gym.user;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import com.gym.user.childclasses.Admin;
import com.gym.user.childclasses.Member;
import com.gym.user.childclasses.Trainer;

/**
 * Service class to handle user-related operations like registration,
 * login, user listing, and deletion.
 */

public class UserService {

    private UserDAO userDAO;

    /**
     * Initializes the service with a UserDAO.
     */

    public UserService() {
        userDAO = new UserDAO();
    }

    /**
     * Prints all users currently in the database.
     */

    public void printAllUsers() {
        List<User> users = userDAO.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("\n╔════╦══════════════════╦════════════╦════════════════════════════╦══════════════╦══════════════════════════╗");
            System.out.printf("║ %-2s ║ %-16s ║ %-10s ║ %-26s ║ %-12s ║ %-24s ║%n",
                    "ID", "Username", "Role", "Email", "Phone", "Address");
            System.out.println("╠════╬══════════════════╬════════════╬════════════════════════════╬══════════════╬══════════════════════════╣");
    
            for (User user : users) {
                System.out.printf("║ %-2d ║ %-16s ║ %-10s ║ %-26s ║ %-12s ║ %-24s ║%n",
                        user.getId(),
                        user.getUsername(),
                        user.getRole().toUpperCase(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getAddress());
            }
    
            System.out.println("╚════╩══════════════════╩════════════╩════════════════════════════╩══════════════╩══════════════════════════╝");
        }
    }

    /**
     * Registers a new user after checking for duplicates and hashing the password.
     * 
     * @param user The user object to register.
     */

    public boolean register(User user) {
        
        if (user == null) {
            System.out.println("User is null please provide a valid user");
            return false;
        }

        String normalizedUsername = user.getUsername().trim().toLowerCase();
        String normalizedEmail = user.getEmail().trim().toLowerCase();

        // Check for duplicate username/email
        if (userDAO.isUsernameOrEmailTaken(normalizedUsername, normalizedEmail)) {
            System.out.println("Username or email already in use.");
            return false;
        }

        // Securely hash password before storing

        String hashedPassword = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt(8));

        // Create user with normalized and secure values
        User newUser = new User(
            normalizedUsername, 
            hashedPassword, 
            normalizedEmail, 
            user.getPhoneNumber(), 
            user.getAddress(), 
            user.getRole());

        userDAO.registerUser(newUser);
        // System.out.println(user.getRole().toUpperCase() + " User registered successfully.");
        return true;
    }

    /**
     * Authenticates a user by checking username and password.
     * Converts the base user object into the appropriate role-specific object.
     * 
     * @param username The username input.
     * @param password The password input.
     * @return A role-specific user object (Admin, Trainer, Member) if successful, null otherwise.
     */

    public User login(String username, String password) {

        username = username.trim().toLowerCase();
        User user = userDAO.getUserByUsername(username);
    
        if (user == null) {
            System.out.println("The User Does Not Exist!");
            return null;
        }
    
        if (!BCrypt.checkpw(password, user.getPasswordHash())) {
            System.out.println("Wrong Password, Please Try Again!");
            return null;
        }

        // Convert to specific user role type
        switch (user.getRole().toLowerCase()) {
            case "admin":
                return new Admin(user);
            case "trainer":
                return new Trainer(user);
            case "member":
                return new Member(user);
            default:
                System.out.println("Unknown role: " + user.getRole());
                return user; 
        }
    }

    /**
     * Deletes a user from the database based on their ID.
     * 
     * @param userID The unique ID of the user to be deleted.
     */

     public boolean deleteUser(int userID) {
        return userDAO.deleteUserById(userID); // returns true or false
    }
    
}
