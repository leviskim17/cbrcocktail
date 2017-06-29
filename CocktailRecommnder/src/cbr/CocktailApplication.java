/**
 * Houses1.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 23/10/2007
 */
package cbr;

import java.util.Collection;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.PlainTextConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.gui.formFilling.ObtainQueryWithFormMethod;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Table;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.InrecaLessIsBetter;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.McSherryMoreIsBetter;
import jcolibri.method.retrieve.selection.SelectCases;
import recolibry.conditionals.BuyOrQuit;
import representation.CocktailDescription;
import jcolibri.util.gui.DisplayCasesTableMethod;
import jcolibri.util.gui.UserChoice;

/**
 * Simple Single-Shot flats recommender using form-filling, Nearest Neighbour retrieval and top k selection .
 * <br>
 * This recommender allows user to define his/her requirements using a form. 
 * Then, it retrieves the k most similar cases using Nearest Neighbour retrieval.
 * These k cases are displayed to the user in a table and the system finishes.
 * <br>Summary:
 * <ul>
 * <li>Type: Single-Shot
 * <li>Case base: houses
 * <li>One off Preference Elicitation: Form Filling with initial values
 * <li>Retrieval: NearestNeigbour + topKselection
 * <li>Display: In table
 * </ul>
 * This recommender implements the following template:<br>
 * <center><img src="../Template1_Cycle.jpg"/></center>
 * 
 * <br>Read the documentation of the recommenders extension for details about templates
 * and recommender strategies: {@link jcolibri.extensions.recommendation}
 * 
 * @see jcolibri.method.gui.formFilling.ObtainQueryWithFormMethod
 * @see jcolibri.method.retrieve.NNretrieval.NNScoringMethod
 * @see jcolibri.method.retrieve.selection.SelectCases
 * @see jcolibri.extensions.recommendation.casesDisplay.DisplayCasesTableMethod
 * 
 * @author Juan A. Recio-Garcia
 * @author Developed at University College Cork (Ireland) in collaboration with Derek Bridge.
 * @version 1.0
 *
 */
public class CocktailApplication implements StandardCBRApplication
{
    /** Connector object */
    Connector _connector;
    /** CaseBase object */
    CBRCaseBase _caseBase;

    /** KNN config */
    NNConfig simConfig;
    
    public void configure() throws ExecutionException
    {
	// Create a data base connector
	_connector = new PlainTextConnector();
	// Init the ddbb connector with the config file
//	_connector.initFromXMLfile(jcolibri.util.FileIO
//			.findFile("jcolibri/test/recommenders/housesData/plaintextconfig.xml"));
	_connector.initFromXMLfile(jcolibri.util.FileIO
			.findFile("config/plaintextconfig.xml"));
	// Create a Lineal case base for in-memory organization
	_caseBase = new LinealCaseBase();
	
	simConfig = new NNConfig();
	// Set the average() global similarity function for the description of the case
	simConfig.setDescriptionSimFunction(new Average());
//	simConfig.addMapping(new Attribute("area", HouseDescription.class), new Table("jcolibri/test/recommenders/housesData/area.csv"));

	simConfig.addMapping(new Attribute("area", CocktailDescription.class), new Table("config/area.csv"));

	simConfig.addMapping(new Attribute("beds", CocktailDescription.class), new McSherryMoreIsBetter(0,0));
	simConfig.addMapping(new Attribute("price", CocktailDescription.class), new InrecaLessIsBetter(2000, 0.5));
	simConfig.addMapping(new Attribute("furnished", CocktailDescription.class), new Equal());
	simConfig.addMapping(new Attribute("type", CocktailDescription.class), new Equal());
	simConfig.addMapping(new Attribute("baths", CocktailDescription.class), new McSherryMoreIsBetter(7,1));


    }

    public void cycle(CBRQuery query) throws ExecutionException
    {
	// Obtain query
	ObtainQueryWithFormMethod.obtainQueryWithInitialValues(query,null,null);
	
	// Execute KNN
	Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
	
	// Select cases
	Collection<CBRCase> retrievedCases = SelectCases.selectTopK(eval, 5);
	
	// Display cases
	UserChoice choice = DisplayCasesTableMethod.displayCasesInTableBasic(retrievedCases);//DisplayCasesMethod.displayCases(retrievedCases);

	// Buy or Quit
	if(BuyOrQuit.buyOrQuit(choice))
	    System.out.println("Finish - User Buys: "+choice.getSelectedCase());
	
	else
	    System.out.println("Finish - User Quits");

    }

    public void postCycle() throws ExecutionException
    {
	_connector.close();
    }

    public CBRCaseBase preCycle() throws ExecutionException
    {
	// Load cases from connector into the case base
	_caseBase.init(_connector);		
	// Print the cases
	java.util.Collection<CBRCase> cases = _caseBase.getCases();
	for(CBRCase c: cases)
		System.out.println(c);
	return _caseBase;
    }
    

    public static void main(String[] args) {
	
	StandardCBRApplication recommender = new CocktailApplication();
	try
	{
	    recommender.configure();
	    
	    recommender.preCycle();
	    
	    CBRQuery query = new CBRQuery();
	    
	    CocktailDescription cd = new CocktailDescription();
	    cd.setArea(CocktailDescription.Area.Hampstead);
	    cd.setBaths(1);
	    cd.setBeds(CocktailDescription.Beds.two);
	    cd.setFurnished(true);
	    cd.setPrice(500);
	    cd.setType(CocktailDescription.Type.Flat);
	    query.setDescription(cd);
	    
	    recommender.cycle(query);
	    
	    recommender.postCycle();
	    
	} catch (Exception e)
	{
	    org.apache.commons.logging.LogFactory.getLog(CocktailApplication.class).error(e);
	    
	}

    }

}