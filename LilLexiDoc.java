/**
 * Lil Lexi Document Model
 * 
 */
import java.util.List;

import org.eclipse.swt.graphics.Image;
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
	
	private Glyph undoneGlyph;
	
	
	/**
 * Ctor
	 */
	public LilLexiDoc() {
		this.glyphs = new ArrayList<Glyph>();
		this.currentRow = new Row();
		this.glyphs.add(currentRow);
		this.undoneGlyph = null;
	}
	
	/**
	 * setUI
	 */
	public void setUI(LilLexiUI ui) {
		this.ui = ui;
	}

	/**
	 * add a char
	 */
	public void add(char c) {
		// If there are 40 items in the current row, make a new one
		if (this.currentRow.getLength() >= 40) {
			this.currentRow = new Row();
			this.glyphs.add(currentRow);
		}
		
		// Add char and update
		this.currentRow.add(new Char(c));
		System.out.println("Total Rows" + this.glyphs.size());
		this.ui.updateUI();
	}
	
	/**
	 * add a picture (displayed as block)
	 */
	public void addPicture(Display display, Image image){
		this.currentRow.add(new Picture(display, image));
	}
	
	/**
	 * Add row and set to current
	 */
	public void addRow() {
		Row r = new Row();
		this.currentRow = r;
		glyphs.add(r);
		ui.updateUI();
	}
	
	/**
	 * Add a shape (displayed as block)
	 */
	public void addShape(int width, int height) {
		this.currentRow.add(new Shape(width, height));
	}
	
	/**
	 * Remove last Glyph and return it
	 * 
	 * @return removed Glyph
	 */
	public Glyph remove() {
		int glyphSize = glyphs.size();
		int currentRowLength = this.currentRow.getLength();
		Glyph returnGlyph = null;
		
		// If the current row is empty...
		if (this.currentRow.getLength() == 0) {
			// Go back to the previous row if there is one
			if(glyphSize > 1) {
				returnGlyph = this.glyphs.get(glyphSize - 1);
				this.glyphs.remove(glyphSize - 1);
				
				// Go back to previous row
				this.currentRow = (Row) this.glyphs.get(glyphSize - 2);
			}
		} else {
			// Remove most recent Glyph on document
			returnGlyph = currentRow.get(currentRowLength -1);
			this.currentRow.remove(currentRowLength - 1);
		}
		
		this.ui.updateUI();
		return returnGlyph;
	}
	
	/**
	 * Undo last Glyph
	 */
	public void undo() {
		// Store most recent
		this.undoneGlyph = 	this.remove();
	}
	
	/**
	 * Redo last Glyph if undone
	 */
	public void redo() {
		// Make sure there is something to redo
		if (this.undoneGlyph != null) {
			// If you undo a row creation, make a new row
			if(this.undoneGlyph instanceof Row) {
				this.addRow();
				
			} else if (this.undoneGlyph instanceof Char){
				this.currentRow.add(undoneGlyph);
				
			} else {
				this.currentRow.add(undoneGlyph);
			}
			
			this.undoneGlyph = null;			
		}
	}
	
	/**
	 * gets
	 */
	public List<Glyph> getGlyphs(){
		return glyphs;
	}
	
	public int getDocHeight() {
		return this.glyphs.size();
	}
}









