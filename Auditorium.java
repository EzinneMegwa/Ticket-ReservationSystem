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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author enmeg
 */
public class Auditorium 
{
   TheaterSeat First = null;    //the head of the linked list
   
   protected int numRow = 0; //the number of rows in the auditorium
   protected int numCol = 0; // the number of columns in the auditorium
    
   //Constructor
   Auditorium(File f) throws IOException // Takes in a File
    {
        //Reads from the file 
        
        Scanner input = new Scanner(f);
        int rowCounter = 0; //counts the iterations, which is the number of rows
        
        String rowSeats = "";   //String to read the line
        
        while(input.hasNext())  //reads through the file
        {
            numRow++;   //counts the number of rows
            rowSeats = input.nextLine(); //reads each row
        }
        input.close();//closes the file
        
        numCol = rowSeats.length();  //the number of columns in the auditorium
        
        input = new Scanner(f);   //reopens the file
        
        while(input.hasNext())
        {
            rowSeats = input.nextLine(); //reads the line
            
            for(int i = 0; i < numCol; i++) //loop through the seats in a row/ line from the file
            {
                if(First == null) //is the first node not pointing at anything?
                {
                    
                    if(rowSeats.charAt(0) != '.')//if the first cahracter in the row is not '.' , aka not empty
                    {
                        First = new TheaterSeat(rowCounter + 1, 'A', true, rowSeats.charAt(0));
                    }
                    else    //else it is empty
                    {
                        First = new TheaterSeat(rowCounter + 1, 'A', false, rowSeats.charAt(0));
                    }
                    
                    rowSeats = rowSeats.substring(1);

                } 
                else //else the list is not empty
                {                    
                    TheaterSeat newnode;    //create a theater seat object
                    
                    if(rowSeats.charAt(0) != '.')//if the first cahracter in the row is not '.' , aka not empty, reserved is true
                    {
                        newnode = new TheaterSeat(rowCounter + 1, (char)('A' + i), true, rowSeats.charAt(0));
                    }
                    else    //else it is empty, reserved is false
                    {
                        newnode = new TheaterSeat(rowCounter + 1, (char)('A' + i), false, rowSeats.charAt(0));
                    }

                    //add the node
                    addSeat(newnode, i);
                    rowSeats = rowSeats.substring(1);

                }
            } 
            rowCounter++; //increments the number of rows (off by one since starting at 0)
        }
        input.close();
        
    }
   
   public int getCol(){return numCol;}  // returns the number of seats in the auditorium
   public int getRow(){return numRow;}  //returns the number of rows in the auditorium
   
   
   /*
   Function: addSeat
   Purpose: This functions adds seats to the auditorium grid
   Param: Takes in a node and the column number (meaning the individual seat in a row, ex: seat 2A, 2B, 2C)
   */
   public void addSeat(TheaterSeat node, int colNumber)
   {
       TheaterSeat rowTravA = First;
       TheaterSeat rowTravC = First;
       TheaterSeat begRowA = First;
       TheaterSeat begRowC = First;
       
       if(begRowA.getRight() == null && colNumber == 0 )//meaning, if we are at the the head, first seat and first row (upper left corner)
       {

            begRowA.setRight(node);
            node.setLeft(rowTravA);

           
       }
       else if(colNumber == 0)  //meaning we are at the first seat in rown, but not in the first row
       {           
           while(begRowC.getDown() != null)
           {
               begRowC = begRowC.getDown();
               begRowA = begRowC.getUp();
           }
           if(begRowA == begRowC) //if current pointer is still on the first row, then there is no 2nd row, so...
           {
               begRowA.setDown(node);   //set the Down of the first seat in the first row (aka First) to the new node
               node.setUp(begRowA); //set the up of the node to first
               //and Ta-Da, we now have the beginning of the second row
           }
           else //else the current beginning pointer ended up moving
           {
               rowTravC = begRowC;
               rowTravC.setDown(node);
               node.setUp(rowTravC);
               
           }
           
       }
       else //not on the first column
       {
           if(begRowC.getDown() == null)//the first row
           {
                while(rowTravC.getRight() != null)  //while ptr for Current row next is not null
                {
                    rowTravC = rowTravC.getRight(); //move to the right 
                }

                rowTravC.setRight(node);  //reached the end of the list, so set the last node to the new one
                node.setLeft(rowTravC); //doble link back to the prev
           }
           else //not the first row
           {
               while(begRowC.getDown() != null)    //while not at the bottom row...
               {
                   begRowC = begRowC.getDown();   //move the current beginning pointer
                   begRowA = begRowC.getUp();   //move the above beginnine pointer to make sure it stays pointing to the row above current
               }
               //while loop ends when you have reached the bottom row
               
               rowTravC = begRowC; //move the pointer for the current row traversal to the beginning o the current row
               rowTravA = begRowA;
               
               while(rowTravC.getRight() != null)
               {
                   rowTravC = rowTravC.getRight();
                   rowTravA = rowTravA.getRight();
               }
               rowTravC.setRight(node);
               node.setLeft(rowTravC);
               node.setUp(rowTravA.getRight());
               rowTravA.getRight().setDown(node);
               
               
           }
       }
       
       
       
       
   }
   public void Print()
   {
       TheaterSeat ptrA = First;
       TheaterSeat begA = First;
       TheaterSeat begC = begA.getDown();
       
       System.out.print("    ");
       
       //Prints the letters for the seats
       for(int i = 0; i < getCol(); i++)
       {
           System.out.print((char)('A' + i));
       }
       System.out.println();
       
       
      
       
//        
//        
//        //FIXME
//        
//        System.out.print("    ");
//        for(int i = 0; i < getCol(); i++)
//       {
//           System.out.print((i+1)%10);
//       }
//       System.out.println();
//       
//       
//       
//       
       
       
       
       int r = 0;
       
       //Prints the row number
       System.out.printf("%2d %1s", r + 1, "");
       while(ptrA.getDown() != null || ptrA.getRight() != null)
       {
           if(ptrA.getReserved())
           {
               System.out.print("#");
           }
           else
           {
               System.out.print(ptrA.getTicketType());
           }
           
           if(ptrA.getRight() != null)
           {
               ptrA = ptrA.getRight();               
           }
           else
           {
               r++;
               System.out.println();
               //System.out.print(r+1 + " ");
               System.out.printf("%2d %1s", r + 1, "");
               ptrA = begC;
               begA = ptrA;
               begC = begA.getDown();
           }
           
           
       }
       
       //Checking the last seat (Lower right seat)
       //The while loop does not account for it, so it must be checked and counted separately
       if(ptrA.getReserved() == true)
       {
           System.out.println("#");
       }
       else
       {
           System.out.println(ptrA.getTicketType());
       }
       
       
       
   }
   
   
   
   
   
   
   
