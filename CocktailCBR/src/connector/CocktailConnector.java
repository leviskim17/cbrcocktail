package connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CaseBaseFilter;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.InitializingException;
import jcolibri.extensions.textual.IE.opennlp.IETextOpenNLP;

import representation.CocktailDescription;


public class CocktailConnector implements Connector
{
    private URL file;
    public CocktailConnector(String sourceFile)
    {
	file = jcolibri.util.FileIO.findFile(sourceFile);
    }

   
    public void close()
    {
	// TODO Auto-generated method stub

    }

   
    public void deleteCases(Collection<CBRCase> cases)
    {
	// TODO Auto-generated method stub

    }

    
    public void initFromXMLfile(URL file) throws InitializingException
    {
	// TODO Auto-generated method stub

    }

    
    public Collection<CBRCase> retrieveAllCases()
    {
	Collection<CBRCase> res = new ArrayList<CBRCase>();
	
	try
	{
	    BufferedReader br = new BufferedReader( new InputStreamReader(file.openStream()));
	    String line = "";
	    while ((line = br.readLine()) != null)
	    {
	    	CocktailDescription cocktail  = new CocktailDescription();
	    	cocktail.setName(line);
	    	cocktail.setIngredient(br.readLine());
	    
	    
	    	cocktail.setDescription(new IETextOpenNLP(br.readLine()));
		br.readLine();
		br.readLine();
		br.readLine();
		CBRCase _case = new CBRCase();
		_case.setDescription(cocktail);
		res.add(_case);
		
	    }
	    br.close();
	} catch (IOException e)
	{
	    org.apache.commons.logging.LogFactory.getLog(this.getClass()).error(e);
	    
	}
	
	return res;
    }

  
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter)
    {
	// TODO Auto-generated method stub
	return null;
    }

    public void storeCases(Collection<CBRCase> cases)
    {
	// TODO Auto-generated method stub

    }

}
