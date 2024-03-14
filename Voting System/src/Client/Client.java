/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import ADT.BinarySearchTree;
import ADT.BinarySearchTreeInterface;
import ADT.List;
import ADT.DoublyLinkedList;

import Entity.Voter;
import Entity.Candidates;
import Entity.Voting;
import java.time.Instant;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author user
 */
public class Client {

    static BinarySearchTreeInterface<Candidates> canList = new BinarySearchTree<>();
    static BinarySearchTreeInterface<Voting> voteList = new BinarySearchTree<>();
    static JFrame frame = new JFrame();
    static Scanner scanner = new Scanner(System.in);
    static String usernameAdmin;
    static String passwordAdmin;
    static String choice1;
    static String choiceAdmin;
    static String searchChoice;
    static String editChoice;
    static String addChoice;
    static String reportChoice;
    static String newName;
    static String newID;
    static String newSlogan;
    static int newAmount;
    static String removeID;
    static String searchID;
    static String editID;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    static Date voteDate = new Date();

    static VoterManagement voter = new VoterManagement();
    static List<Voter> voterList = voter.getVoters();
    static List<Voting> votingHistory = new DoublyLinkedList<>();
    private static Voter currentUser;
    static int selection;
    static int selectionSub;
    static boolean found = false;
    static int userLogin;
    static Scanner sc = new Scanner(System.in);

    public static void toAdmin() {
        do {
            System.out.print("\nEnter 0 to back to menu: ");
            addChoice = scanner.next();
            if (addChoice.equals("0")) {
                adminMenu();

            } else {
                errorMessage("Please enter 0 !");

            }
        } while (!addChoice.equals("0"));

    }

    public static void errorMessage(String message) {
        JOptionPane.showMessageDialog(null, (message + " Please Input Again."));

    }

    public static void firstMenu() {
        System.out.println("=======================================================================================================================================");
        System.out.println("                                        Tarumt Singing Idols Voting System");
        System.out.println("=======================================================================================================================================");

        do {
            System.out.println("\n");
            System.out.println("=======================================================================================================================================");
            System.out.println("Main Menu");
            System.out.println("=======================================================================================================================================");
            System.out.println("1. User");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.print("Your choice:");
            choice1 = scanner.next();
            if (choice1.equals("1")) {
                subMenu();
                voterMenu();
            } else if (choice1.equals("2")) {
                do {
                    System.out.print("Username:");
                    usernameAdmin = scanner.next();
                    System.out.print("Password:");
                    passwordAdmin = scanner.next();
                    if (usernameAdmin.equals("admin") && passwordAdmin.equals("123456789")) {
                        adminMenu();
                    } else {
                        errorMessage("Wrong password Or Username!");
                    }
                } while (!usernameAdmin.equals("Username") || !passwordAdmin.equals("123456789"));
            } else if(choice1.equals("3"))  {
                    System.exit(0);
            } else {
                errorMessage("Invalid Option!");

            }
        } while (!choice1.equals("1") && !choice1.equals("2"));
    }

