package edu.ucalgary.oop;
import java.util.Scanner;

public interface LogInquiry{

    // public void CaseDecider(){
    //     pass;
    // }

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

    default String setInquirerType(){
        System.out.println("Are you a central based or location based worker (central/location): ");
        String type = scannerLogInquiry.nextLine();
        return type;
    }

}
