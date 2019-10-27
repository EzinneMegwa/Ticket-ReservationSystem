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

import java.util.*;

/**
 *
 * @author enmeg
 */
public class Order 
{
    protected int adultTix = 0;
    protected int childTix = 0;
    protected int seniorTix = 0;
    protected int auditoriumNum = 0;
    protected ArrayList<Seat> audiSeat = new ArrayList<Seat>();
    
    Order()
    {
        
    }
    Order(int aT, int cT, int sT, int audi)
    {
        adultTix = aT;
        childTix = cT;
        seniorTix = sT;
        auditoriumNum = audi;
    }
    //mutators
    public void setAdult(int adult){adultTix = adult;}
    public void setChild(int child){childTix = child;}
    public void setSenior(int senior){seniorTix = senior;}
    public void setAudi(int audiNum){auditoriumNum = audiNum;}
    
    //accessors
    public int getAdultTix(){return adultTix;}
    public int getChildTix(){return childTix;}
    public int getSeniorTix(){return seniorTix;}
    public int getAudiNum(){return auditoriumNum;}
}
