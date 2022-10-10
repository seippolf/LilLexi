import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class Picture extends Glyph {
	
	private char c;
	private String imageName;
	private Image image;
	private int width;
	private int height;
	private Display display;

	public Picture(String name, Display display) {
		this.c = ' ';
		this.imageName = name;
		image = getImage(display);
		this.width = getWidth();
		this.height = getHeight();
	}
	
	private Image getImage(Display display) {
		Image pic = 
				new Image(display, Picture.class.getResourceAsStream(this.imageName));
		return pic;
	}
	
	/*
	 * THIS MIGHT ACTUALLY WORK! lol
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
