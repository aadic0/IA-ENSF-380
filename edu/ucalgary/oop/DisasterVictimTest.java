package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DisasterVictimTest {
    private DisasterVictim victim;
    private ArrayList<Supply> suppliesToSet = new ArrayList<>();
    private String expectedFirstName = "Freda";
    private String expectedEntryDate = "2024-01-18";
    private String validDateOfBirth = "1987-05-21";
    private String invalidDateOfBirth = "15/13/2024";
    private String expectedGender = "female";
    private String expectedComments = "Needs medical attention and speaks 2 languages";

    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, expectedEntryDate);
        suppliesToSet.add(new Supply("Water Bottle", 10));
        suppliesToSet.add(new Supply("Blanket", 5));
    }

    @Test
    public void testConstructorWithValidEntryDate() {
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", expectedEntryDate, victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        new DisasterVictim("Freda", "18/01/2024");
        // Expecting IllegalArgumentException due to invalid date format
    }

    @Test
    public void testSetAndGetDateOfBirth() {
        victim.setDateOfBirth(validDateOfBirth);
        assertEquals("setDateOfBirth should correctly update the date of birth", validDateOfBirth, victim.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        victim.setDateOfBirth(invalidDateOfBirth); // This format should cause an exception
    }

    @Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Alice";
        victim.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName, victim.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Smith";
        victim.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName, victim.getLastName());
    }

    @Test
    public void testGetComments() {
        victim.setComments(expectedComments);
        assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
    }

    @Test
    public void testSetComments() {
        victim.setComments(expectedComments);
        String newComments = "Has a minor injury on the left arm";
        victim.setComments(newComments);
        assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
    }

    @Test
    public void testGetAssignedSocialID() {
        DisasterVictim newVictim = new DisasterVictim("Kash", "2024-01-21");
        int expectedSocialId = newVictim.getAssignedSocialID() + 1;
        DisasterVictim actualVictim = new DisasterVictim("Adeleke", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId, actualVictim.getAssignedSocialID());
    }

    @Test
    public void testGetEntryDate() {
        assertEquals("getEntryDate should return the expected entry date", expectedEntryDate, victim.getEntryDate());
    }

    @Test
    public void testSetMedicalRecords() {
        MedicalRecord testRecord = new MedicalRecord(new Location("Shelter Z", "1234 Shelter Ave"), "test for strep", "2024-02-09");
        MedicalRecord[] records = {testRecord};
        victim.setMedicalRecords(records);
        assertTrue("setMedicalRecords should correctly update medical records", victim.getMedicalRecords().length > 0);
    }

    @Test
    public void testSetPersonalBelongings() {
        ArrayList<Supply> newSupplies = new ArrayList<>();
        Supply one = new Supply("Tent", 1);
        Supply two = new Supply("Jug", 3);
        newSupplies.add(one);
        newSupplies.add(two);
        victim.setPersonalBelongings(newSupplies);
        assertTrue("setPersonalBelongings should correctly update personal belongings", victim.getPersonalBelongings().size() > 0);
    }
}
