import java.util.ArrayList;
import org.eclipse.swt.graphics.GC;

public class Row extends Glyph {
	
	private char c;
	private int width;
	private int height;
	
	private int length;
	
	private ArrayList<Glyph> glyphs;
	
	public Row() {
		
		// Non-Character glyphs should have a null character (for comparison)
		this.c = Character.MIN_VALUE;
		
		// Dimensions
		this.width = 0;
		this.height = 0;
		
		// Children
		this.length = 0;
		this.glyphs = new ArrayList<>();
	}
	
	public void add(Glyph g) {
		
		// Ensure height is equal to height of tallest child
		if (this.height < g.getHeight()) {
			this.height =  g.getHeight();
		}
		
		// Ensure width is equal to total width of all children
		this.width += g.getWidth();
		
		// Ensure length is equal to total number of children
		this.length += 1;
		
		glyphs.add(g);
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
		for (Glyph g: glyphs) {
			g.draw(gc, y, x);
			
			// Add column spacing
			y += 10; //TODO decide on spacing constant
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