    public static void adminMenu() {
        do {
            System.out.println("=======================================================================================================================================");
            System.out.println("Admin Menu");
            System.out.println("=======================================================================================================================================");
            System.out.println("1. Add New Candidate");
            System.out.println("2. Remove a Candidate");
            System.out.println("3. Search");
            System.out.println("4. Edit");
            System.out.println("5. Generate Report");
            System.out.println("0. Back");
            System.out.print("Your Choice:");
            choiceAdmin = scanner.next();
            if (choiceAdmin.equals("1")) {
                System.out.print("Please enter a new candidates student ID:");
                newID = scanner.next();
                if (canList.search(new Candidates(newID, "", "", 0))) {
                    System.out.println("Candidates Available add failed");
                    toAdmin();
                } else {
                    System.out.print("Please enter a new candidates name: ");
                    scanner.nextLine();
                    newName = scanner.nextLine();

                    System.out.print("Please enter a new candidates slogan:");
                    newSlogan = scanner.nextLine();

                    System.out.print("Please enter a new candidates number of votes:");

                    newAmount = scanner.nextInt();
                    System.out.println("\nSuccessfull add new candidates");
                    canList.add(new Candidates(newID, newName, newSlogan, newAmount));
                }

                toAdmin();
            } else if (choiceAdmin.equals("2")) {
                canList.printTree();
                System.out.print("Please enter the student ID of the candidate that you would like to remove:");
                removeID = scanner.next();
                if (canList.remove(new Candidates(removeID, "", "", 0)) != null) {
                    canList.remove(new Candidates(removeID, "", "", 0));
                    System.out.println("\nCandidate remove successful");
                } else {
                    System.out.println("Candidates Not Availables");
                }
                toAdmin();

            } else if (choiceAdmin.equals("3")) {
                System.out.print("Enter the candidates student ID:");
                searchID = scanner.next();
                if (canList.search(new Candidates(searchID, "", "", 0))) {
                    System.out.println("Found the Candidate!\n");
                    System.out.println(canList.getEntry(new Candidates(searchID, "", "", 0)));
                    System.out.println("1. Edit candidate");
                    System.out.println("2. Remove candidate");
                    System.out.println("0. back");
                    System.out.print("Please enter your choice: ");
                    searchChoice = scanner.next();
                    if (searchChoice.equals("1")) {
                        editCandidate();
                        System.out.println("Edit successful!");
                        toAdmin();

                    } else if (searchChoice.equals("2")) {
                        canList.remove(new Candidates(searchID, "", "", 0));
                        System.out.println("remove succesfull!");
                        toAdmin();
                    } else if (searchChoice.equals("0")) {
                        adminMenu();

                    } else {
                        errorMessage("Invalid Option!");
                    }

                } else {
                    System.out.println("Candidates Not Found!");
                    toAdmin();
                }

            } else if (choiceAdmin.equals("4")) {
                canList.printTree();
                System.out.print("Please enter the candidates student ID:");
                searchID = scanner.next();
                if (canList.search(new Candidates(searchID, "", "", 0))) {

                    System.out.println("Found the Candidate!");
                    System.out.println(canList.getEntry(new Candidates(searchID, "", "", 0)));

                    editCandidate();

                } else {
                    System.out.println("Candidate Not available");
                    toAdmin();
                }

            } else if (choiceAdmin.equals("5")) {
                reportMenu();

            } else if (choiceAdmin.equals("0")) {
                firstMenu();
            } else {
                errorMessage("Invalid Option");
            }
        } while (!choiceAdmin.equals("1") && !choiceAdmin.equals("2") && !choiceAdmin.equals("3")
                && !choiceAdmin.equals("4") && !choiceAdmin.equals("5") && !choiceAdmin.equals("0"));

    }

    public static void editBack() {
        String back;
        do {
            System.out.print("Enter 0 to back:");
            back = scanner.next();

            if (back.equals("0")) {
                editCandidate();
            } else {
                errorMessage("Invalid Option!");
            }
        } while (!back.equals(0));

    }

    public static void editCandidate() {
        Candidates editCan = canList.getEntry(new Candidates(searchID, "", "", 0));
        do {
            System.out.println("\n1. Edit Name");
            System.out.println("2. Edit Slogan");
            System.out.println("3. Edit Number of Vote");
            System.out.println("0. Back");
            System.out.print("Enter Your Choice:");
            editChoice = scanner.next();
            if (editChoice.equals("1")) {
                System.out.print("Enter the new candidate name:");
                scanner.nextLine();
                newName = scanner.nextLine();
                editCan.setName(newName);
                System.out.println("Edit successful!");
                editBack();

            } else if (editChoice.equals("2")) {

                System.out.print("Enter the new candidate Slogan:");
                scanner.nextLine();
                newSlogan = scanner.nextLine();
                editCan.setSlogan(newSlogan);
                System.out.println("Edit successful!");

                editBack();
            } else if (editChoice.equals("3")) {
                System.out.print("Enter the new number of vote:");
                newAmount = scanner.nextInt();
                editCan.setAmount(newAmount);
                System.out.println("Edit successful!");

                editBack();
                editCan.setAmount(newAmount);
            } else if (editChoice.equals("0")) {
                adminMenu();
            } else {
                errorMessage("Invalid Option");
            }
        } while (!editChoice.equals("1") && !editChoice.equals("2") && !editChoice.equals("3") && !editChoice.equals("0"));
    }

