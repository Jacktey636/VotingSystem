/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Objects;
import java.util.Date;

/**
 *
 * @author user
 */
public class Voting implements Comparable<Voting> {

    private Candidates can;
    private Voter vot;
    private String voterStudentID, candidateStudentID;
    private Date VoteDate;

    public Voting(Candidates can) {
        this.can = can;
    }

    public Voting(String voter, String Cand, Date VoteDate) {
        voterStudentID = voter;
        candidateStudentID = Cand;
        this.VoteDate = VoteDate;
    }

    public Voter getVot() {
        return vot;
    }

    public String getVoterStudentID() {
        return voterStudentID;
    }

    public void setVoterStudentID(String voterStudentID) {
        this.voterStudentID = voterStudentID;
    }

    public String getCandidateStudentID() {
        return candidateStudentID;
    }

    public void setCandidateStudentID(String candidateStudentID) {
        this.candidateStudentID = candidateStudentID;
    }

    public Date getVoteDate() {
        return VoteDate;
    }

    public void setVoteDate(Date VoteDate) {
        this.VoteDate = VoteDate;
    }

    public Candidates getCan() {
        return can;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Voting other = (Voting) obj;
        return Objects.equals(can, other.can);
    }

    @Override
    public int hashCode() {
        return Objects.hash(can);
    }

    @Override
    public int compareTo(Voting other) {
        int amountComparison = Integer.compare(can.getAmount(), other.getCan().getAmount());
        if (amountComparison == 0) {
            // If the amounts are equal, compare the candidate IDs
            return can.getID().compareTo(other.can.getID());
        } else {
            // If the amounts are not equal, return the result of the amount comparison
            return amountComparison;
        }
    }

    @Override
    public String toString() {
        return String.format("%-25s %-30s %-40s" , this.voterStudentID , this.candidateStudentID , this.VoteDate);
//        return "Voting"
//                + "\nvoterStudentID: " + voterStudentID
//                + "\ncandidateStudentID=" + candidateStudentID
//                + "\nVote Date: " + VoteDate;
    }

}
