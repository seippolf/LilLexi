import java.awt.Canvas;
import java.util.ArrayList;

public class Row extends Glyph {
	
	private Canvas c;
	private int row;
	private int length;
	
	private ArrayList<Glyph> glyphs;
	
	public Row(Canvas c, int row) {
		this.c = c;
		this.row = row;
		this.length = 0;
	}
	
	public void add(Glyph g) {
		glyphs.add(g);
	}
	
	public void draw() {
		
	}
}
