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
public class Customer 
{
    protected String userName = "";
    protected String password = "";
    protected ArrayList<Order> cusOrder = new ArrayList<Order>();
    Customer()
    {
        
    }
    Customer(String user, String pass)
    {
        userName = user;
        password = pass;
    }
    
    //mutators
    public void setUser(String uName){userName = uName;}
    public void setPass(String pWord){password = pWord;}
    
    //accessors
    public String getUser(){return userName;}
    public String getPass(){return password;}
    
    
    
    
    
}
