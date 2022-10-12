import org.eclipse.swt.widgets.Display;

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
	 * add Char glyph
	 */
	void add(char c) 
	{	
		currentDoc.add(c);
	}
	
	void backspace() {
		currentDoc.remove();
	}
	
	/**
	 * add Picture glyph
	 */
	void addPicture(Display display, String path) {
		currentDoc.addPicture(display, path);
	}

	/**
	 * add shape glyph
	 */
	void addShape(int width, int height) {
		currentDoc.addShape(width, height);
	}
	
	/**
	 * quitEditor  user quits
	 */
	void  quitEditor() 
	{ 
		System.exit(0); 
	}	
}






