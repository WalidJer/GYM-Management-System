package com.gym.memberships;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.database.DBConnection;

/**
 * Data Access Object (DAO) for managing membership records in the database.
 */

public class MembershipDAO {

    /**
     * Inserts a new membership record into the database.
     *
     * @param membership the membership to add.
     */


    public void addMembership(Membership membership) {
        String query = "INSERT INTO memberships (membership_type, membership_description, membership_cost, member_id) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, membership.getMembershipType());
            statement.setString(2, membership.getMembershipDescription());
            statement.setDouble(3, membership.getMembershipCost());
            statement.setInt(4, membership.getMemberID());

            int addedrows = statement.executeUpdate();
            System.out.println("Membership purchased successfully. Rows affected: " + addedrows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Retrieves all memberships associated with a specific user.
     *
     * @param userID the user ID to query.
     * @return list of Memberships.
     */


    public List<Membership> getMembershipsByMemberID(int userID) {
        List<Membership> membList = new ArrayList<>();
        String query = "SELECT * FROM memberships WHERE member_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                membList.add(new Membership(
                        rs.getInt("membership_id"),
                        rs.getString("membership_type"),
                        rs.getString("membership_description"),
                        rs.getDouble("membership_cost"),
                        rs.getInt("member_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membList;
    }

    /**
     * Retrieves all memberships in the system (for admin view).
     *
     * @return list of all Memberships.
     */


    public List<Membership> getAllMemberships() {
        List<Membership> allMemberships = new ArrayList<>();
        String query = "SELECT * FROM memberships";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                allMemberships.add(new Membership(
                        rs.getInt("membership_id"),
                        rs.getString("membership_type"),
                        rs.getString("membership_description"),
                        rs.getDouble("membership_cost"),
                        rs.getInt("member_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allMemberships;
    }



    /**
     * Calculates the total revenue generated from all memberships.
     *
     * @return total revenue.
     */


    public double getTotalRevenue() {
        String query = "SELECT SUM(membership_cost) AS total FROM memberships";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    /**
     * Calculates the total expenses (membership fees) paid by a specific user.
     *
     * @param memberID the user ID to query.
     * @return total amount paid by the member.
     */

    public double getTotalExpensesByMember(int memberID) {
        String query = "SELECT SUM(membership_cost) AS total FROM memberships WHERE member_id = ?";
        double total = 0.0;
    
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
    
            statement.setInt(1, memberID);
            ResultSet rs = statement.executeQuery();
    
            if (rs.next()) {
                total = rs.getDouble("total");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return total;
    }

    public boolean deleteMembershipsByMemberId(int memberId) {
        String query = "DELETE FROM memberships WHERE member_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
    
            statement.setInt(1, memberId);
            int rows = statement.executeUpdate();
            return rows > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
