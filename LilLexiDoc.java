/**
 * Lil Lexi Document Model
 * 
 */
import java.util.List;

import org.eclipse.swt.widgets.Shell;

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
	public void add(char c, Shell shell)
	{
		glyphs.add(new Char(c, shell));
		ui.updateUI();
	}
	
	/**
	 * gets
	 */
	public List<Glyph> getGlyphs(){return glyphs;}
}









