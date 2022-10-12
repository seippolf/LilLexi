import org.eclipse.swt.graphics.GC;

public class Shape extends Glyph {
	
	private char c;
	private int width;
	private int height;
	
	public Shape (int width, int height) {
		this.c = ' ';
		this.width = width;
		this.height = height;
	}
	
	public void draw(GC gc, int x, int y) {
		gc.drawRectangle(x, y, this.width, this.height);
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