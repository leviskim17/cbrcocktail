/**
 * HouseSolution.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 26/10/2007
 */
package jcolibri.test.recommenders.rec4.housesData;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

/**
 * Implements the house soluction
 * @author Juan A. Recio-Garcia
 * @author Developed at University College Cork (Ireland) in collaboration with Derek Bridge.
 * @version 1.0
 *
 */
public class HouseSolution implements CaseComponent
{
    Integer id;
    String address;
    Integer price;
    
    public String toString() 
    {
		return "("+id+";"+address+")";
    }
    
    public String getAddress()
    {
        return address;
    }



    public void setAddress(String address)
    {
        this.address = address;
    }



    public Integer getId()
    {
        return id;
    }



    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getPrice()
    {
        return price;
    }


    public void setPrice(Integer price)
    {
        this.price = price;
    }

    /* (non-Javadoc)
     * @see jcolibri.cbrcore.CaseComponent#getIdAttribute()
     */
    public Attribute getIdAttribute()
    {
	return new Attribute("id",this.getClass());
    }

}