    /*displayReport function
    Purpose: To display all the theater report include ticket sales and amount of each ticket type
    Returns: nothing
    */
   public void displayReport()
   {
       
       int countA = 0;
       int countC = 0;
       int countS = 0;
       int countEmpty = 0;
       int countTotal= 0;
       int totalSold = 0;
       double sales = 0;
       

       TheaterSeat ptrA = First;
       TheaterSeat begA = First;
       TheaterSeat begC = begA.getDown();
       
       
       while(ptrA.getDown() != null || ptrA.getRight() != null)
       {
           
            if(ptrA.getTicketType() == 'A')
            {
                
                countA++;
            }
            else if(ptrA.getTicketType() == 'C')
            {
                countC++;
            }
            else if(ptrA.getTicketType() == 'S')
            {
                countS++;
            }
            else if(ptrA.getTicketType()== '.')
            {
                countEmpty++;
            }            

           if(ptrA.getRight() != null)
           {
               ptrA = ptrA.getRight();               
           }
           else
           { 
               ptrA = begC;
               begA = ptrA;
               begC = begA.getDown();
           }
           
       }
       
       //Checking the last seat (Lower right seat)
       //The while loop does not account for it, so it must be checked and counted separately
        if(ptrA.getTicketType() == 'A')
        {
            countA++;
        }
        else if(ptrA.getTicketType() == 'C')
        {
            countC++;
        }
        else if(ptrA.getTicketType() == 'S')
        {
            countS++;
        }
        else if(ptrA.getTicketType() == '.')
        {
            countEmpty++;
        }
        
        
        
        
       countTotal = countA + countC + countS + countEmpty;
       
       
       sales = (countA*10) + (countC * 5) + (countS * 7.50);   //calculates total sales

       
       totalSold = countA + countC  + countS;  //calculates the total tickets sold
        
        //Prints and formats the output
        
        DecimalFormat ft = new DecimalFormat("$###,##0.00");
        
        //System.out.println("Total seats: " + countTotal);
        System.out.printf("%-15s %10d\n", "Total seats: " , countTotal);
        //System.out.println("Total sold: " + totalSold);
        System.out.printf("%-15s %10d\n", "Total sold: " , totalSold);
        //System.out.println("Adult sold: " + countA);
        System.out.printf("%-15s %10d\n", "Adult sold: " , countA);
        //System.out.println("Child sold: " + countC);
        System.out.printf("%-15s %10d\n", "Child sold: " , countC);
        //System.out.println("Senior sold: " + countS);
        System.out.printf("%-15s %10d\n", "Senior sold: " , countS);
        //System.out.printf("Total sales: %.2f" + ft.format(sales));
        System.out.printf("%-15s", "Total sales: ");
        System.out.printf("%11s", "" + ft.format(sales));
        System.out.println();
       
       
   }
   
   
   
