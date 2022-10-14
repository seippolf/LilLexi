import java.io.FileNotFoundException;

/**
 * Lil Lexi Document Editor
 * 
 */

public class LilLexi
{
	static private LilLexiDoc currentDoc = null;

	// File Exception due to dictionary.txt
	public static void main(String args[]) throws FileNotFoundException
	{
		if (currentDoc == null)
			currentDoc = new LilLexiDoc();
		LilLexiUI lexiUI = new LilLexiUI();
		lexiUI.setCurrentDoc( currentDoc );
		currentDoc.setUI(lexiUI);
		
		LilLexiControl lexiControl = new LilLexiControl( currentDoc );
		lexiUI.setController( lexiControl );
		
		lexiUI.start();
	} 
}