    public static void backReport() {
        String back;
        do {
            System.out.print("Enter 0 to back:");
            back = scanner.next();

            if (back.equals("0")) {
                reportMenu();
            } else {
                errorMessage("Invalid Option!");
            }
        } while (!back.equals(0));
    }

    public static void reportMenu() {
        System.out.println("1. Candidates Report");
        System.out.println("2. Voting Result Report");
        System.out.println("3. Voting History Report");
        System.out.println("4. Voter Report");
        System.out.println("0. Back");
        System.out.print("Please enter your choice: ");
        reportChoice = scanner.next();

        if (reportChoice.equals("1")) {
            System.out.println("Candidates List");
            System.out.println("=======================================================================================================================================");
            System.out.printf("%-15s %-30s %-60s %-2s\n", "Student ID", " Candidates Name", "Slogan", "Number Of Vote");
            System.out.println("=======================================================================================================================================");
            Iterator<Candidates> can = canList.getInorderIterator();
            while (can.hasNext()) {
                Candidates candidate = can.next();
                System.out.printf("%-15s %-30s %-60s %-2s\n", candidate.getID(), candidate.getName(), candidate.getSlogan(), candidate.getAmount());

            }
            System.out.println("\nTotal Candidates:" + canList.getSize());
            backReport();

        } else if (reportChoice.equals("2")) {
            System.out.println("Candidates Ranking");
            System.out.println("=======================================================================================================================================");
            System.out.printf("%-5s %-15s %-25s %-12s\n", "Rank", "Student ID", " Candidates Name:", "Number Of Vote");
            System.out.println("=======================================================================================================================================");
            Iterator<Candidates> iterator = canList.getInorderIterator_reverse();//setter
            int voteNum = 0;
            voteList.clear();
            while (iterator.hasNext()) {
                Candidates candidate = iterator.next();
                voteList.add(new Voting(candidate));

            }

            Iterator<Voting> vo = voteList.getInorderIterator_reverse();
            int no = 1;
            while (vo.hasNext()) {
                Voting vote = vo.next();
                System.out.printf("%-5s %-15s %-25s  %-2s\n", no++, vote.getCan().getID(), vote.getCan().getName(), vote.getCan().getAmount());
                voteNum += vote.getCan().getAmount();

            }
            double averageVote = ((double) voteNum / canList.getSize());
            System.out.println("\nTotal Vote:" + voteNum);
            System.out.print("Average Vote: ");
            System.out.printf("%.2f", averageVote);
            System.out.println("");
            backReport();

        } else if (reportChoice.equals("3")) {
            System.out.println("==========================================================================================================");
            System.out.printf("%-25s %-30s %-40s\n", "Voter ID", "Candidate ID", "Vote Date");
            System.out.println("==========================================================================================================");
            System.out.println(votingHistory);
            backReport();
        } else if (reportChoice.equals("4")) {
            printVoters();
            backReport();
        } else if (reportChoice.equals("0")) {
            adminMenu();
        } else {
            errorMessage("Invalid Option!");

        }
    }

    //voter
    public static void subMenu() {
        System.out.println("==========================================================================================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Back");
        System.out.print("Please enter your selection: ");

        int selection = sc.nextInt();
        System.out.println("==========================================================================================================");
        do {
            if (selection == 1) {
                do {
                    Login();
                    if (found == true) {
                        voterMenu();
                    }
                } while (found != true);
            } else if (selection == 2) {
                Register();
                subMenu();
            } else if (selection == 3) {
                firstMenu();
            } else {
                System.out.println("Invalid Selection");
                subMenu();
                break;
            }

        } while (selection != 1 && selection != 2);
    }

