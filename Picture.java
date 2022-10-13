import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class Picture extends Glyph {
	
	private char c;
	private String path;
	private Image image;
	private int width;
	private int height;

	public Picture(Display display, Image image){
		this.c = ' '; // Char is a space for comparison operations
		//this.path = path;
		
		this.image = image; // getImage(display);
		this.width = this.image.getImageData().width;
		this.height = this.image.getImageData().height;
	}
	
	/**
	 * Return an image from a file (located at path)
	 * with passed display.
	 * 
	 * @param display
	 * @return Image class from path and display
	 */
	private Image getImage(Display display) {
		return new Image(display, path);
	}
	
	/*
	 * Draw an image with starting (top left)
	 *  coordinates of x and y. Image size
	 *  is determined by file.
	 */
	public void draw(GC gc, int x, int y) {
		gc.drawImage(image, x, y);
	}
	
	@Override
	public int getWidth() {
		ImageData iData = image.getImageData();
		this.width = iData.width;
		return width;
	}

	@Override
	public int getHeight() {
		ImageData iData = image.getImageData();
		this.height = iData.height;
		return height;
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
