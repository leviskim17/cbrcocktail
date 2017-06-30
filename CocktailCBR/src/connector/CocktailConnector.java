/**
 * RestaurantsConnector.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 23/06/2007
 */
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
//import jcolibri.test.test13.RestaurantDescription;

import representation.CocktailDescription;

/**
 * Obtains cases from a simple txt file. This connector is thought to work with txt files that contain
 * descriptions of restaurants following the format:
 * <pre>
 * NAME
 * ADDRESS
 * LOCATION
 * PHONE
 * DESCRIPTION
 * AUTHOR_OF_DESCRIPTION
 * 
 * 
 * NAME
 * ADDRESS
 * ...
 * </pre>
 * This connector only reads cases but does not write anything to the text file.
 * @author Juan A. Recio-Garcia
 * @version 1.0
 *
 */
public class CocktailConnector implements Connector
{
    private URL file;
    public CocktailConnector(String sourceFile)
    {
	file = jcolibri.util.FileIO.findFile(sourceFile);
    }

    /* (non-Javadoc)
     * @see jcolibri.cbrcore.Connector#close()
     */
    public void close()
    {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see jcolibri.cbrcore.Connector#deleteCases(java.util.Collection)
     */
    public void deleteCases(Collection<CBRCase> cases)
    {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see jcolibri.cbrcore.Connector#initFromXMLfile(java.net.URL)
     */
    public void initFromXMLfile(URL file) throws InitializingException
    {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see jcolibri.cbrcore.Connector#retrieveAllCases()
     */
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

    /* (non-Javadoc)
     * @see jcolibri.cbrcore.Connector#retrieveSomeCases(jcolibri.cbrcore.CaseBaseFilter)
     */
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter)
    {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see jcolibri.cbrcore.Connector#storeCases(java.util.Collection)
     */
    public void storeCases(Collection<CBRCase> cases)
    {
	// TODO Auto-generated method stub

    }

}
