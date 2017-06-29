/**
 * HouseDescription.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 26/10/2007
 */
package representation;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

/**
 * Implements the house description.
 * @author Juan A. Recio-Garcia
 * @version 1.0
 *
 */
public class CocktailDescription implements CaseComponent
{
    public enum Food_2  {one2,two2,three2,four2,five2,six2,seven2};
    public enum Food_3  {one3,two3,three3,four3,five3,six3,seven3};
    public enum Food_1  {one1,two1,three1,four1,five1,six1,seven1};
    Integer id;
    Food_1 food_choose_1;
    Food_2 food_choose_2;
    Food_3 food_choose_3;
    
    public String toString() 
    {
    	return "("+id+";"+food_choose_1+";"+food_choose_2+";"+food_choose_3+")";
    }


    public Food_1 getFood_1()
    {
        return food_choose_1;
    }


    public void setFood_1(Food_1 food_1)
    {
        this.food_choose_1 = food_1;
    }



    public Food_2 getFood_2()
    {
        return food_choose_2;
    }


    public void setFood_2(Food_2 food_2)
    {
        this.food_choose_2 = food_2;
    }

    public Integer getId()
    {
        return id;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public Food_3 getFood_3()
    {
        return food_choose_3;
    }


    public void setFood_3(Food_3 food_3)
    {
        this.food_choose_3 = food_3;
    }



    public Attribute getIdAttribute()
    {
	return new Attribute("id",this.getClass());
    }

}
