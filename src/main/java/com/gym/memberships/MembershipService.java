package com.gym.memberships;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Service layer for handling membership-related operations.
 * Connects UI logic with the data layer (DAO).
 */

public class MembershipService {

    private MembershipDAO membershipDAO;

    /**
     * Default constructor initializes the DAO.
     */

    public MembershipService() {
        this.membershipDAO = new MembershipDAO();
    }

    /**
     * Allows a Member or Trainer to purchase a new membership.
     *
     * @param membership The membership object to add.
     */


    public void purchaseMembership(Membership membership) {
        if (membership == null) {
            System.out.println("Membership is null.");
            return;
        }
        membershipDAO.addMembership(membership);
    }


    /**
     * Displays all memberships for a specific user (Member or Trainer).
     *
     * @param memberID The ID of the user.
     */


    public void viewMyMemberships(int memberID) {
        List<Membership> myMemberships = membershipDAO.getMembershipsByMemberID(memberID);

        if (myMemberships.isEmpty()) {
            System.out.println("You have no memberships yet.");
        } else {
            System.out.println("\n╔══════╦════════════════╦══════════════════════════════════════╦════════════╦════════════╗");
            System.out.printf("║ %-4s ║ %-14s ║ %-36s ║ %-10s ║ %-10s ║%n",
                    "ID", "Type", "Description", "Cost", "User ID");
            System.out.println("╠══════╬════════════════╬══════════════════════════════════════╬════════════╬════════════╣");
    
            for (Membership m : myMemberships) {
                System.out.printf("║ %-4d ║ %-14s ║ %-36s ║ $%-9.2f ║ %-10d ║%n",
                        m.getMembershipID(),
                        m.getMembershipType(),
                        m.getMembershipDescription(),
                        m.getMembershipCost(),
                        m.getMemberID());
            }
    
            System.out.println("╚══════╩════════════════╩══════════════════════════════════════╩════════════╩════════════╝");
        }
    }

    /**
     * Displays all memberships in the system (Admin view).
     */


    public void viewAllMemberships() {
        List<Membership> allMemberships = membershipDAO.getAllMemberships();

        if (allMemberships.isEmpty()) {
            System.out.println("No memberships found.");
        } else {
            System.out.println("\n╔══════╦════════════════╦══════════════════════════════════════╦════════════╦════════════╗");
            System.out.printf("║ %-4s ║ %-14s ║ %-36s ║ %-10s ║ %-10s ║%n",
                    "ID", "Type", "Description", "Cost", "User ID");
            System.out.println("╠══════╬════════════════╬══════════════════════════════════════╬════════════╬════════════╣");
    
            for (Membership m : allMemberships) {
                System.out.printf("║ %-4d ║ %-14s ║ %-36s ║ $%-9.2f ║ %-10d ║%n",
                        m.getMembershipID(),
                        m.getMembershipType(),
                        m.getMembershipDescription(),
                        m.getMembershipCost(),
                        m.getMemberID());
            }
    
            System.out.println("╚══════╩════════════════╩══════════════════════════════════════╩════════════╩════════════╝");
        }
    }

    /**
     * Calculates and displays the total revenue from all memberships.
     * for Admins.
     */


    public void viewTotalRevenue() {
        double total = membershipDAO.getTotalRevenue();
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.printf ("║ Total Revenue from Memberships: $%.2f ║%n", total);
        System.out.println("╚═════════════════════════════════════════╝");
    }

    /**
     * Calculates and displays total membership expenses for a user.
     * Intended for Members.
     *
     * @param memberID The user ID.
     */
    
    public void viewTotalExpenses(int memberID) {
        double total = membershipDAO.getTotalExpensesByMember(memberID);
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.printf ("║ Your Total Membership Expenses: $%.2f ║%n", total);
        System.out.println("╚═════════════════════════════════════════╝");
    }

    public static class ConsoleFormatter {

        public static void printMembershipReceipt(Membership membership, String username) {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a"));

            System.out.println("\n╔══════════════════════════════════════════════╗");
            System.out.println("║          MEMBERSHIP PURCHASE RECEIPT         ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.printf ("║ Member:     %-33s║%n", username);
            System.out.printf ("║ Type:       %-33s║%n", membership.getMembershipType());
            System.out.printf ("║ Description:%-33s║%n", membership.getMembershipDescription());
            System.out.printf ("║ Cost:       $%-32.2f║%n", membership.getMembershipCost());
            System.out.printf ("║ Date:       %-33s║%n", date);
            System.out.println("╚══════════════════════════════════════════════╝");
        }
    }

    public boolean deleteMembershipsForUser(int memberId) {
        return membershipDAO.deleteMembershipsByMemberId(memberId);
    }
    
}
