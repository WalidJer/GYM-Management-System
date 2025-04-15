package com.gym;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import com.gym.memberships.Membership;
import com.gym.memberships.MembershipService;
import com.gym.user.User;
import com.gym.user.UserService;
import com.gym.workoutclasses.WorkoutClass;
import com.gym.workoutclasses.WorkoutClassService;

public class GymApp 
{
    private static final UserService userService = new UserService();
    private static final MembershipService membershipService = new MembershipService();
    private static final WorkoutClassService workoutClassService = new WorkoutClassService();


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println();;
        printBanner();
        

        while (true) {
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║        MAIN MENU           ║");
            System.out.println("╚════════════════════════════╝");
            System.out.println(" [1] Register a New Account");
            System.out.println(" [2] Login to Your Account");
            System.out.println(" [0] Exit Application");
            System.out.print("\nSelect an option (0-2): ");
        
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    register(scanner);
                    break;
                case "2":
                User user = login(scanner);
                if (user != null) {
                    showLoading("Logging you in");
                    System.out.printf("\n Welcome back, %s! (%s)\n", user.getUsername(), user.getRole().toUpperCase());
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy - h:mm a");
                    String formattedDate = now.format(formatter);
                    System.out.println("You logged in at: " + formattedDate);
                    String role = user.getRole();

                
                    if (role.equalsIgnoreCase("admin")) {
                        adminMenu(scanner);
                    } else if (role.equalsIgnoreCase("trainer")) {
                        trainerMenu(scanner, user);
                    } else if (role.equalsIgnoreCase("member")) {
                        memberMenu(scanner, user);
                    } else {
                        System.out.println("Unknown role. Returning to main menu.");
                    }
                }
                    break;
                case "0":
                    printLogoutMessage();
                    System.out.println("Thanks for using KEYIN Gym Management System. Stay fit!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Trainer Menu
    private static void trainerMenu(Scanner scanner, User user) {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║         TRAINER MENU           ║");
        System.out.println("╚════════════════════════════════╝");
        while (true) {
            System.out.println("\n-----------------------------------------");
            System.out.println("Please choose from the following options:");
            System.out.println(" [1] Purchase Membership");
            System.out.println(" [2] View My Memberships");
            System.out.println(" [3] Create Workout Class");
            System.out.println(" [4] View My Workout Classes");
            System.out.println(" [5] Update Workout Class");
            System.out.println(" [6] Delete Workout Class");
            System.out.println(" [0] Logout");
            System.out.println("-----------------------------------------");
            System.out.print("Your selection: ");
    
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createMembership(scanner, user);
                    break;
                case "2":
                    membershipService.viewMyMemberships(user.getId());
                    break;
                case "3":
                    System.out.print("Enter class type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    workoutClassService.createWorkoutClass(new WorkoutClass(type, desc, user.getId()));
                    break;
                case "4":
                    workoutClassService.viewMyWorkoutClasses(user.getId());
                    break;
                case "5":
                    System.out.print("Enter ID of class to update: ");
                    int updateID = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new class type: ");
                    String newType = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDesc = scanner.nextLine();
                    workoutClassService.updateWorkoutClass(new WorkoutClass(updateID, newType, newDesc, user.getId()));
                    break;
                case "6":
                    System.out.print("Enter ID of class to delete: ");
                    int deleteID = Integer.parseInt(scanner.nextLine());
                    System.out.print("Are you sure you want to delete class ID " + deleteID + "? (yes/no): ");
                    String confirmDelete = scanner.nextLine().trim().toLowerCase();
                    if (confirmDelete.equals("yes")) {
                        workoutClassService.deleteWorkoutClass(deleteID, user.getId());
                    } else {
                        System.out.println("Class deletion canceled.");
                    }
                    break;
                case "0":
                    System.out.println("Logging out of Trainer Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please select between 0 and 6.");
            }
        }
    }

    // Member Menu
    private static void memberMenu(Scanner scanner, User user) {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║         MEMBER MENU            ║");
        System.out.println("╚════════════════════════════════╝");

        while (true) {
            System.out.println("\n------------------------------------------");
            System.out.println("Please choose from the following options:");
            System.out.println(" [1] Purchase Membership");
            System.out.println(" [2] View My Memberships");
            System.out.println(" [3] View All Workout Classes");
            System.out.println(" [4] View Total Membership Expenses");
            System.out.println(" [0] Logout");
            System.out.println("------------------------------------------");
            System.out.print("Your selection: ");
    
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createMembership(scanner, user);
                    break;
                case "2":
                    membershipService.viewMyMemberships(user.getId());
                    break;
                case "3":
                    workoutClassService.viewAllWorkoutClasses();
                    break;
                case "4":
                    membershipService.viewTotalExpenses(user.getId());
                    break;
                case "0":
                    System.out.println("Logging out of Member Menu......");
                    return;
                default:
                    System.out.println("Invalid option. Please select a number between 0 and 4.");
            }
        }
    }

    // Admin Menu
    private static void adminMenu(Scanner scanner) {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║          ADMIN MENU            ║");
        System.out.println("╚════════════════════════════════╝");
        while (true) {
            System.out.println("\n------------------------------------------");
            System.out.println("Please choose from the following options:");
            System.out.println(" [1] View All Memberships");
            System.out.println(" [2] View Total Revenue");
            System.out.println(" [3] View All Users");
            System.out.println(" [4] Delete User by ID");
            System.out.println(" [0] Logout");
            System.out.println("------------------------------------------");
            System.out.print("Your selection: ");
    
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    membershipService.viewAllMemberships();
                    break;
                case "2":
                    membershipService.viewTotalRevenue();
                    break;
                case "3":
                    userService.printAllUsers();
                    break;
                
                case "4":
                    System.out.print("Enter User ID to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Are you sure you want to delete user ID " + id + "? (yes/no): ");
                    String confirmDelete = scanner.nextLine().trim().toLowerCase();
                
                    if (confirmDelete.equals("yes")) {
                        boolean deleted = userService.deleteUser(id);
                
                        if (!deleted) {
                            System.out.println("User could not be deleted (likely due to memberships or workout classes).");
                
                            System.out.print("Do you want to delete their memberships and workout classes first? (yes/no): ");
                            String confirmCascade = scanner.nextLine().trim().toLowerCase();
                
                            if (confirmCascade.equals("yes")) {
                                boolean membershipsDeleted = membershipService.deleteMembershipsForUser(id);
                                boolean classesDeleted = workoutClassService.deleteWorkoutClassesByTrainer(id);
                
                                if (membershipsDeleted || classesDeleted) {
                                    System.out.println("Related data deleted. Retrying user deletion...");
                                    boolean retry = userService.deleteUser(id);
                
                                    if (retry) {
                                        System.out.println("User deleted successfully.");
                                    } else {
                                        System.out.println("User could not be deleted even after cleanup.");
                                    }
                                } else {
                                    System.out.println("No memberships or classes found, or deletion failed.");
                                }
                            } else {
                                System.out.println("Cancelled. User was not deleted.");
                            }
                        } else {
                            System.out.println("User deleted successfully.");
                        }
                    } else {
                        System.out.println("Deletion canceled.");
                    }
                    break;
                case "0":
                    System.out.println("Logging out of Admin Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please select a number between 0 and 4.");
            }
        }
    }

