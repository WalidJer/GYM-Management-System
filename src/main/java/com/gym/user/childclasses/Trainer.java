package com.gym.user.childclasses;

import com.gym.user.User;

/**
 * Represents a Trainer in the Gym Management System.
 * Trainers can create and manage workout classes, and purchase memberships.
 */

public class Trainer extends User {

    //=============================Constructors=================================

    /**
     * No-argument constructor.
     * Sets the role to "trainer".
     */
    public Trainer() {
        this.setRole("trainer");
    }


    /**
     * Full constructor to create a trainer with details.
     *
     * @param username     The trainer's username.
     * @param passwordHash The hashed password.
     * @param email        Email address.
     * @param phone        Phone number.
     * @param address      Physical address.
     */

    public Trainer(String username, String passwordHash, String email, String phone, String address) {
        super(username, passwordHash, email, phone, address, "trainer");
    }

    /**
     * Copy constructor to convert a User to a Trainer.
     *
     * @param user The user object to copy from.
     */

    public Trainer(User user) {
        super(user);
    }

    //======================================toString=====================================

    /**
     * Returns a string representation of the Trainer.
     * 
     * @return String with trainer details.
     */

    @Override
    public String toString() {
        return "Trainer{" + super.toString() + "}";
    }
    
}