    public void toFile() throws IOException
   {
       TheaterSeat ptrA = First;
       TheaterSeat begA = First;
       TheaterSeat begC = begA.getDown();
       
       PrintWriter output = new PrintWriter (new File ("A1.txt"));
       
       while(ptrA.getDown() != null || ptrA.getRight() != null)
       {
           output.print(ptrA.getTicketType());
           if(ptrA.getRight() != null)
           {
               ptrA = ptrA.getRight();               
           }
           else
           {
               output.println();
               ptrA = begC;
               begA = ptrA;
               begC = begA.getDown();
           }
           
           
       }
       output.println(ptrA.getTicketType());
       
       output.close();
       
       
   }
    
    
    
    /*checkAvailable function
    Purpose: Goes through auditorium row to check for available seat, marked by a '.'
    Param: int row selection, starting seat selection, tickets of each type
    Returns: nothing
    */
    public boolean checkAvailable(int rowNum, int seatNum, int numTix)
    {
        //Ptr to the first Seat 1A
        TheaterSeat ptrSeat = First;
        
        //Move ptr to the selected seat fromt he user        
   
        for(int r = 1; r < rowNum; r++)
        {
            ptrSeat = ptrSeat.getDown();        
        }
        for(int c = 1; c < seatNum; c++)
        {
            ptrSeat = ptrSeat.getRight();            
        }
        
        //Pointer should at this point be in the beginning selected seat
        //While the ptrs it no moved into null
        while(ptrSeat != null)
        {
            if(ptrSeat.getReserved() == true) //check each seat, if the seat is reserved,, then seats are not available
            {
                return false;//return false
            }
            else    //else the seat is available
            {

                ptrSeat = ptrSeat.getRight();   //move to the right to begin checking the other seats to see if they are available
                
                numTix--;   //decrement for each seat you have checked is for sure available
                if(numTix == 0) //if numTix reaches Zero, all the desired seats have been checked
                    return true;//return true, all seats are available
            }
            
        }
        //Security if statement to catch whether all the seats were available
        //If the ptrSeat enters null, then seats lef bounds of the auditorium, menaing seats were not available
        if(numTix == 0)
        {
            return true;
        }
        else    //they were not all available
        {
            return false;
        }
       
    }
    
     /*reserveSeat function
    Purpose: Goes through auditorium to reserves seats for each ticket, replacing dots with A,C,or S
    Param: int row, seat, tickets of each type
    Returns: nothing
    */
    public void reserveSeat(int rowNum, int seatNum, int adultTix, int childTix, int seniorTix)
    {               
        //Ptr to the first Seat 1A
        TheaterSeat ptrSeat = First;
        int a = 0;
        int c = 0;
        int s = 0;
        
        //Move ptr to the selected seat
        
        
        for(int row = 1; row < rowNum; row++)
        {
            ptrSeat = ptrSeat.getDown();            
        }
        for(int col = 1; col < seatNum; col++)
        {
            ptrSeat = ptrSeat.getRight();            
        }
               
        //Pointer should at this point be in the beginning selected seat
        //Begin reserving those seats
        for(a = 0; a < adultTix; a++)
        {
            //goes through auditorium to reserve adult seats
            ptrSeat.setTicketType('A');
            ptrSeat.setReserved(true);
            ptrSeat = ptrSeat.getRight();
        }
        for(c = 0; c < childTix; c++)
        {
            //goes trhough auditorium to reserve child seats after adult seats
            ptrSeat.setTicketType('C');
            ptrSeat.setReserved(true);
            ptrSeat = ptrSeat.getRight();
        }
        for(s = 0; s < seniorTix; s++)
        {
            //goes throught auditorium to reserve senior seats after adult and child seats
            ptrSeat.setTicketType('S');
            ptrSeat.setReserved(true);
            ptrSeat = ptrSeat.getRight();
        }
  
        
    }
    
    /*bestAvailable function
      purpose: To find the best available seat in the theater closest to the middle of the auditorium
      Param:  int number of tickets user wants
      Return: the starting bestSeat node
    */
    
