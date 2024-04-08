package edu.ucalgary.oop;


public class Inquirer implements LogInquiry{
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private final String INFO;
    private final String SERVICES_PHONE;
    private final String CALLER_PHONE_NUMBER; // Log this to differentiate between inquirer objects

    public Inquirer() {

        this.FIRST_NAME = setFirstName();
        this.LAST_NAME = setLastName();
        this.INFO = setInfo();
        this.SERVICES_PHONE = setServicePhone();
        this.CALLER_PHONE_NUMBER = setPersonalPhone();

    }

    public String getFirstName() { return this.FIRST_NAME; }
    public String getLastName() { return this.LAST_NAME; }
    public String getServicesPhoneNum() { return this.SERVICES_PHONE; }
    public String getInfo() { return this.INFO; }
    public String getCallerPhoneNum() { return this.CALLER_PHONE_NUMBER; }
}   
