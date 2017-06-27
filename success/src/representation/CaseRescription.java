package representation;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
 import colibristudio.annotations.JCOLIBRIAttributeType;


public class CaseRescription implements CaseComponent { 


	/* Generated Class. Please Do Not Modify... */ 


	private java.lang.String Step;


	public java.lang.String getStep()
		{
			return Step;
		}
	public void setStep(java.lang.String Step0)
		{
			this.Step = Step0;
		}

	private java.lang.String Ingredients;


	public java.lang.String getIngredients()
		{
			return Ingredients;
		}
	public void setIngredients(java.lang.String Ingredients1)
		{
			this.Ingredients = Ingredients1;
		}

	private java.lang.Integer CaseID;


	public java.lang.Integer getCaseID()
		{
			return CaseID;
		}
	public void setCaseID(java.lang.Integer CaseID2)
		{
			this.CaseID = CaseID2;
		}


	@Override
	public Attribute getIdAttribute()
		{
			return new Attribute("CaseID",this.getClass());
		} 

	public String toString()		{
			return "["+ Ingredients + " , " + Step + " , " + CaseID +"]";
		}

}
