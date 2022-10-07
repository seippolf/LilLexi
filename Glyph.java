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
	public void draw() {};

	public char getChar() {return c;}
	public void setChar(char c) {this.c = c;}
}
