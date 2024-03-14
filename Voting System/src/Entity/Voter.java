/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import ADT.List;
import ADT.DoublyLinkedList;

/**
 *
 * @author Asus
 */
public class Voter {

    private static int lastAssignedID = 999;
    private int voteID;
    private String userName;
    private String password;
    private String studentID;
    private int vote;
    private List<Voter> voterList;

    public Voter(String userName, String password, String studentID) {
        this.voteID = ++lastAssignedID;
        this.userName = userName;
        this.password = password;
        this.studentID = studentID;
        vote = 2;
        voterList = new DoublyLinkedList<>();
    }

    public Voter(String userName, String password, String studentID, int Vote) {
        this.voteID = ++lastAssignedID;
        this.userName = userName;
        this.password = password;
        this.studentID = studentID;
        this.vote = Vote;
        voterList = new DoublyLinkedList<>();
    }



    public Voter(String studentID) {
        this.studentID = studentID;
        voterList = new DoublyLinkedList<>();
    }

    public Voter() {
        this("", "", "");
        voterList = new DoublyLinkedList<>();
    }

    public int getNextID() {
        return lastAssignedID + 1;
    }

    public List<Voter> getVoters() {
        return voterList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getVoteID() {
        return voteID;
    }

    public int getVote() {
        return vote;
    }

    public int voted() {
        vote--;
        if (vote == 0) {
            System.out.println("You had been used all your vote!");
        }
        return vote;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Voter)) {
            return false;
        }
        return (this.studentID.equals(((Voter) obj).getStudentID()));
    }


    public String toString() {
        return String.format("%-15s %-30s %-30s %-15s %-12s", this.voteID, this.userName, this.password, this.studentID, this.vote);
    }
}
