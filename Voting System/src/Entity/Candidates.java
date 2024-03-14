package Entity;

import ADT.BinarySearchTree;
import java.util.Comparator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user
 */
public class Candidates implements Comparable<Candidates> {

    private String ID;
    private String name;
    private String slogan;
    private int amount;

    public int compareTo(Candidates otherCandidate) {
        return this.ID.compareTo(otherCandidate.ID);
    }

    public Candidates(String ID, String name, String slogan, int amount) {
        this.ID = ID;
        this.name = name;
        this.slogan = slogan;
        this.amount = amount;
    }

    public void voting() {
        amount++;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getAmount() {
        return amount;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Candidates)) {
            return false;
        }

        Candidates other = (Candidates) obj;

        return ID.equals(other.ID);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    public String toString() {
        return "Student ID: " + ID + "\nStudent Name: " + name + "\nSlogan: " + slogan + "\nVoting Amount: " + amount + "\n\n";
    }

    

}
