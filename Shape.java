import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.*;

public class Shape extends Glyph {

	private int x = 0;
	private int y = 0;
	private int width;
	private int height;
	private Canvas canvas;
	
	public Shape (Canvas canvas, int wi, int he) {
		this.width = wi;
		this.height = he;
	}
	
	public void draw() {
		GC gc = new GC(canvas);
		gc.drawRectangle(this.x, this.y, this.width, this.height);
	}
	
	@Override
	public int getWidth() {return width;}

	@Override
	public int getHeight() {return height;}

}