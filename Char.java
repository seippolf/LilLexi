import java.awt.Font;

import org.eclipse.swt.graphics.GC;

public class Char extends Glyph {
	
	private char c;
	private int width;
	private int height;
	
	public Char(char c) {
		this.c = c;
		this.width = 0;
		this.height = 20;
	}
	
	public void draw(GC gc, int x, int y) {
		gc.drawString("" + c, x, y);
	}
		
	@Override
	public  int getWidth() {
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