    public static void voterMenu() {
        System.out.println("==========================================================================================================");
        System.out.println("Voter Menu");
        System.out.println("==========================================================================================================");
        System.out.println("1.  Vote");
        System.out.println("2.  Edit Profile");
        System.out.println("3.  Remove Account");
        System.out.println("4.  Log Out");
        System.out.print("What you wanted to perform: ");
        selectionSub = sc.nextInt();

        do {
            switch (selectionSub) {
                case 1:
                    System.out.println("Candidates List");
                    System.out.println("==========================================================================================================");
                    System.out.printf("%-15s %-30s %-60s\n", "Student ID", " Candidates Name", "Slogan");
                    System.out.println("==========================================================================================================");
                    Iterator<Candidates> can = canList.getInorderIterator();
                    while (can.hasNext()) {
                        Candidates candidate = can.next();
                        System.out.printf("%-15s %-30s %-60s\n", candidate.getID(), candidate.getName(), candidate.getSlogan());

                    }
                    System.out.print("Please input the candidate student ID you wanted to perform: ");
                    searchID = scanner.next();
                    if (canList.search(new Candidates(searchID, "", "", 0))) {
                        int vote = currentUser.getVote();

                        if (vote > 0 && vote < 3) {
                            Candidates Vote = canList.getEntry(new Candidates(searchID, "", "", 0));
                            Vote.voting();
                            System.out.println("Vote Successfully");
                            votingHistory.add(new Voting(currentUser.getStudentID(), searchID, voteDate));
                            vote = currentUser.voted();
                        } else {
                            System.out.println("You had finished your vote");
                        }
                        voterMenu();
                    }

                    break;

                case 2:
                    editProfile();
                    voterMenu();
                    break;
                case 3:
                    removeAccount();
                    firstMenu();
                    break;
                case 4:
                    signOut();
                    found = false;
                    subMenu();
                    break;
                default:
                    System.err.println("Invalid Selection");
                    voterMenu();
                    break;
            }
        } while (selectionSub < 1 || selectionSub > 5);
    }

    public static void Register() {
        String userName;
        String password;
        String studentID;
        sc.nextLine();
        System.out.println("==========================================================================================================");
        System.out.println("Register");
        System.out.println("==========================================================================================================");
        System.out.print("Username: ");
        userName = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        System.out.print("StudentID: ");
        studentID = sc.nextLine();

        Voter newVoter = new Voter(studentID);
        if (voterList.contains(newVoter)) {
            System.out.println("Already have this student ID in list");
        } else {
            voterList.add(new Voter(userName, password, studentID));
            System.out.println("You had successfully registered!");
        }
    }

    public static boolean Login() {
        String userName;
        String password;
        System.out.println("");
        System.out.println("==========================================================================================================");
        System.out.println("Login");
        System.out.println("==========================================================================================================");
        sc.nextLine();
        System.out.print("Username: ");
        userName = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        Iterator<Voter> iterator = voterList.getIterator();
        while (iterator.hasNext()) {
            Voter voter = iterator.next();
            if (voter.getUserName().equals(userName) && voter.getPassword().equals(password)) {
                System.out.println("Welcome, " + voter.getUserName() + "!");
                currentUser = voter;
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Invalid username or password");
        }
        return found;
    }

    public static void removeAccount() {
        if (currentUser != null) {
            voterList.remove(currentUser);
            currentUser = null;
            System.out.println("Ur account has been removed!");
        } else {
            System.out.println("U are not yet login yet!");
        }

    }

    public static void signOut() {
        if (currentUser != null) {
            currentUser = null;
            System.out.println("You had log out");
        } else {
            System.out.println("You are not yet login!");
        }
    }

    public static void editProfile() {
        System.out.println("==========================================================================================================");
        System.out.println("Edit Profile!");
        System.out.println("==========================================================================================================");
        System.out.println("1. Username");
        System.out.println("2. Password");
        System.out.println("3. StudentID");
        System.out.println("4. Back");
        System.out.print("Please select the things that you want to edit:");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                sc.nextLine();
                System.out.print("Please enter the username that you wanna change to: ");
                String userName = sc.nextLine();
                currentUser.setUserName(userName);
                System.out.println("Username updated successfully!");
                break;
            case 2:
                sc.nextLine();
                System.out.print("Please enter the password that you wanna change to: ");
                String password = sc.nextLine();
                currentUser.setUserName(password);
                System.out.println("Password updated successfully!");
                break;
            case 3:
                sc.nextLine();
                System.out.print("Please enter the student ID that you wanna change to: ");
                String studentID = sc.nextLine();
                currentUser.setUserName(studentID);
                System.out.println("student ID updated successfully!");
                break;
            case 4:
                voterMenu();
                break;
            default:
                System.out.println("Invalid selecion");
                break;
        }
    }

