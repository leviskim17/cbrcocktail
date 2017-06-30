package representation;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.extensions.textual.IE.opennlp.IETextOpenNLP;


public class CocktailDescription implements CaseComponent
{
    String name;
    String ingredient;

    IETextOpenNLP description;
    
    String price;
    String foodType;
    String breakfastDays;
    String lunchDays;
    String dinnerDays;
    String food;
    Boolean alcohol;
    Boolean takeout;
    Boolean delivery;
    Boolean parking;
    Boolean catering;
    
   
     
    public Attribute getIdAttribute()
    {
	return new Attribute("name",this.getClass());
    }

   
    public String getIngredient()
    {
        return ingredient;
    }

  
    public void setIngredient(String ingredient)
    {
        this.ingredient = ingredient;
    }

   
    public Boolean getAlcohol()
    {
        return alcohol;
    }

    
    public void setAlcohol(Boolean alcohol)
    {
        this.alcohol = alcohol;
    }

   
    public String getBreakfastDays()
    {
        return breakfastDays;
    }


    public void setBreakfastDays(String breakfastDays)
    {
        this.breakfastDays = breakfastDays;
    }

  
    public Boolean getCatering()
    {
        return catering;
    }

 
    public void setCatering(Boolean catering)
    {
        this.catering = catering;
    }

   
    public Boolean getDelivery()
    {
        return delivery;
    }

   
    public void setDelivery(Boolean delivery)
    {
        this.delivery = delivery;
    }

  
    public IETextOpenNLP getDescription()
    {
        return description;
    }

  
    public void setDescription(IETextOpenNLP description)
    {
        this.description = description;
    }

 
    public String getDinnerDays()
    {
        return dinnerDays;
    }


    public void setDinnerDays(String dinnerDays)
    {
        this.dinnerDays = dinnerDays;
    }

   
    public String getFood()
    {
        return food;
    }

   
    public void setFood(String food)
    {
        this.food = food;
    }

   
    public String getFoodType()
    {
        return foodType;
    }

  
    public void setFoodType(String foodType)
    {
        this.foodType = foodType;
    }



    
    public String getLunchDays()
    {
        return lunchDays;
    }

    
    public void setLunchDays(String lunchDays)
    {
        this.lunchDays = lunchDays;
    }

    
    public String getName()
    {
        return name;
    }

  
    public void setName(String name)
    {
        this.name = name;
    }

   
    public Boolean getParking()
    {
        return parking;
    }

   
    public void setParking(Boolean parking)
    {
        this.parking = parking;
    }

    


    
    public String getPrice()
    {
        return price;
    }

   
    public void setPrice(String price)
    {
        this.price = price;
    }

    
    public Boolean getTakeout()
    {
        return takeout;
    }

    
    public void setTakeout(Boolean takeout)
    {
        this.takeout = takeout;
    }

    public String toString()
    {
	StringBuffer sb = new StringBuffer();
	sb.append(this.name);
	sb.append(", ");
	sb.append(this.ingredient);
	sb.append(", ");
	
	sb.append(", ");

	sb.append(",");
	sb.append(this.description);
	
	return sb.toString();
    }
    
}
