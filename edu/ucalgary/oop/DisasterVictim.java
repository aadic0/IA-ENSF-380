package edu.ucalgary.oop;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;

public class DisasterVictim implements EnterDisasterVictim {
    private static int counter = 0;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private final int ASSIGNED_SOCIAL_ID;
    private Set<FamilyRelation> familyConnections; // kept as a set to avoid duplicates
    private List<DisasterVictim> interFamilyRelations; // used to check for missing relationships that we should have
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
    private ArrayList<Supply> personalBelongings;
    private final String ENTRY_DATE;
    private String gender;
    private String comments;
    private String age; // has logic in the setters and getters that prevents both dob and age coexisting


    public DisasterVictim(String firstName, String ENTRY_DATE) { // for testing
        this.firstName = firstName;
        if (!isValidDateFormat(ENTRY_DATE)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.ENTRY_DATE = ENTRY_DATE;
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
        this.dietaryRestrictions = new HashSet<>();
    }


    public DisasterVictim(){ // Using interface

        this.firstName = enterDisasterVictimFirstName();
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
        String testDate = enterDisasterVictimDate();
        if (!isValidDateFormat(testDate)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.ENTRY_DATE = testDate;
    }



    private static int generateSocialID() {
        counter++;
        return counter;
    }

    private static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(dateFormatPattern);
    }

    // Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (getAge() == null){
            if (!isValidDateFormat(dateOfBirth)) {
                throw new IllegalArgumentException("Invalid date format for date of birth. Expected format: YYYY-MM-DD");
            }
            this.dateOfBirth = dateOfBirth;
        }
    }

    public String getAge(){
        return age;
    }

    public void setAge(String age){
        if (getDateOfBirth() != null){
            this.age = age;
        }
    }

    public int getAssignedSocialID() {
        return ASSIGNED_SOCIAL_ID;
    }

  public FamilyRelation[] getFamilyConnections() {
        return familyConnections.toArray(new FamilyRelation[0]);
    }

    public MedicalRecord[] getMedicalRecords() {
        return medicalRecords.toArray(new MedicalRecord[0]);
    }

    public ArrayList<Supply> getPersonalBelongings() {
        return this.personalBelongings;
    }

    // The add and remove methods remain correct.

    // Correct the setters to accept Lists instead of arrays
public void setFamilyConnections(FamilyRelation[] connections) {
    this.familyConnections.clear();
    for (int i = 0; i < connections.length; i++) {
        FamilyRelation newRecord = connections[i];
        newRecord.getPersonOne().addFamilyConnection(newRecord.getPersonTwo(), newRecord.getRelationshipTo());
    }
}


    public void setMedicalRecords(MedicalRecord[] records) {
        this.medicalRecords.clear();
        for (MedicalRecord newRecord : records) {
            addMedicalRecord(newRecord);
        }
    }

    public void setPersonalBelongings(ArrayList<Supply> belongings) {
        this.personalBelongings = belongings;
    }

    // Add a Supply to personalBelonging
    public void addPersonalBelonging(Supply supply) {

        personalBelongings.add(supply);
    
        }


    // Remove a Supply from personalBelongings, we assume it only appears once
    public void removePersonalBelonging(Supply unwantedSupply) {
        personalBelongings.remove(unwantedSupply);
    }

    public void removeFamilyConnection(FamilyRelation exRelation) {
        familyConnections.remove(exRelation);
    }

    // Bidirectional linking to ensure consistency
    public void addFamilyConnection(DisasterVictim person, String relationshipTo) {
        // SUPPORTS PARENT/CHILD, SIBLING/SIBLING, HUSBAND/WIFE, SPOUSE/SPOUSE
        if (interFamilyRelations.contains(person)){ return; } // Case where relationship already exists

        FamilyRelation relation = new FamilyRelation(this, relationshipTo, person);

        if (!familyConnections.add(relation)){ // Cool little piece of code that checks if it can be added to a set (its unique in that case) or if its a duplicate (sets cant contain duplicates)
            return;
        }

        interFamilyRelations.add(person);
        person.interFamilyRelations.add(this);
        familyConnections.add(relation);
        person.addFamilyConnection(this, generateInverseRelationship(relationshipTo));
    }


    private String generateInverseRelationship(String relationshipTo){
        switch (relationshipTo.toLowerCase()) {
            case "parent":
                return "child";
            case "sibling":
                return "sibling";
            case "husband":
                return "wife";
            case "wife":
                return "husband";
            case "spouse":
                return "spouse";
            default:
                return "";
        }
    }

    // Add a MedicalRecord to medicalRecords
    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    public String getEntryDate() {
        return ENTRY_DATE;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments =  comments;
    }

    public String getGender() {
        return gender;
    }

    private Set<String> loadGenderOptions() throws IOException {
        Set<String> options = new HashSet<>();
        Path filePath = Paths.get("GenderOptions.txt"); // Assuming file is in the same directory

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Remove leading/trailing whitespace
                if (!line.isEmpty()) {
                    options.add(line.toLowerCase()); // Store options in lowercase for case-insensitive comparison
                }
            }
        }

        return options;
    }

    public void setGender(String gender) throws IllegalArgumentException, IOException {
        if (!gender.matches("(?i)^(other)$")) { // Allow "other" without file validation
            Set<String> validGenderOptions;
            try {
                validGenderOptions = loadGenderOptions(); // Load options, handle potential IOException
            } catch (IOException e) {
                throw new IllegalArgumentException("Error loading gender options: " + e.getMessage(), e); // Re-throw as IllegalArgumentException with cause
            }
            if (!validGenderOptions.contains(gender.toLowerCase())) {
                throw new IllegalArgumentException("Invalid gender. Please choose from options in GenderOptions.txt or 'other'.");
            }
        }
        this.gender = gender.toLowerCase(); // Store in a consistent format
    }

    private Set<DietaryRestriction> dietaryRestrictions; // Set to store dietary restrictions

    public void addDietaryRestriction(DietaryRestriction restriction) {
        dietaryRestrictions.add(restriction);
    }

    public void removeDietaryRestriction(DietaryRestriction restriction) {
        dietaryRestrictions.remove(restriction);
    }

    public Set<DietaryRestriction> getDietaryRestrictions() {
        return Collections.unmodifiableSet(dietaryRestrictions); // Return unmodifiable set to prevent external modification
    }

    // New enumeration for dietary restrictions
    public enum DietaryRestriction {
        AVML("Asian vegetarian meal"),
        DBML("Diabetic meal"),
        GFML("Gluten intolerant meal"),
        KSML("Kosher meal"),
        LSML("Low salt meal"),
        MOML("Muslim meal"),
        PFML("Peanut-free meal"),
        VGML("Vegan meal"),
        VJML("Vegetarian Jain meal");

        private final String description;

        DietaryRestriction(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

}
