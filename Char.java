import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class Char extends Glyph {
	
	private char c;
	private int width;
	private int height;
	private Shell shell;
	private FontMetrics fm;
	
	public Char(char c, Shell shell) {
		this.c = c;
		this.shell = shell;
		
		Text text = new Text(shell, SWT.NONE);
		GC gc = new GC(text);
		this.fm = gc.getFontMetrics();
		this.width = getWidth();
		this.height = getHeight();
	}
	
	public void draw() {};

	public char getChar() {return c;}
	public void setChar(char c) {this.c = c;}

	@Override
	public  int getWidth() {
		width = (int) fm.getAverageCharacterWidth();
		return width;
	}

	@Override
	public int getHeight() {
		height = (int) fm.getHeight();
		return height;
	}

}
