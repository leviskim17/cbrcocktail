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


	@Override
	public Attribute getIdAttribute()
		{
			return new Attribute("CaseId",this.getClass());
		} 

	public String toString()		{
			return "["+ CaseId +"]";
		}

}
