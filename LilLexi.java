import java.io.FileNotFoundException;

/*
 * AUTHOR: Justin Johnson & Jasmine Ying
 * FILE: LilLexi.java
 * ASSIGNMENT: Lil Lexi
 * COURSE: CSC 335; Fall 2022
 */

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


