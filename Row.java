import java.awt.Canvas;
import java.util.ArrayList;

import org.eclipse.swt.graphics.GC;

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
	
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void draw(GC gc, int x, int y) {
		for (Glyph g: glyphs) {
			g.draw(gc, x, y);
			
			// Add column spacing
			y += 10;
		}
	}

}
