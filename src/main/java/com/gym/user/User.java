package com.gym.user;

/**
 * Represents a user in the Gym Management System.
 * This is the base class extended by Admin, Trainer, and Member.
 */

public class User {

    //=============================Attributes==================================
    private int id;
    private String username;
    private String passwordHash;
    private String email;
    private String phoneNumber;
    private String address;
    private String role;  // e.g., "admin", "trainer", "member"


    //=============================Constructors=================================

    /**
     *            Default constructor.
     * Initializes all fields to default values.
     */

    public User() {
        this.id = 0;
        this.username = null;
        this.passwordHash = null;
        this.email = null;
        this.phoneNumber = null;
        this.address = null;
        this.role = null;  
    }

    /**
     * Full constructor with all fields including ID.
     * 
     * @param id           User's ID
     * @param username     Username
     * @param passwordHash Hashed password
     * @param email        Email address
     * @param phoneNumber  Phone number
     * @param address      Physical address
     * @param role         User role (admin/trainer/member)
     */


    public User(int id, String username, String passwordHash, String email, String phoneNumber, String address, String role){
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;   
    }

    /**
     * Constructor used during registration (ID auto-generated).
     *
     * @param username     Username
     * @param passwordHash Hashed password
     * @param email        Email address
     * @param phoneNumber  Phone number
     * @param address      Physical address
     * @param role         User role (admin/trainer/member)
     */


    public User( String username, String passwordHash, String email, String phoneNumber, String address, String role){
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;   
    }

    /**
     * Copy constructor.
     *
     * @param other The user object to copy from.
     */
    public User(User other) {
        this.id = other.id;
        this.username = other.username;
        this.passwordHash = other.passwordHash;
        this.email = other.email;
        this.phoneNumber = other.phoneNumber;
        this.address = other.address;
        this.role = other.role;
    }

    //===============================Getters and Setters===========================

    /** @return User ID */
    public int getId() {
        return id;
    }

    /** @param id Set user ID */
    public void setId(int id) {
        this.id = id;
    }

    /** @return Username */
    public String getUsername() {
        return username;
    }

    /** @param username Set username */
    public void setUsername(String username) {
        this.username = username;
    }

    /** @return Hashed password */
    public String getPasswordHash() {
        return passwordHash;
    }
    
    /** @param passwordHash Set hashed password */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /** @return Email address */
    public String getEmail() {
        return email;
    }

    /** @param email Set email */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /** @return Phone number */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** @param phoneNumber Set phone number */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** @return Address */
    public String getAddress() {
        return address;
    }

    /** @param address Set physical address */
    public void setAddress(String address) {
        this.address = address;
    }

    /** @return Role (admin/trainer/member) */
    public String getRole() {
        return role;
    }

    /** @param role Set user role */
    public void setRole(String role) {
        this.role = role;
    }

    //========================================toString=====================================

    /**
     * Returns a readable string representation of the user.
     * @return String with user details
     */

     
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
