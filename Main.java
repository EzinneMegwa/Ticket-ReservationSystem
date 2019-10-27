/*
 * Author: Ezinne Megwa
 * Net ID: ENM170130
 * Date:
 * Class:
 * Program Description:
 * 
 * 
 * 
 * 
 * 
 */
package Tickets;

import java.io.*;
import java.util.*;

/**
 *
 * @author enmeg
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception
    {
        // TODO code application logic here
        String line = "";
        int index = 0;
        
        String username = "";
        String password = "";
        int passAttempts = 2;
        
        int userChoice = 0;
        int adminChoice = 0;
        int AudiChoice = 0;
        
        int rowChoice = 0;
        
        String seat = "";
        char seatChoice = 'A';
        
        int AdultTix = 0;
        int ChildTix = 0;
        int SeniorTix = 0;
        int totalTix = 0;
        
        int seatNumber = 0;
        String userAnswer = "";  
        
        boolean validChoice = true;
        boolean validLogin = true;        


        //Read in the username and pass file
        
        Scanner input = new Scanner(new File("userdb.dat"));
        Customer userObj;
        //Key: String Username, Value: String Customer Obj(Holds)
        Hashtable <String, Customer> userdb  = new Hashtable<String, Customer>();
        
        
        while(input.hasNext())//while there is still a line to read
        {
            line = input.nextLine();
            
            index = line.indexOf(" ");
            username = line.substring(0, index);
            password = line.substring(index+1);
            
            userObj = new Customer(username, password);
            
            userdb.put(username, userObj);
        }
        input.close();
        
        input = new Scanner(System.in);
        
        while(adminChoice != 3)
        {
        
            //Login In Prompt
            System.out.println("Hello! Welcome to the Theater Reservation System.");
            System.out.println("To begin reserving seats for your show, please login.");

            do //Do while loop for login validation
            {                        
                try
                {
                    System.out.print("Username:");        
                    username = input.nextLine();

                    System.out.print("Password:");
                    password = input.nextLine();

                    if(!userdb.containsKey(username) || !userdb.get(username).getPass().equals(password)) //if username is no int he map
                    {
                        validLogin = false;
                        throw new Exception("Login information is incorrect.");
                    }
                    else
                    {
                        validLogin = true;
                    }                                    
                }
                catch(Exception e)
                {                
                    if(!userdb.containsKey(username))
                    {
                        System.out.println("Username is not in the System. Please try again");
                        continue;                    
                    }
                    else if(!userdb.get(username).getPass().equals(password))
                    {                    
                        while(userdb.get(username).getPass() != password && passAttempts > 0)
                        {
                            //System.out.println("Pass is " + userdb.get(username).getPass());                        
                            System.out.println("Password is not a associated with this account.");
                            System.out.println("You have "+ passAttempts +" more attempts Please try again\nPassword:");
                            //input.nextLine();//clears buffer
                            password = input.nextLine();
                            passAttempts--;
                        }

                        if(passAttempts == 0)
                        {
                            System.out.println("You have no more attempts. Returning to Main Menu");
                            continue;
                        }
                    }
                }            
            }while(validLogin == false); //username does not match something in the hash map

            if(username.equals("admin"))
            {
                //Display Admin Screen
                //1.Print Report
                //2.Logout - return to main screen
                //3.Exit - end program
                
                System.out.println("Welcome " + username + " . Please make a selection:");
                System.out.println("1.Print Report\n2.Logout\n3.Exit");
                do{
                    try
                    {
                        adminChoice = input.nextInt();
                        if(adminChoice != 1 && adminChoice != 2 && adminChoice != 3)  //in the case of the input being not 1 or 2
                            throw new InputMismatchException(); //throw an exception
                        else    //else input is valid
                            validChoice = true;
                    }
                    catch(InputMismatchException Mm)    //WHat to do when your input is invalid
                    {
                        validChoice = false;    //input is not valid
                        System.out.println("Your input was invalid. Please select:\n1 to Print Report\n2 to Logout\n3 to Exit");
                        input.nextLine();//clear buffer to take in next input
                    }
                    
                }while(validChoice != true);
                
                if(adminChoice == 1)
                {
                    //Print Report
                }
                else if(adminChoice == 2)
                {
                    continue;
                }
                else if(adminChoice == 3)
                {
                    break;//breaks from the big loop
                }                
            }
            else //not an admin
            {
                //else do normal things
                //1Reserve Seat
                //2View Orders
                //3Update Order
                //4Display receipt
                //5Logout
                
                System.out.println("Welcome " + username + " . Please make a selection:");
                System.out.println("1.Reserve Seat\n2.ViewOrders\n3.Update Order\n4.Display Receipt\n5.Logout");
                do  //Do-While loop for the input handling
                {
                    try //Run code like normal, but it could throw an exception or error
                    {
                        userChoice = input.nextInt();   //take user input
                        input.nextLine();
                        if(userChoice != 1 && userChoice != 2 
                                && userChoice != 3 && userChoice != 4 && userChoice != 5)  //in the case of the input being not 1 or 2
                            throw new InputMismatchException(); //throw an exception
                        else    //else input is valid
                            validChoice = true;
                    }
                    catch(InputMismatchException Mm)    //WHat to do when your input is invalid
                    {
                        validChoice = false;    //input is not valid
                        System.out.println("Your input was invalid. Please select:\n1 to Reserve Seat\n2 to ViewOrders\n3 to Update Order\n4 to Display Receipt \n5 to Logout");
                        input.nextLine();//clear buffer to take in next input
                    }
                    
                }while(validChoice != true);    //keep looping till input
                
                //Create the 3 Auditoriums
                TheaterSeat bestAv = null;

                Auditorium a1 = null;   //Creates the Auditorium 1
                Auditorium a2 = null;   //Creates the Auditorium 2
                Auditorium a3 = null;   //Creates the Auditorium 3
                try //try file exceptions
                {
                    File A1 = new File("A1.txt");
                    a1 = new Auditorium(A1);

                    File A2 = new File("A2.txt");
                    a2 = new Auditorium(A2);

                    File A3 = new File("A3.txt");
                    a3 = new Auditorium(A3);
                }
                catch (IOException f)
                {
                    System.out.println("File not found");
                }
                
                
                if(userChoice == 1)
                {
                    //Reserve Seat
                    System.out.println("Choose an Auditorium");
                    System.out.println("1.Auditorium 1");
                    System.out.println("2.Auditorium 2");
                    System.out.println("3.Auditorium 3");
                                        
                    
                    do  //Do-While loop for the input handling
                    {
                    try //Run code like normal, but it could throw an exception or error
                    {
                        AudiChoice = input.nextInt();   //take user input
                        if(AudiChoice != 1 && AudiChoice != 2 && AudiChoice != 3)  //in the case of the input being not 1 or 2
                            throw new InputMismatchException(); //throw an exception
                        else    //else input is valid
                            validChoice = true;
                    }
                    catch(InputMismatchException Mm)    //WHat to do when your input is invalid
                    {
                        validChoice = false;    //input is not valid
                        System.out.println("Your input was invalid. Please select:\n1 to Reserve Seats \nor\n2 to Exit");
                        input.nextLine();//clear buffer to take in next input
                    }
                    
                    }while(validChoice != true);    //keep looping till input is valid
            
                    Auditorium audiPtr = null;//Pointer to make my life easier. Purpose, to point toe the user selected auditorium
                    
                    if(AudiChoice == 1)
                    {
                        audiPtr = a1;                        
                        
                    }
                    else if(AudiChoice == 2)
                    {
                        audiPtr = a2;
                    }
                    else if(AudiChoice == 3)
                    {
                        audiPtr = a3;
                    }
                    
                    
                    //Prompt for row

                    System.out.print("\nWhat row would you like? Enter row number: ");

                    //Input validation using try catch

                    do  //Do-While loop for the input handling
                    {
                        try //Run code like normal, but it could throw an exception or error
                        {
                            rowChoice = input.nextInt();    //Takes user input for row number
                            if(rowChoice > audiPtr.getRow() || rowChoice < 0)  //in case input is out of bounds
                                throw new InputMismatchException(); //throw an exception
                            else    //else input is valid
                                validChoice = true;
                        }
                        catch(InputMismatchException Mm)    //WHat to do when your input is invalid
                        {
                            validChoice = false;    //input is not valid
                            System.out.println("That is not a valid row. Please select a seat within the auditorium:");
                            input.nextLine();//clear buffer to take in next input
                        }

                    }while(validChoice != true);    //keep looping till input is valid




                    //Prompt for Starting Seat


                    System.out.print("What starting seat would you like? Enter seat letter: ");

                    //Input validation using try catch

                    do  //Do-While loop for the input handling
                    {
                        try //Run code like normal, but it could throw an exception or error
                        {
                            seat = input.next();    //Takes user input for seat letter
                            seatChoice = seat.charAt(0);    //convert to char
                            if(Character.isLowerCase(seatChoice))   //if lower, convert to upper
                            {
                                seatChoice = Character.toUpperCase(seatChoice);
                            }

                            if(seatChoice > (65+ audiPtr.getCol()) || seatChoice < 65)  //in case input is out of bounds
                                throw new InputMismatchException(); //throw an exception
                            else    //else input is valid
                                validChoice = true;
                        }
                        catch(InputMismatchException Mm)    //WHat to do when your input is invalid
                        {
                            validChoice = false;    //input is not valid
                            System.out.println("That is not a valid seat selection. Please select a seat within the auditorium:");
                            input.nextLine();//clear buffer to take in next input
                        }

                    }while(validChoice != true);    //keep looping till input is valid



                    //Prompt for number of adult tickets



                    System.out.print("How many Adult Tickets would you like? ");

                    //Input validation using try catch

                    do  //Do-While loop for the input handling
                    {
                        try //Run code like normal, but it could throw an exception or error
                        {
                            AdultTix = input.nextInt();    //Takes user input for num adultTix

                            validChoice = true;

    //                        if(AdultTix > a.getRow() || AdultTix < 0)  //in case input is out of bounds
    //                            throw new InputMismatchException(); //throw an exception
    //                        else    //else input is valid

                        }
                        catch(InputMismatchException Mm)    //WHat to do when your input is invalid
                        {
                            validChoice = false;    //input is not valid
                            System.out.println("Invalid entry or amount exceeds row length. Please enter a valid amount:");
                            input.nextLine();//clear buffer to take in next input
                        }

                    }while(validChoice != true);    //keep looping till input is valid




                    //Prompt for number of child tickets



                    System.out.print("How many Child Tickets would you like? ");

                    //Input validation using try catch

                    do  //Do-While loop for the input handling
                    {
                        try //Run code like normal, but it could throw an exception or error
                        {
                            ChildTix = input.nextInt();    //Takes user input for num childTix
                            validChoice = true;


    //                        if(ChildTix > a.getRow() || ChildTix < 0)  //in case input is out of bounds
    //                            throw new InputMismatchException(); //throw an exception
    //                        else    //else input is valid

                        }
                        catch(InputMismatchException Mm)    //WHat to do when your input is invalid
                        {
                            validChoice = false;    //input is not valid
                            System.out.println("Invalid entry or amount exceeds row length. Please enter a valid amount:");
                            input.nextLine();//clear buffer to take in next input
                        }

                    }while(validChoice != true);    //keep looping till input is valid


                    //Prompt for number of Senior tickets 



                    System.out.print("How many Senior Tickets would you like? ");

                    //Input validation using try catch

                    do  //Do-While loop for the input handling
                    {
                        try //Run code like normal, but it could throw an exception or error
                        {
                            SeniorTix = input.nextInt();    //Takes user input for num adultTix
                            validChoice = true;

    //                        if(SeniorTix > a.getRow() || SeniorTix < 0)  //in case input is out of bounds
    //                            throw new InputMismatchException(); //throw an exception
    //                        else    //else input is valid

                        }
                        catch(InputMismatchException Mm)    //What to do when your input is invalid
                        {
                            validChoice = false;    //input is not valid
                            System.out.println("Invalid entry or amount exceeds row length. Please enter a valid amount:");
                            input.nextLine();//clear buffer to take in next input
                        }

                    }while(validChoice != true);    //keep looping till input is valid

                    totalTix = AdultTix + ChildTix + SeniorTix;

                    seatNumber = (seatChoice + 1) - 65;

                    //If the user entered 0 for every ticket
                    if(totalTix == 0)
                    {
                        System.out.println("You didn't select any number of Tickets. Returning to Menu.");
                        continue;
                    }
                    //or if the total number of tickets is greater than the number of seats in a single row
                    else if(totalTix > audiPtr.getCol())
                    {
                        System.out.println("There is nothing available for you in the Auditorium. Retruning to Menu.");
                        continue;
                    }
                    //finding seatnum

                    if(audiPtr.checkAvailable(rowChoice, seatNumber, totalTix))
                    {
                        System.out.println("Seats are avalable"); 
                        audiPtr.reserveSeat(rowChoice, seatNumber, AdultTix, ChildTix, SeniorTix);
                        //a.displayReport();
                        System.out.println("Your seats have been reserved!"); 
                    }
                    else
                    {
                        System.out.println("\nSeat is not available for reservation"); 
                        //Call Best available
                        bestAv = audiPtr.bestAvailable(totalTix);
                        if(bestAv == null)
                        {
                         System.out.println("There are no seats available in the auditorium for you. Sorry.");
                        }
                        else
                        {
                            System.out.println("Here are the next best available seats!");
                            System.out.print("Row: " + bestAv.getRow() + " in Seats: ");
                            for(int x = 0; x < totalTix; x++)
                            {
                                System.out.print((char)(bestAv.getSeat() + x) + " ");
                            }
                            System.out.println("\nWould you like to reserve these seats?");

                            //input validation for yes or no                      
                           do  //Do-While loop for the input handling
                           {
                               try //Run code like normal, but it could throw an exception or error
                               {
                                   userAnswer = input.next();//userAnswer is being repurposed to hold the User's answer


                                   if(!userAnswer.equalsIgnoreCase("y") && !userAnswer.equalsIgnoreCase("n"))  //in case input is out of bounds
                                       throw new InputMismatchException(); //throw an exception
                                   else    //else input is valid
                                   {

                                       if(userAnswer.equalsIgnoreCase("y"))    //ignores letter case for input, If user says Yes
                                       {

                                           audiPtr.reserveSeat(bestAv.getRow(), ((bestAv.getSeat()+1)-65), AdultTix, ChildTix, SeniorTix);

                                           System.out.println("\nYour seats have been reserved!"); 
                                       }
                                       else if(userAnswer.equalsIgnoreCase("n"))    //else user says No
                                       {
                                           System.out.println("No seats have been reserved for you");
                                       }
                                       validChoice = true;  
                                   }
                               }
                               catch(InputMismatchException Mm)    //WHat to do when your input is invalid
                               {
                                   validChoice = false;    //input is not valid
                                   System.out.println("Invalid entry. Please select \"Y\" for Yes to reserve or \"N\" for no to reserve:");
                                   input.nextLine();//clear buffer to take in next input
                               }
                           }while(validChoice != true);    //keep looping till input is valid               
                        }                                                
                    }
                    
                }
                else if(userChoice == 2)
                {
                    //View Orders
                }
                else if(userChoice == 3)
                {
                    //Update Order
                }    
                else if(userChoice == 4)
                {
                    //Display Recipet
                }
                else if(adminChoice == 5)
                {
                    continue;// Goes back to main menu
                }    
            }        
        }        
        //Admin has exited the program
        //Do what needs to be done!
                              
        
    }
    
    
    
}
