/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import ADT.DoublyLinkedList;
import ADT.List;
import Entity.Voter;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class VoterManagement {

    static List<Voter> voterList = new DoublyLinkedList<>();
    Scanner sc = new Scanner(System.in);
    private static Voter currentUser;

    public static void addVoter() {
  
        voterList.add(new Voter("Alice", "password1", "22WMR00001",1));
        voterList.add(new Voter("Bob", "password2", "22BMR00200",0));
        voterList.add(new Voter("Charlie", "password3", "22WMR00300",2));
        voterList.add(new Voter("Jack", "password4", "22WOP00004",0));
        voterList.add(new Voter("Jason", "password5", "22WMR00050",0));
    }

    public List<Voter> getVoters() {
        return voterList;
    }

    public static void main(String[] args) {
//        Voter voter = new Voter();
        VoterManagement vm = new VoterManagement();
        addVoter();
        System.out.println(voterList);
    }

}