    public static void printVoters() {
        System.out.println("1. Forward");
        System.out.println("2. Backward");
        System.out.println("3. Exit");
        System.out.print("Please select the print method you want to: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("List of Voters in Forwads:");
                System.out.println("==========================================================================================================");
                System.out.println("==========================================================================================================");
                System.out.printf("%-15s %-30s %-30s %-15s %-15s\n", "Voter ID", " User Name", "Password", "StudentID", "Vote Left");
                System.out.println("==========================================================================================================");
                voterList.printListForward();
                break;
            case 2:
                System.out.println("List of Voters In Backwards:");
                System.out.println("==========================================================================================================");
                System.out.printf("%-15s %-30s %-30s %-15s %-15s\n", "Voter ID", " User Name", "Password", "StudentID", "Vote Left");
                System.out.println("==========================================================================================================");
                voterList.printListBackward();

                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selecion");
                break;
        }
    }

    public static void addVoting() {
        Date setDate1 = new Date(123, 2, 23, 12, 2, 6);
        votingHistory.add(new Voting("22WMR005", "22WMR14567", setDate1));
        Date setDate2 = new Date(123, 2, 22, 17, 3, 12);
        votingHistory.add(new Voting("22BMR002", "22WMR14567", setDate2));
        Date setDate3 = new Date(123, 2, 21, 19, 1, 23);
        votingHistory.add(new Voting("22BMR002", "22WMR14567", setDate3));
        Date setDate4 = new Date(123, 2, 23, 15, 8, 31);
        votingHistory.add(new Voting("22WMR005", "22WMR14567", setDate4));
        Date setDate5 = new Date(123, 2, 19, 2, 7, 6);
        votingHistory.add(new Voting("22WOP004", "22WMR14567", setDate5));
        Date setDate6 = new Date(123, 2, 27, 10, 5, 16);
        votingHistory.add(new Voting("22WOP004", "22WMR14567", setDate6));
        Date setDate7 = new Date(123, 2, 3, 1, 6, 7);
        votingHistory.add(new Voting("22WMR001", "22WMR14567", setDate7));
    }

    public static void main(String[] args) {

        canList.add(new Candidates("23WMR34567", "Jason LIM JIA WEI", "Voice for all, vision for the future", 10));
        canList.add(new Candidates("22WMR36567", "Jimmy Leong Jun Jie", "Together, we can create a better future", 24));
        canList.add(new Candidates("22WMR14567", "Anson Tan Hao Jun", "Building a community where everyone has a voice", 14));
        canList.add(new Candidates("22WMB04567", "Kelly Lim Mei Ling", "Empowering our community, one voice at a time", 24));
        canList.add(new Candidates("22WEI06543", "Chen Kai Xin", "Advocating for progress, diversity, and unity", 7));
        canList.add(new Candidates("22WMR00256", "Tan Kai Lee", "Amplifying voices, forging connections, creating impact", 32));
        canList.add(new Candidates("21WMR05467", "Khaw Chan Yin", "Uniting our community through shared values and goals", 30));
        canList.add(new Candidates("20WMR04459", "Jenny Liew Mei Xin", "Leading with integrity, empathy, and purpose", 17));
//        dd/MM/yyyy HH:mm:ss

//        System.out.println(voteList);
//        System.out.println(voterList);
//        VoterManagement.voterList.printListForward();
        addVoting();
        VoterManagement.addVoter();
        firstMenu();

    }

}
