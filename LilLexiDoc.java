/**
 * Lil Lexi Document Model
 * 
 */
import java.util.List;
import java.util.ArrayList;

/**
 * LilLexiDoc
 */
public class LilLexiDoc 
{
	private LilLexiUI ui;
	private List<Glyph> glyphs;
	
	/**
	 * Ctor
	 */
	public LilLexiDoc() 
	{
		glyphs = new ArrayList<Glyph>();
	}
	
	/**
	 * setUI
	 */
	public void setUI(LilLexiUI ui) {this.ui = ui;}

	
	/**
	 * add a char
	 */
	public void add(char c)
	{
		glyphs.add(new Char(c));
		ui.updateUI();
	}
	
//	/**
//	 * add a picture
//	 */
//	public void add(String path) {
//		glyphs.add(p);
//		ui.updateUI();
//	}
	
	public void addRow() {
		glyphs.add(new Row());
		ui.updateUI();
	}
	
	public void addShape(int width, int height) {
		glyphs.add(new Shape(width, height));
		ui.updateUI();
	}
	
	/**
	 * gets
	 */
	public List<Glyph> getGlyphs(){return glyphs;}
}