    public TheaterSeat bestAvailable(int numTix)
    {
        
        TheaterSeat startingCur = First;
        TheaterSeat rowBHead = First;   //this is the row below current
        TheaterSeat rowTrav = First;
        TheaterSeat bestSeat = null;
        TheaterSeat middleSeat = First;
        
        int discCounter = 0;
        double distance = 0;
        double tempDis = 0;
        double varA = 0;
        double varB = 0;
        
        
        
        
        //middle most row and center of a row
        double middleRow = getRow() /2.0;    //finds the middle row
        double rowCenter = getCol()/2.0;     //finds the center of the row
        double centerSeat = 0.0;
        
        //If the number of rows in auditorium is even...
        if(getRow()%2 == 0)
        {
            //the middle row in the theater is the number of rows/2 then the ceiling of the number plus 0.5
            middleRow = (Math.ceil((getRow()/2.0))) + 0.5;
        }
        else //else number of rows in theater is odd
        {
            middleRow = Math.ceil(getRow()/2.0);
            
        }
        
        //if the number of columns in the theater is even...
        if(getCol()%2 == 0)
        {
            //the center of a row is the number of Columns
            //(because remember, the number of columns indicate
            //the number of seats in a single row)
            
            rowCenter = (getCol()/2.0) + 0.5;
        }
        else
        {
            rowCenter = getCol()/2.0;
            
        }
        
        //best row in the house aka closest to the middle
        int bestRow = 0;
        

        for(int i = 1; i <= getRow(); i++) //Loop goes through every row in the auditorium
            {
                rowBHead = startingCur.getDown(); //rowBelow current head
                
                while(startingCur != null)  //while the starting seat of the traversal is not null...
                {
                    int j = 0;
                    
                    rowTrav = startingCur;//traverse through the row
                    middleSeat = startingCur;//middle seat of the desires number of seats starts at the beginning of the available seats
                    
                    for(j = 0; j < numTix; j++) //traverse through the number of tickets the user wants
                    {
                        if(rowTrav == null)//if you reach the end of the row aka null...
                        {
                            break;
                        }
                        else if(rowTrav.getReserved() == true)//else check if set is reserved
                        {
                            break;  //break if seat is reserved
                        }
                        rowTrav = rowTrav.getRight();   //if seat is not reserved, keep traversing
                    }
                    if(j == numTix) //the inner for loop completeed every iteration without breaking, so all seats were available
                    {
                        //Move to the middle seat of the best number of seats you found
                        for(int m = 0; m < (Math.floor(numTix/2.0)); m++)
                        {
                            middleSeat = middleSeat.getRight();
                        }
                        
                        //If the number of desired seats is even
                        if(numTix%2 == 0)
                        {
                            //This is the center of the desired seats
                            centerSeat = ((double)(middleSeat.getSeat()+1)-65) - 0.5;
                            
                        }
                        else //else odd
                        {
                            //This is the center of the desired seats
                            centerSeat = ((double)(middleSeat.getSeat()+1)-65);
                        }
                        //If this is the first time calculating distance...
                        if(discCounter == 0)
                        {
                            //Calculate the distance using pythagorean therom
                            varA = Math.abs(middleRow - (i));                            
                            varB = Math.abs(rowCenter - (centerSeat));
                            distance = Math.sqrt((varA * varA)+(varB * varB));
                            distance = Math.abs(distance);
                            bestSeat = startingCur;
                            bestRow = i;
                            discCounter++;                               
                        }
                        else    //else this is the nth time calculating disctance, meaning we have something to compare to now
                        {
                            
                            //Calculate the distance using pythagorean therom
                            varA = Math.abs(middleRow - (i));
                            varB = Math.abs(rowCenter - (centerSeat));
                            tempDis = Math.sqrt((varA * varA)+(varB * varB));
                            tempDis = Math.abs(tempDis);
                            //System.out.println("Temp Distance: " + tempDis + "\tDistance is: " + distance);
                            
                            if(tempDis < distance)  //compare the distance
                            {
                                distance = tempDis; //keep the smallest distance
                                bestSeat = startingCur; //keep the bestSeat node
                                bestRow = i;//keep the best row                                
                            }
                            //else if the distances are the same...
                            else if(Double.compare(tempDis, distance) == 0)
                            {
                                //Find which row is closer, the middle row or the last recorded bestRow
                                //if the currect row is closer...
                                if(Math.abs(middleRow - i) < Math.abs(middleRow - bestRow))
                                {
                                    bestRow = i;//record the current row as bestRow
                                    bestSeat = startingCur;//record the BestSeat
                                }
                                //else if the current row and the last recorded best row is the same distance...
                                else if(Math.abs(middleRow - i) == Math.abs(middleRow - bestRow))
                                {
                                    //if i (the current row) is the smaller row number...
                                    if(i < bestRow)
                                    {
                                        bestRow = i;//make current the new best row
                                        bestSeat = startingCur;//update bestSeat
                                    }                                    
                                }
                            }
                        }
                    }
                    
                    startingCur = startingCur.getRight();   //reset your starting from current row pointer
                }
                startingCur = rowBHead;//move your current row head pointer down a row
            }       
        return bestSeat;    //return a bestRow, function will return null if there is no best seat in theater
    }
   
}