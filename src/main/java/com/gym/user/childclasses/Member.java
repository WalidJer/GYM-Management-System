package com.gym.user.childclasses;

import com.gym.user.User;

/**
 * Represents a Member in the Gym Management System.
 * Members can purchase memberships and view workout classes.
 */

public class Member extends User {

    //=============================Constructors=================================

    /**
     * No-argument constructor.
     * Sets the role to "member".
     */

    public Member() {
        this.setRole("member");
    }


    /**
     * Full constructor to create a member with details.
     *
     * @param username     The member's username.
     * @param passwordHash The hashed password.
     * @param email        Email address.
     * @param phone        Phone number.
     * @param address      Physical address.
     */ 

    public Member(String username, String passwordHash, String email, String phone, String address) {
        super(username, passwordHash, email, phone, address, "member");
    }

    /**
     * Copy constructor to convert a User to a Member.
     *
     * @param user The user object to copy from.
     */

    public Member(User user) {
        super(user);
    }

     //======================================toString=====================================

    /**
     * Returns a string representation of the Member.
     * 
     * @return String with member details.
     */
    @Override
    public String toString() {
        return "Member{" + super.toString() + "}";
    }
    
    
}
