import org.eclipse.swt.widgets.Shell;

/**
 * Controller
 */
public class LilLexiControl 
{
	private LilLexiDoc currentDoc;

	/**
	 * LilLexiControl
	 */
	public LilLexiControl( LilLexiDoc doc )
	{
		this.currentDoc = doc;
	}
	
	/**
	 * selectCard  user selects a card
	 */
	void add( char c, Shell shell ) 
	{	
		currentDoc.add(c, shell);
	}	

	/**
	 * quitEditor  user quits
	 */
	void  quitEditor() 
	{ 
		System.exit(0); 
	}	
}






