package com.gym.memberships;

/**
 * Represents a gym membership.
 * A membership is associated with a user (member or trainer).
 */

public class Membership {

    //=======================Attributes========================

    private int membershipID;
    private String membershipType;
    private String membershipDescription;
    private double membershipCost;
    private int memberID; // Foreign Key referencing users.user_id (Member)

    //=======================Constructors==========================

    /**
     * Default constructor.
     */

    public Membership() {
        this.membershipID = 0;
        this.membershipType = null;
        this.membershipDescription = null;
        this.membershipCost = 0.0;
        this.memberID = 0;
    }


    /**
     * Constructor without ID, typically used for creation before persistence.
     * 
     * @param membershipType Type of the membership (e.g., Monthly, Yearly).
     * @param membershipDescription Description/details of the membership.
     * @param membershipCost Price of the membership.
     * @param memberID The ID of the user who purchased the membership.
     */

    public Membership(String membershipType, String membershipDescription, double membershipCost, int memberID) {
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberID = memberID;
    }


    /**
     * Full constructor used when retrieving from the database (includes ID).
     * 
     * @param membershipID Unique ID for the membership.
     * @param membershipType Type of the membership.
     * @param membershipDescription Description of the membership.
     * @param membershipCost Price of the membership.
     * @param memberID User ID of the member who owns it.
     */

    public Membership(int membershipID, String membershipType, String membershipDescription, double membershipCost, int memberID) {
        this.membershipID = membershipID;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberID = memberID;
    }

    //=============================Getters and Setters===========================
    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipDescription() {
        return membershipDescription;
    }

    public void setMembershipDescription(String membershipDescription) {
        this.membershipDescription = membershipDescription;
    }

    public double getMembershipCost() {
        return membershipCost;
    }

    public void setMembershipCost(double membershipCost) {
        this.membershipCost = membershipCost;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    //=====================toString=========================

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + membershipID +
                ", type='" + membershipType + '\'' +
                ", description='" + membershipDescription + '\'' +
                ", cost=$" + String.format("%.2f", membershipCost) +
                ", memberID=" + memberID +
                '}';
    }
    
}
