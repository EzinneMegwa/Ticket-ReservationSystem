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
public class TheaterSeat extends BaseNode
{
    TheaterSeat Up = null;
    TheaterSeat Down = null;
    TheaterSeat Left = null;
    TheaterSeat Right = null;
    
    TheaterSeat(int row, char seat, boolean reserve, char tix)
    {
        super(row, seat, reserve, tix);
        
    }
    
    //Mutators
    public void setUp(TheaterSeat newNode){Up = newNode;}
    public void setDown(TheaterSeat newNode){Down = newNode;}
    public void setLeft(TheaterSeat newNode){Left = newNode;}
    public void setRight(TheaterSeat newNode){Right = newNode;}
    
    //Accessors
    public TheaterSeat getUp(){return Up;}
    public TheaterSeat getDown(){return Down;}
    public TheaterSeat getLeft(){return Left;}
    public TheaterSeat getRight(){return Right;}
    
}

