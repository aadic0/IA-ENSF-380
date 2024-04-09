package edu.ucalgary.oop;

import static org.junit.Assert.*;
import org.junit.*;

public class ReliefServiceTest {
    private Inquirer inquirer = new Inquirer("John", "Doe", "Wants help", "+1-123-456-7890", "+1-123-456-7890", "Central");
    private DisasterVictim missingPerson;
    private Location lastKnownLocation;

    @Before
    public void setUp() {
        

        missingPerson = new DisasterVictim("Jane", "2024-01-01");

        lastKnownLocation = new Location("Shelter A", "123 Main St");
    }

    @Test
    public void testSetAndGetInquirer() {
        ReliefService service = new ReliefService(null, missingPerson, "2024-02-01", "Info", lastKnownLocation);

        service.setInquirer(inquirer);
        assertEquals("John", service.getInquirer().getFirstName());
        assertEquals("Doe", service.getInquirer().getLastName());
        assertEquals("+1-123-456-7890", service.getInquirer().getCallerPhoneNum());
    }

    @Test
    public void testSetAndGetMissingPerson() {
        ReliefService service = new ReliefService(inquirer, null, "2024-02-01", "Info", lastKnownLocation);

        service.setMissingPerson(missingPerson);
        assertEquals("Jane", service.getMissingPerson().getFirstName());
        assertEquals("2024-01-01", service.getMissingPerson().getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidDateOfInquiry() {
        ReliefService service = new ReliefService(inquirer, missingPerson, "invalid date", "Info", lastKnownLocation);
        // This should throw an IllegalArgumentException
    }

    @Test
    public void testGetLogDetails() {
        ReliefService service = new ReliefService(inquirer, missingPerson, "2024-02-01", "Info", lastKnownLocation);

        String expectedLog = "Inquirer: John, Missing Person: Jane, Date of Inquiry: 2024-02-01, Info Provided: Info, Last Known Location: Shelter A";
        assertEquals(expectedLog, service.getLogDetails());
    }
}
