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
public abstract class BaseNode  //Abstract class
{
    protected int Row = 0;
    protected char Seat;
    protected boolean Reserved = false;
    protected char TicketType;

    BaseNode(int r, char s, boolean res, char tixtype)
    {
        Row = r;
        Seat = s;
        Reserved = res;
        TicketType = tixtype;
    }
    BaseNode()
    {
        
    }
    public void setRow(int r){Row = r;}     //   
    public void setSeat(char seat){Seat = seat;}
    public void setReserved(boolean res){Reserved = res;}
    public void setTicketType(char tix){TicketType = tix;}
    
    public int getRow(){return Row;}     //   
    public char getSeat(){return Seat;}
    public boolean getReserved(){return Reserved;}
    public char getTicketType(){return TicketType;}
    
}
