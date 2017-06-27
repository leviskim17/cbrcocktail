package representation;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
 import colibristudio.annotations.JCOLIBRIAttributeType;


public class CaseSolution implements CaseComponent { 


	/* Generated Class. Please Do Not Modify... */ 


	private java.lang.Integer caseID;


	public java.lang.Integer getCaseID()
		{
			return caseID;
		}
	public void setCaseID(java.lang.Integer caseID3)
		{
			this.caseID = caseID3;
		}

	private java.lang.String Result;


	public java.lang.String getResult()
		{
			return Result;
		}
	public void setResult(java.lang.String Result4)
		{
			this.Result = Result4;
		}


	@Override
	public Attribute getIdAttribute()
		{
			return new Attribute("caseID",this.getClass());
		} 

	public String toString()		{
			return "["+ Result + " , " + caseID +"]";
		}

}
