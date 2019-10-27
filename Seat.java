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

/**
 *
 * @author enmeg
 */
public class Seat extends BaseNode
{
    int row;
    char seat;
    char tixType;
    
    Seat()
    {
        
    }
    Seat(int rowNum, char seatType, char ticket)
    {
        row = rowNum;
        seat = seatType;
        tixType = ticket;
    }
    
}
