import org.eclipse.swt.graphics.GC;

/*
 * AUTHOR: Justin Johnson & Jasmine Ying
 * FILE: Glyph.java
 * ASSIGNMENT: Lil Lexi
 * COURSE: CSC 335; Fall 2022
 */

/**
 * Glyph
 */
public abstract class Glyph {
	public Glyph() {} // Empty constructor
	
	public abstract void draw(GC gc, int x, int y);

	public abstract char getChar();
	
	public abstract void setChar(char c);
	
	public abstract int getWidth();
	
	public abstract int getHeight();	
}
