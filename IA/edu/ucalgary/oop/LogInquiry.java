package edu.ucalgary.oop;

import java.util.Scanner;

public interface LogInquiry{

    Scanner scannerLogInquiry = new Scanner(System.in);

    default String setFirstName(){

        System.out.print("Enter first name: ");
        String firstName = scannerLogInquiry.nextLine();

        return firstName;
        
    }

    default String setLastName(){
        System.out.print("Enter last name: ");
        String lastName = scannerLogInquiry.nextLine();
        return lastName;
    }

    default String setInfo(){
        System.out.print("Enter information about the inquiry: ");
        String info = scannerLogInquiry.nextLine();
        return info;
    }

    default String setServicePhone(){
        System.out.print("Enter the service's phone number: ");
        String servicePhone = scannerLogInquiry.nextLine();
        return servicePhone;
    }

    default String setPersonalPhone(){
        System.out.print("Enter your personal phone number: ");
        String personalPhone = scannerLogInquiry.nextLine();
        return personalPhone;
    }

    
    default void searchInquiry(String keywordString, String FIRST_NAME, String LAST_NAME, String INFO, String SERVICES_PHONE, String CALLER_PHONE_NUMBER) {
        keywordString = keywordString.toLowerCase(); // Convert keyword to lowercase for case-insensitive search

        boolean foundMatch = false;

        if (FIRST_NAME.toLowerCase().contains(keywordString) ||
            LAST_NAME.toLowerCase().contains(keywordString) ||
            INFO.toLowerCase().contains(keywordString) ||
            SERVICES_PHONE.toLowerCase().contains(keywordString) ||
            CALLER_PHONE_NUMBER.toLowerCase().contains(keywordString)) {
            foundMatch = true;
            // Handle the matched inquiry here (e.g., print details, perform actions)
        }

        if (foundMatch) {
            System.out.println("Based on the keywords, the search yielded this name: " + FIRST_NAME);
        }
    }
}