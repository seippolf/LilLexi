import java.util.ArrayList;
import org.eclipse.swt.graphics.GC;

/*
 * AUTHOR: Justin Johnson & Jasmine Ying
 * FILE: Row.java
 * ASSIGNMENT: Lil Lexi
 * COURSE: CSC 335; Fall 2022
 */

public class Row extends Glyph {
	
	private char c;
	private int width;
	private int height;
	
	private int length;
	
	private ArrayList<Glyph> glyphs;
	
	public Row() {
		
		// Rows should have a null character (for comparison)
		this.c = '\n';
		
		// Dimensions
		this.width = 0;
		this.height = 0;
		
		// Children
		this.length = 0;
		this.glyphs = new ArrayList<>();
	}
	
	/**
	 * Add a Glyph to list in row
	 * 
	 * @param g
	 */
	public void add(Glyph g) {
		
		// Ensure height is equal to height of tallest child
		if (this.height < g.getHeight()) {
			this.height =  g.getHeight();
		}
		
		// Ensure width is equal to total width of all children
		this.width += g.getWidth();
		
		// Ensure length is equal to total number of children
		this.length += 1;
		
		this.glyphs.add(g);
	}
	
	/**
	 * Remove Glyph at passed index reconfigure height
	 *  and reduce size
	 * 
	 * @param index
	 */
	public void remove(int index) {
		if (this.length >= index +1) {
			this.glyphs.remove(index);
			this.length--;		
		}
	}
	
	/**
	 * Return Glyph at index
	 * 
	 * @param index
	 * @return Glyph
	 */
	public Glyph get(int index) {
		return this.glyphs.get(index);
	}
	
	/**
	 * Return number of children
	 * 
	 * @return number of children
	 */
	public int getLength() {
		return this.length;
	}
	
	@Override
	public void draw(GC gc, int y, int x) {
		for (Glyph g: this.glyphs) {
			
			// Always check for height changes before drawing
			this.height = 0;
			if (g.getHeight() > this.height) {
				this.height = g.getHeight();
			}
			
			g.draw(gc, y, x);
			
			// Add column spacing
			y += 15; //TODO decide on spacing constant
		}			
	}
	
	@Override
	public int getWidth() {
		return this.width;
	}
	
	@Override
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public char getChar() {
		return this.c;
	}
		
	@Override
	public void setChar(char c) {
		this.c = c;
	}
	

}
