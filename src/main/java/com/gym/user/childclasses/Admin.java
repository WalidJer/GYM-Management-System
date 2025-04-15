package com.gym.user.childclasses;

import com.gym.user.User;

/**
 * Represents an Admin in the Gym Management System.
 * Admins can manage users and view financial statistics.
 */

public class Admin extends User{

    //=============================Constructors=================================

    /**
     * No-argument constructor.
     * Sets the role to "admin".
     */

    public Admin() {
        this.setRole("admin");
    }


    /**
     * Full constructor to create an admin with details.
     *
     * @param username     The admin's username.
     * @param passwordHash The hashed password.
     * @param email        The admin's email.
     * @param phone        Phone number.
     * @param address      Physical address.
     */ 

    public Admin(String username, String passwordHash, String email, String phone, String address) {
        super(username, passwordHash, email, phone, address, "admin");
    }

    /**
     * Copy constructor to convert a User to an Admin.
     *
     * @param user The user object to copy from.
     */


    public Admin(User user) {
        super(user);
    }

    //======================================toString=====================================

    /**
     * Returns a string representation of the Admin.
     * 
     * @return String with admin details.
     */

    @Override
    public String toString() {
        return "Admin{" + super.toString() + "}";
    }
    
}
