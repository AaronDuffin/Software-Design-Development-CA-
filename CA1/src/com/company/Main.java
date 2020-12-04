package com.company;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner keyboard = new Scanner(System.in);
    // model is a special class called a singleton. It is only instanticated once.
    // get the model once here at the start of your application.
    // Then use model to ask it to model.addXXX(), model.removeXXX(), get etc.
    static Model model = Model.getInstance();

    public static void main(String[] args){

        Apartment p;
        int deleteId;

        int opt;
        do {
            System.out.println("\n\n********* MAIN MENU ********");
            System.out.println("1. Create new Apartment");
            System.out.println("2. View all Apartments");
            System.out.println("3. Update Apartment ");
            System.out.println("4. Delete Apartment ");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("*****Enter option: *******\n\n");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);
            // NOTE For above - slight different way of reading in an number, read the line as a String, then convert the String to an integer


            switch (opt) {

                // if the user choses option 1 create a programmer from user input and add it to the array list in Model.
                case 1: {
                    // ask the user for the programmer details, then create a programmer object p
                    p = readApartment();
                    // add the programmer object p to the model (ArrayList of programmers)
                    System.out.println(p);
                    boolean created = model.addApartment(p);
                    if (created)
                        System.out.println("***** Apartment Added to the Model *****");
                    else
                        System.out.println("***** Apartment not Added to the Model *****");

                    break;
                }

                // if the user choses option 2 get the array list of programmers from the model and display them all on screen.
                case 2: {
                    // this method will call the model to get the list of programmers and display them to the screen.
                    viewApartment();
                    break;
                }

                case 3: {
                    // ask the user for the programmer details, then create a programmer object p
                    p = updateApartment();
                    // add the programmer object p to the model (ArrayList of programmers)
                    System.out.println(p);
                    boolean created = model.updateApartment(p);
                    if (created)
                        System.out.println("***** Apartment Updated *****");
                    else
                        System.out.println("***** Apartment not Updated *****");

                    break;
                }

                case 4: {
                    // ask the user for the programmer details, then create a programmer object p
                    deleteId = deleteApartment();
                    // add the programmer object p to the model (ArrayList of programmers)
                    System.out.println(deleteId);
                    boolean created = model.deleteApartment(deleteId);
                    if (created)
                        System.out.println("***** Apartment Deleted *****");
                    else
                        System.out.println("***** Apartment not Deleted *****");

                    break;
                }
            }
        }
        while (opt != 5);
        System.out.println("Goodbye");
    }

    // reads the details from the user, creates a Programmer object and returns this object to the calling program
    private static Apartment readApartment() {
        String address;
        double cost;
        String bedroomNumber;
        String maintenance;
        boolean parkingSpace;



        // GetSting is a helper method created below to make the code a bit neater.
        // It is the similar to the two lines of code - System.out.println("Enter XXX")...readLine()

        System.out.print("Enter Address : ");
        address = keyboard.nextLine();

        // this line reads in the double variable - salary which the user types, however the "ENTER" is still sitting in the buffer
        System.out.print("Enter cost : ");
        cost = keyboard.nextDouble();
        keyboard.nextLine();

        System.out.print("Enter Bedroom Number : ");
        bedroomNumber = keyboard.nextLine();

        System.out.print("Enter Maintenance : ");
        maintenance = keyboard.nextLine();

        System.out.print("Enter Parking Space : ");
        parkingSpace = keyboard.nextBoolean();
        keyboard.nextLine();
        // this nextLine() swallows up the carraige return (ENTER) that is sitting in the buffer from when the user typed in the salary then hits enter.




        // Create the Apartments object p
        Apartment p =
                new Apartment(address, cost, bedroomNumber,
                        maintenance, parkingSpace);

        //return the Apartments object p to the calling method
        return p;
    }


    // gets the Apartments array list from the model and displays it.
    private static void viewApartment() {

        // ask the model for the list of programmers
        List<Apartment> apartments = model.getApartments();

        System.out.println("***** Printing All Apartments *****");
        // display the list of programmers
        for (Apartment ap : apartments) {
            System.out.println(ap);
        }

        System.out.println("***** Finished Printing All Apartments *****");
    }

    private static Apartment updateApartment(){
        int id;
        String address;
        double cost;
        String bedroomNumber;
        String maintenance;
        boolean parkingSpace;



        // GetSting is a helper method created below to make the code a bit neater.
        // It is the similar to the two lines of code - System.out.println("Enter XXX")...readLine()

        System.out.print("Enter ID of the Apartment you want to update : ");
        id = keyboard.nextInt();
        keyboard.nextLine();

        System.out.print("Enter Address : ");
        address = keyboard.nextLine();

        // this line reads in the double variable - salary which the user types, however the "ENTER" is still sitting in the buffer
        System.out.print("Enter cost : ");
        cost = keyboard.nextDouble();
        keyboard.nextLine();

        System.out.print("Enter Bedroom Number : ");
        bedroomNumber = keyboard.nextLine();

        System.out.print("Enter Maintenance : ");
        maintenance = keyboard.nextLine();

        System.out.print("Enter Parking Space : ");
        parkingSpace = keyboard.nextBoolean();
        keyboard.nextLine();
        // this nextLine() swallows up the carraige return (ENTER) that is sitting in the buffer from when the user typed in the salary then hits enter.




        // Create the Apartments object p
        Apartment p =
                new Apartment(id, address, cost, bedroomNumber,
                        maintenance, parkingSpace);

        //return the Apartments object p to the calling method
        return p;

    }

    private static int deleteApartment(){
        int id;

        System.out.print("Enter ID of the Apartment you want to delete : ");
        id = keyboard.nextInt();
        keyboard.nextLine();

        return id;

    }

}

