package edu.ucalgary.oop;
import java.util.Scanner;
import java.util.ArrayList;

public interface EnterDisasterVictim {

    Scanner scanner = new Scanner(System.in);

    default String enterDisasterVictimFirstName() {
        System.out.print("Enter first name: ");
        return scanner.nextLine();
    }

    default String enterDisasterVictimDate() {
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        return scanner.nextLine();
    }

    default ArrayList<MedicalRecord> enterMedicalInformation(Location location) {
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        boolean done = false;
    
        while (!done) {
            System.out.print("Enter medical record details (location, treatment details, date of treatment, separated by spaces, or 'done' to finish): ");
            String details = scanner.nextLine();
    
            if (!details.equalsIgnoreCase("done")) {
                String[] recordParts = details.split(" ", 3); // Split into max 3 parts
    
                // Input validation and handling
                if (recordParts.length != 3) {
                    System.out.println("Invalid input format. Please enter location, treatment details, and date of treatment separated by spaces.");
                    continue;
                }
    
                String recordLocation = recordParts[0];
                String treatmentDetails = recordParts[1];
                String dateOfTreatment = recordParts[2];
    
                try {
                    // Validate location based on existing locations (assuming validation logic)
                    if (recordLocation != "Foothills Hospital" || recordLocation != "Childrens Hospital") {
                        System.out.println("Invalid medical record location. Please enter a valid location like Foothills Hospital or Childrens Hospital.");
                        continue;
                    }
    
                    // Validate date format within MedicalRecord constructor (assuming validation exists)
                    medicalRecords.add(new MedicalRecord(location, treatmentDetails, dateOfTreatment));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format for treatment details. Please enter YYYY-MM-DD.");
                }
            } else {
                done = true;
            }
        }
    
        return medicalRecords;
    }
    

    default ArrayList<FamilyRelation> enterFamilyRelationship(ArrayList<DisasterVictim> disasterVictims) {
        ArrayList<FamilyRelation> familyRelations = new ArrayList<>();
        boolean done = false;

        while (!done) {
            System.out.println("Available disaster victims:");
            // Assuming you have a way to display the list of victims in a user-friendly format
            // ... (display available victims using disasterVictims)

            System.out.print("Enter victim 1 ID: ");
            int victim1ID = scanner.nextInt();
            scanner.nextLine(); // Consume newline after integer input

            System.out.print("Enter relationship (e.g., parent, child, sibling, spouse): ");
            String relationship = scanner.nextLine();

            System.out.print("Enter victim 2 ID: ");
            int victim2ID = scanner.nextInt();
            scanner.nextLine(); // Consume newline after integer input

            // Find DisasterVictim objects based on IDs
            DisasterVictim personOne = null;
            DisasterVictim personTwo = null;
            for (DisasterVictim victim : disasterVictims) {
                if (victim.getAssignedSocialID() == victim1ID) {
                    personOne = victim;
                } else if (victim.getAssignedSocialID() == victim2ID) {
                    personTwo = victim;
                }
            }

            // Check if both victims are found
            if (personOne == null || personTwo == null) {
                System.out.println("Invalid victim ID. Please enter existing victim IDs.");
                continue;
            }

            // Create and add FamilyRelation object
            familyRelations.add(new FamilyRelation(personOne, relationship, personTwo));

            System.out.print("Add another relationship? (y/n): ");
            done = !scanner.nextLine().equalsIgnoreCase("y");
        }

        return familyRelations;
    }

}
