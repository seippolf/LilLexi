import org.eclipse.swt.graphics.GC;

public class Char extends Glyph {
	
	private char c;
	private int width;
	private int height;

	
	public Char(char c) {
		this.c = c;
		this.height = 0;
		this.height = 0;
	}
	
	public void draw(GC gc, int x, int y) {
		gc.drawString("" + c, x, y);
	}

	/**
	 * Return character
	 * 
	 * @return character
	 */
	public char getChar() {
		return this.c;
	}
	
	/**
	 * Set character to c
	 * 
	 * @param c
	 */
	public void setChar(char c) {
		this.c = c;
	}

	@Override
	public  int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
}
