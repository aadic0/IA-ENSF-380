package edu.ucalgary.oop;
import java.util.ArrayList;

public class Inquirer implements LogInquiry{
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private String INFO;
    private final String SERVICES_PHONE;
    private final String CALLER_PHONE_NUMBER; // Log this to differentiate between inquirer objects
    private final String INQUIRER_TYPE; // Can be set to central or location 
    private ArrayList<String> INFO_HISTORY;


    public Inquirer() {
        this.FIRST_NAME = setFirstName();
        this.LAST_NAME = setLastName();
        this.INFO = setInfo();
        this.SERVICES_PHONE = setServicePhone();
        this.CALLER_PHONE_NUMBER = setPersonalPhone();
        this.INQUIRER_TYPE = setInquirerType();
        INFO_HISTORY.add(INFO);
    }

    // this constructor is for testing
    public Inquirer(String fname, String lname, String info, String services_phone, String caller_phone, String type) {
        FIRST_NAME = fname;
        LAST_NAME = lname;
        INFO = info;
        SERVICES_PHONE = services_phone;
        CALLER_PHONE_NUMBER = caller_phone;
        INQUIRER_TYPE = type;
        INFO_HISTORY.add(info);
    }

    public void setInfo(String info){
        this.INFO = info;
        INFO_HISTORY.add(info);
    }

    public String getFirstName() { return this.FIRST_NAME; }
    public String getLastName() { return this.LAST_NAME; }
    public String getServicesPhoneNum() { return this.SERVICES_PHONE; }
    public String getInfo() { return this.INFO; }
    public String getCallerPhoneNum() { return this.CALLER_PHONE_NUMBER; }
    public String getInquirerType() { return this.INQUIRER_TYPE; }
    public ArrayList<String> getInfoHistory() { return this.INFO_HISTORY; }

}