    // Registration
    private static void register(Scanner scanner) {
        System.out.println("\n\t\t\t╔═══════════════════════════════════════╗");
        System.out.println("\t\t\t║           ACCOUNT REGISTRATION        ║");
        System.out.println("\t\t\t║           Create Your Account         ║");
        System.out.println("\t\t\t╚═══════════════════════════════════════╝");
        System.out.println("\t\tPlease fill in your details to create an account.");
        System.out.println("\t\t\t----------------------------------------");
        String username;
        while (true) {
            System.out.print("Enter username (min 3 chars, no spaces): ");
            username = scanner.nextLine().trim();
            if (username.length() >= 3 && !username.contains(" ")) 
            break;
            System.out.println("Invalid username. Please try again.");
        }
    
        String password;
        while (true) {
            System.out.print("Enter password (min 5 chars): ");
            password = scanner.nextLine();
            if (password.length() >= 5) 
            break;
            System.out.println("Password too short. Try again.");
        }
    
        String email;
        while (true) {
            System.out.print("Enter email address: ");
            email = scanner.nextLine().trim();
            if (email.matches("^\\S+@\\S+\\.\\S+$")) 
            break;
            System.out.println("Invalid email format. Please enter a valid email.");
        }

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        String role;
        while (true) {
            System.out.print("Enter role (admin, trainer, member): ");
            role = scanner.nextLine().trim().toLowerCase();
            if (role.equals("admin") || role.equals("trainer") || role.equals("member")){
                break;
            }
            System.out.println("Invalid role. Please enter 'admin', 'trainer', or 'member'.");
        }

        System.out.println("--------------------------------------------------------");
        System.out.println("Registering your account...");
    
        // Register the user
        User user = new User(username, password, email, phone, address, role);
        boolean success = userService.register(user);
        if (success) {
            System.out.printf("\nRegistration successful! You can now login as a %s.\n", role.toUpperCase());
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    // Login
    private static User login(Scanner scanner) {
        System.out.println("\n\t\t\t╔═══════════════════════════════════════╗");
        System.out.println("\t\t\t║             SYSTEM LOGIN              ║");
        System.out.println("\t\t\t║           Login Into Account          ║");
        System.out.println("\t\t\t╚═══════════════════════════════════════╝");
        System.out.println("\t\t\tPlease enter your credentials below.");
        System.out.println("\t\t\t----------------------------------------");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return userService.login(username, password);
    }

    // membership input
    private static void createMembership(Scanner scanner, User user) {
        System.out.println("\n\t\t\t╔═══════════════════════════════════════╗");
        System.out.println("\t\t\t║        MEMBERSHIP REGISTRATION        ║");
        System.out.println("\t\t\t║      Join and Level Up Your Fit!      ║");
        System.out.println("\t\t\t╚═══════════════════════════════════════╝");
        System.out.println("\t\t\tFill in the membership details below.");
        System.out.println("\t\t\t----------------------------------------");
        System.out.print("Enter Membership Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Description: ");
        String desc = scanner.nextLine();
        
        double cost;
        while (true) {
            System.out.print("Enter Cost: ");
            try {
                cost = Double.parseDouble(scanner.nextLine().trim());
                if (cost > 0) break;
                else System.out.println("Cost must be greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Please enter a valid number (e.g., 49.99): $");
            }
        }

        Membership m = new Membership(type, desc, cost, user.getId());
        membershipService.purchaseMembership(m);
        MembershipService.ConsoleFormatter.printMembershipReceipt(m, user.getUsername());
    }

    private static void showLoading(String message) {
        System.out.print(message);
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print(" .");
        }
        System.out.println(" Done.");
    }




    private static void printBanner() {
        System.out.println(ANSI_YELLOW +
            "╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           KEYIN FITNESS CLUB                              ║");
        System.out.println("║                      STRONG BODY. STRONG MIND.                            ║");
        System.out.println("║       ------------------------------------------------------------        ║");
        System.out.println("║       WHEN LIFE GIVES YOU PAIN... HIT THE IRON AT KEYIN GYM!              ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝"
            + ANSI_RESET);
    }

    private static void printLogoutMessage() {
        System.out.println("\n\t\t\t╔═══════════════════════════════════════╗");
        System.out.println("\t\t\t║             LOGGING OUT...            ║");
        System.out.println("\t\t\t║     Thank you for visiting Keyin Gym  ║");
        System.out.println("\t\t\t║        Stay strong. Stay healthy.     ║");
        System.out.println("\t\t\t╚═══════════════════════════════════════╝");
        System.out.println("\n");
    }
    


}
