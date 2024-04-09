package edu.ucalgary.oop;

import edu.ucalgary.oop.ReliefService;
import edu.ucalgary.oop.Database;
import edu.ucalgary.oop.DisasterVictim;
import edu.ucalgary.oop.FamilyRelation;
import edu.ucalgary.oop.Inquirer;
import edu.ucalgary.oop.MedicalRecord;
import edu.ucalgary.oop.Location;

import edu.ucalgary.oop.LogInquiry;
import edu.ucalgary.oop.EnterDisasterVictim;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        System.out.println("Aadi's Disaster Relief System");
        Database db = new Database();

        ArrayList<DisasterVictim> allVictims = new ArrayList();         
        Scanner mainScanner = new Scanner(System.in);
        boolean validChoice = false;
        // We will store all possible locations in memory (Foothills hospital and the Childrens hospital)
        Location foothills = new Location("foothills hospital", "1403 29 Street NW");
        Location childrens = new Location("childrens hospital", "28 Oki Drive");

        System.out.println("What do you want to do (Use Numbers 1: Access Disaster Victim Information, 2: Access Inquiry Information): ");
        String userChoice = mainScanner.nextLine();

        while (!validChoice){
            if (userChoice.equals("1")){
                validChoice = true;
                System.out.println("You have selected to work with DisasterVictim.");

                validChoice = false;
                while (!validChoice){
                    System.out.println("What would you like to do next? (1: Create a new disaster victim instance 2: Display information on a disaster victim 3: Edit a disaster victim 4: Exit) ");
                    userChoice = mainScanner.nextLine();
                    if (userChoice.equals("1")){
                        // Create new disaster victim
                        DisasterVictim newVictim = new DisasterVictim();
                        allVictims.add(newVictim);
                        System.out.println("Victim Created");
                    }

                    else if (userChoice.equals("2")) {
                        // Display disaster victim
                        // Search through allVictims by firstname to locate, and then display all available details about that person
                      
                        if (allVictims.isEmpty()) {
                            System.out.println("There are currently no disaster victims in the system.");
                        } else {
                            Scanner nameScanner = new Scanner(System.in);
                            System.out.println("Enter the first name of the disaster victim you want to display information for:");
                            String searchName = nameScanner.nextLine().trim();

                            DisasterVictim victimSelected;
                            boolean victimFound = false;
                            for (DisasterVictim victim : allVictims) {
                              if (victim.getFirstName().equalsIgnoreCase(searchName)) {
                                    System.out.println("Victim found.");
                                    victimFound = true;
                                    victimSelected = victim;
                                    System.out.println(victim.getFirstName());
                                    System.out.println(victim.getEntryDate());
                                    break;
                              }

                            }
                      
                            if (!victimFound) {
                                System.out.println("No disaster victim found with the name " + searchName);
                            }
                        }
                    }
                    
                    else if (userChoice.equals("3")){
                        // edit the victim

                        System.out.println("What is the victim's name? ");
                        String name = mainScanner.nextLine();

                        DisasterVictim victimSelected = new DisasterVictim("", "2004-10-10");
                        boolean victimFound = false;
                        for (DisasterVictim victim : allVictims) {
                          if (victim.getFirstName().equalsIgnoreCase(name)) {
                                System.out.println("Victim found.");
                                victimFound = true;
                                victimSelected = victim;
                                System.out.println(victim.getFirstName());
                                System.out.println(victim.getEntryDate());
                                break;
                          }
                        }
                  
                        if (!victimFound) {
                            System.out.println("No disaster victim found with the name " + name);   
                        }
                        
                        System.out.println("What would you like to edit for " + victimSelected.getFirstName() + " ");

                        System.out.println("Options (1: First Name, 2: Last Name, 3: Date of Birth/Age 4: Add/Remove Personal Belonging (Supply) 5: Add/Remove Family Connection 6: Add/Remove Medical Record 7: Change Gender, 8: Add/Remove Dietary Restrictions) : ");

                        userChoice = mainScanner.nextLine();

                        switch (userChoice) {

                            case "1":

                                System.out.println("What would you like to set the name to? ");
                                String newName1 = mainScanner.nextLine();
                                victimSelected.setFirstName(newName1);
                                break;
                        
                            case "2":
                                System.out.println("What would you like to set the name to? ");
                                String newName2 = mainScanner.nextLine();
                                victimSelected.setLastName(newName2);
                                
                            case "3":
                                System.out.println("Do you want to add an age or birthday (cant have both): ");
                                String ageOrBirth = mainScanner.nextLine();
                                if (ageOrBirth.equals("age")){
                                    System.out.println("What age is the person? ");
                                    String age = mainScanner.nextLine();
                                    victimSelected.setAge(age);
                                }
                                else if (ageOrBirth.equals("birthday")){
                                    System.out.println("What is the birthday of the person. ");
                                    String birthday = mainScanner.nextLine();
                                    victimSelected.setDateOfBirth(birthday);
                                }

                            case "4":
                                System.out.println("Would you like to add or remove? (add, remove) ");
                                String choiceSupply = mainScanner.nextLine();
                                
                                if (choiceSupply.equals("add")){
                                    
                                }

                                else if (choiceSupply.equals("remove")){

                                }

                            default:
                                break;
                        }


                    }

                    else if (userChoice.equals("4")){
                        System.out.println("Leaving the program.");
                        validChoice = true;
                    }

                    else{
                        System.out.println("That was not a valid choice, please try again.");
                    }

                }
                
            }
    
            











            else if (userChoice.equals("2")) {
                // Access Inquiry Information
                ArrayList<Inquirer> allInquiries = new ArrayList<>(); // Assuming you have an ArrayList to store Inquirer objects
              
                System.out.println("What would you like to do with Inquiries?");
                System.out.println("(1: Create a new Inquiry, 2: Search Inquiries, 3: Display all Inquiries)");
                String inquiryChoice = mainScanner.nextLine().trim();
              
                if (inquiryChoice.equals("1")) {
                  // Create a new Inquirer
                  System.out.println("Enter Inquirer Name:");
                  String inquirerName = mainScanner.nextLine().trim();
                  System.out.println("Enter Inquiry Description:");
                  String inquiryDescription = mainScanner.nextLine().trim();
                
                  Inquirer newInquiry = new Inquirer();
                  

                  allInquiries.add(newInquiry);
                  System.out.println("Inquiry Created Successfully!");
                } else if (inquiryChoice.equals("2")) {
                  // Search Inquiries
                  System.out.println("Enter keywords to search inquiries (separate by spaces):");
                  String searchKeywords = mainScanner.nextLine().trim();
              
                  boolean found = false;
                  for (Inquirer inquiry : allInquiries) {
                    if (inquiry.getFirstName().toLowerCase().contains(searchKeywords.toLowerCase())) {
                      System.out.println("Inquiry Found:");
                      System.out.println(inquiry.getFirstName() + inquiry.getLastName());
                      found = true;
                      break;
                    }
                  }
              
                  if (!found) {
                    System.out.println("No inquiries found matching the keywords.");
                  }
                } else if (inquiryChoice.equals("3")) {
                  // Display all Inquiries
                  if (allInquiries.isEmpty()) {
                    System.out.println("There are currently no inquiries in the system.");
                  } else {
                  System.out.println("List of all Inquiries:");
                    for (Inquirer inquiry : allInquiries) {
                        System.out.println(inquiry); // Assuming Inquirer has a toString method for formatted output
                      }
                    }
                  } else {
                    System.out.println("Invalid Inquiry choice. Please try again.");
                }
            }


            else{
                System.out.println("Invalid choice");
                System.out.println("Try again");
            }
        }
    }
}
