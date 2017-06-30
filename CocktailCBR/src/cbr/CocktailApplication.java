
package cbr;

import java.util.Collection;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;
import jcolibri.extensions.textual.IE.opennlp.IETextOpenNLP;
import jcolibri.extensions.textual.lucene.LuceneIndex;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.LuceneTextSimilarity;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.test.main.SwingProgressBar;
import representation.CocktailDescription;

import connector.CocktailConnector;
import gui.ResultRetrive;



public class CocktailApplication implements StandardCBRApplication
{

    Connector _connector;
    CBRCaseBase _caseBase;

    LuceneIndex luceneIndex;
    
 
    public void configure() throws ExecutionException
    {
	try
	{
	    _connector = new CocktailConnector("config/ccc_cocktails.txt");
	    _caseBase = new LinealCaseBase();
	    
	    jcolibri.util.ProgressController.clear();
	    SwingProgressBar pb = new SwingProgressBar();
	    jcolibri.util.ProgressController.register(pb);   
	} catch (Exception e)
	{
	    throw new ExecutionException(e);
	}
    }

 
    public CBRCaseBase preCycle() throws ExecutionException
    {
	_caseBase.init(_connector);

	
	luceneIndex = jcolibri.method.precycle.LuceneIndexCreator.createLuceneIndex(_caseBase);
	
	return _caseBase;
    }

    public void cycle(CBRQuery query) throws ExecutionException
    {
	Collection<CBRCase> cases = _caseBase.getCases();
	
	NNConfig nnConfig = new NNConfig();
	nnConfig.setDescriptionSimFunction(new Average());
	
	
	Attribute textualAttribute = new Attribute("description", CocktailDescription.class);
	nnConfig.addMapping(textualAttribute, new LuceneTextSimilarity(luceneIndex,query,textualAttribute, true));

	
	System.out.println("RESULT: ");
	
	Collection<RetrievalResult> res = NNScoringMethod.evaluateSimilarity(cases, query, nnConfig);
	res = SelectCases.selectTopKRR(res, 5);
	
	for(RetrievalResult rr: res)
	    System.out.println(rr);
	
			CocktailDescription qrd = (CocktailDescription)query.getDescription();
	CBRCase mostSimilar = res.iterator().next().get_case();
	CocktailDescription rrd = (CocktailDescription)mostSimilar.getDescription();
	new ResultRetrive(qrd.getDescription().getRAWContent(), rrd.getName(), rrd.getIngredient(), rrd.getDescription().getRAWContent());

    }


    public void postCycle() throws ExecutionException
    {
	_connector.close();

    }

    
    public static void main(String[] args)
    {
    	CocktailApplication test = new CocktailApplication();
	try
	{
	    test.configure();
	    
	    CBRCaseBase caseBase = test.preCycle();
	   
	    System.out.println("CASE BASE: ");
	    for(CBRCase c: caseBase.getCases())
		System.out.println(c);
	    System.out.println("Total: "+caseBase.getCases().size()+" cases");

	    boolean _continue = true;
	    while(_continue)
	    {
        	    String queryString = javax.swing.JOptionPane.showInputDialog("Please enter the cocktail description:");
        	    if(queryString == null)
        		_continue = false;
        	    else
        	    {	
                	    CBRQuery query = new CBRQuery();
                	    CocktailDescription queryDescription = new CocktailDescription();
                	    queryDescription.setDescription(new IETextOpenNLP(queryString));
                	    query.setDescription(queryDescription);
                	    
                	    test.cycle(query);
        	    }
	    }
	    test.postCycle();
	    
	} catch (ExecutionException e)
	{
	    org.apache.commons.logging.LogFactory.getLog(CocktailApplication.class).error(e);
	}
    }
}
