/**
 * Lil Lexi Document Model
 * 
 */
import java.util.List;

import org.eclipse.swt.widgets.Display;

import java.util.ArrayList;

/**
 * LilLexiDoc
 */
public class LilLexiDoc 
{
	private LilLexiUI ui;
	private List<Glyph> glyphs;

	private Row currentRow;
	
	/**
	 * Ctor
	 */
	public LilLexiDoc() 
	{
		glyphs = new ArrayList<Glyph>();
		currentRow = new Row();
		glyphs.add(currentRow);
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
		// If there are 40 items in the current row, make a new one
		if (currentRow.getLength() >= 40) {
			currentRow = new Row();
			glyphs.add(currentRow);
		}
		
		// Add char and update
		currentRow.add(new Char(c));
		System.out.println("Total Rows" + glyphs.size());
		ui.updateUI();
	}
	
	/**
	 * add a picture (displayed as block)
	 */
	public void addPicture(Display display, String path) {
		
		// Put a picture on new row
		currentRow = new Row();
		currentRow.add(new Picture(display, path));
		glyphs.add(currentRow);
		
		// Start on new row
		currentRow = new Row();
		glyphs.add(currentRow);
		ui.updateUI();
	}
	
	public void addRow() {
		glyphs.add(new Row());
		ui.updateUI();
	}
	
	public void addShape(int width, int height) {
		// Put a shape on new row
		currentRow = new Row();
		currentRow.add(new Shape(width, height));
		glyphs.add(currentRow);
		
		// Start on new row
		currentRow = new Row();
		glyphs.add(currentRow);
		ui.updateUI();
	}
	
	public void remove() {
		int glyphSize = glyphs.size();
		Row currentRow  = (Row) glyphs.get(glyphSize - 1);
		int currentRowLength = currentRow.getLength();
		
		// If the current row is empty...
		if (currentRow.getLength() == 0) {
			// Go back to the previous row if there is one
			if(glyphSize > 1) {
				glyphs.remove(glyphSize - 1);				
			}
		} else {
			// Remove most recent Glyph on document
			currentRow.remove(currentRowLength - 1);
		}
		
		ui.updateUI();
	}
	
	/**
	 * gets
	 */
	public List<Glyph> getGlyphs(){
		return glyphs;
	}
}









