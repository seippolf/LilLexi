import org.eclipse.swt.graphics.GC;

/**
 * Glyph
 */
public abstract class Glyph
{
	public Glyph() {} // Empty constructor
	
	public abstract void draw(GC gc, int x, int y);

	public abstract char getChar();
	
	public abstract void setChar(char c);
	
	public abstract int getWidth();
	
	public abstract int getHeight();
}
