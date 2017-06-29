package representation;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
 import colibristudio.annotations.JCOLIBRIAttributeType;


public class CaseDescription implements CaseComponent { 


	/* Generated Class. Please Do Not Modify... */ 


	private java.lang.Integer CaseId;


	public java.lang.Integer getCaseId()
		{
			return CaseId;
		}
	public void setCaseId(java.lang.Integer CaseId0)
		{
			this.CaseId = CaseId0;
		}

	private java.lang.String Recipe;


	public java.lang.String getRecipe()
		{
			return Recipe;
		}
	public void setRecipe(java.lang.String Recipe1)
		{
			this.Recipe = Recipe1;
		}

	private java.lang.String Ingredient;


	public java.lang.String getIngredient()
		{
			return Ingredient;
		}
	public void setIngredient(java.lang.String Ingredient2)
		{
			this.Ingredient = Ingredient2;
		}

	private java.lang.String Step;


	public java.lang.String getStep()
		{
			return Step;
		}
	public void setStep(java.lang.String Step3)
		{
			this.Step = Step3;
		}


	@Override
	public Attribute getIdAttribute()
		{
			return new Attribute("CaseId",this.getClass());
		} 

	public String toString()		{
			return "["+ Step + " , " + Recipe + " , " + CaseId + " , " + Ingredient +"]";
		}

}
