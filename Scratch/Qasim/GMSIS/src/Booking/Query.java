/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Booking;

/**
 *
 * @author qhzrv
 */
public class Query {
    
    private final String myQuery;
    private final int type;
    
    public Query(int setType, String setQuery)
    {
        myQuery = setQuery;
        type = setType;
    }
    
    public int getType()
    {
        return type;
    }
    
    public String getQuery()
    {
        return myQuery;
    }
}
    
    
    

