import org.eclipse.swt.graphics.Image;
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
	 * Curse forward
	 */
	void next() {
		
	}
	
	/**
	 * Curse backward
	 */
	void prev() {
		
	}
	
	/**
	 * add Char glyph
	 */
	void add(char c) 
	{	
		currentDoc.add(c);
	}
	
	/**
	 * remove last Glyph
	 */
	void backspace() {
		currentDoc.remove();
	}
	
	/**
	 * undo last Glyph
	 */
	void undo() {
		currentDoc.undo();
	}
	
	/**
	 * redo last Glyph, if undone
	 */
	void redo() {
		currentDoc.redo();
	}
	
	/**
	 * add Picture glyph
	 */
	void addPicture(Display display, Image image) {
		currentDoc.addPicture(display, image);
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






