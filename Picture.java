import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class Picture extends Glyph {
	
	private String imageName;
	private Image image;
	private int width;
	private int height;
	private Display display;

	public Picture(String name, Display display) {
		this.imageName = name;
		image = getImage(display);
		this.width = getWidth();
		this.height = getHeight();
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

	private Image getImage(Display display) {
		Image pic = 
		  new Image(display, Picture.class.getResourceAsStream(this.imageName));
		return pic;
	}

}
