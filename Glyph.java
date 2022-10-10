import org.eclipse.swt.graphics.GC;

/**
 * Glyph
 */
public abstract class Glyph
{
	private char c;
	private int width;
	private int height;
	
	// Empty Constructor
	public Glyph() {}
	
	// Empty Draw method
	public void draw(GC gc, int x, int y) {};

	public char getChar() {
		return c;
	}
	
	public void setChar(char c) {
		this.c = c;
	}
	
	public abstract int getWidth();
	
	public abstract int getHeight();
}
