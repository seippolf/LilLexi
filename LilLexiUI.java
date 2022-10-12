/**
 * UI for Lil Lexi
 * 
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Font;
import java.util.List;


/**
 * LilLexiUI
 * 
 */
public class LilLexiUI
{
	private LilLexiDoc currentDoc;
	private LilLexiControl lexiControl;
	private Display display;
	private Shell shell;
	private Label statusLabel;
	private Canvas canvas;	
	
	/**
	 * Ctor
	 */
	public LilLexiUI() 
	{
		//---- create the window and the shell
		Display.setAppName("Lil Lexi");
		display = new Display();  
		setShell(new Shell(display));
	    getShell().setText("Lil Lexi");
		getShell().setSize(900,900);
		getShell().setLayout( new GridLayout());	
	}
		
	/**
	 * start the editor
	 */
	public void start()
	{	
		//---- create widgets for the interface
	    Composite upperComp = new Composite(getShell(), SWT.NO_FOCUS);
	    Composite lowerComp = new Composite(getShell(), SWT.NO_FOCUS);
	    
	    //---- canvas for the document
		canvas = new Canvas(upperComp, SWT.NONE);
		canvas.setSize(800,800);

		canvas.addPaintListener(e -> {
			System.out.println("PaintListener");
			Rectangle rect = getShell().getClientArea();
			e.gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE)); 
            e.gc.fillRectangle(rect.x, rect.y, rect.width, rect.height);
            e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK)); 
    		Font font = new Font(display, "Arial", 12, SWT.NONE);
    		e.gc.setFont(font);
    		
    		List<Glyph> glyphs = currentDoc.getGlyphs();
    		
    		int column = 0, row = 0;
    		for (Glyph g: glyphs)
    		{
//    			System.out.println(g);
    			g.draw(e.gc, column, row);			
    			row += g.getHeight();
    		}
		});	
		
        canvas.addMouseListener(new MouseListener() {
            public void mouseDown(MouseEvent e) {
            	System.out.println("mouseDown in canvas");
            } 
            public void mouseUp(MouseEvent e) {} 
            public void mouseDoubleClick(MouseEvent e) {} 
        });
        
        canvas.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent e) {
        		char c = e.character;
        		
        		// Conditional for special keys (<-, ->, BACKSPACE, ENTER)
        		if (c == '\b') {
        			System.out.println("key BACKSPACE");
        			lexiControl.backspace();
        		} else {
        			System.out.println("key " + e.character);
        			lexiControl.add(e.character);        			
        		}
        	}
        	public void keyReleased(KeyEvent e) {}
        });

		Slider slider = new Slider (canvas, SWT.VERTICAL);
		Rectangle clientArea = canvas.getClientArea ();
		slider.setBounds (clientArea.width - 40, clientArea.y + 10, 32, clientArea.height);
		slider.addListener (SWT.Selection, event -> {
			String string = "SWT.NONE";
			switch (event.detail) {
				case SWT.DRAG: string = "SWT.DRAG"; break;
				case SWT.HOME: string = "SWT.HOME"; break;
				case SWT.END: string = "SWT.END"; break;
				case SWT.ARROW_DOWN: string = "SWT.ARROW_DOWN"; break;
				case SWT.ARROW_UP: string = "SWT.ARROW_UP"; break;
				case SWT.PAGE_DOWN: string = "SWT.PAGE_DOWN"; break;
				case SWT.PAGE_UP: string = "SWT.PAGE_UP"; break;
			}
			System.out.println ("Scroll detail -> " + string);
		});
		        
        //---- status label
        lowerComp.setLayout(new RowLayout());
        statusLabel = new Label(lowerComp, SWT.NONE);		

		FontData[] fD = statusLabel.getFont().getFontData();
		fD[0].setHeight(24);
		statusLabel.setFont( new Font(display,fD[0]));
		statusLabel.setText("Ready to edit!");
		
		//---- main menu
		Menu menuBar, fileMenu, insertMenu;
		MenuItem fileMenuHeader, insertMenuHeader;
		MenuItem  fileExitItem, insertImageItem, insertRectItem;

		menuBar = new Menu(getShell(), SWT.BAR);
		
		//---- Set File and Insert menu headers
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("File");
		fileMenu = new Menu(getShell(), SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);
		insertMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		insertMenuHeader.setText("Insert");
		insertMenu = new Menu(getShell(), SWT.DROP_DOWN);
		insertMenuHeader.setMenu(insertMenu);
		
		//---- Insert -> Image Menu Item
	    insertImageItem = new MenuItem(insertMenu, SWT.PUSH);
	    insertImageItem.setText("Image");
	    insertImageItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		lexiControl.addPicture(display, "C:\\Users\\justi\\Pictures\\Bubble_gum_gamerpic.0.jpg");
	    		updateUI();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		lexiControl.addPicture(display, "C:\\Users\\justi\\Pictures\\Bubble_gum_gamerpic.0.jpg");
	    		updateUI();
	    	}
	    });
	    
	    //---- Insert -> Rectangle Menu Item
	    insertRectItem = new MenuItem(insertMenu, SWT.PUSH);
	    insertRectItem.setText("Rectangle");
	    insertRectItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		lexiControl.addShape(250, 250);
	    		updateUI();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		lexiControl.addShape(250, 250);
	    		updateUI();
	    	}	    
    	});
	    
	    //---- File -> Exit Menu Item
	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("Exit");
	    fileExitItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		getShell().close();
	    		display.dispose();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		getShell().close();
	    		display.dispose();
	    	}
	    });

	    getShell().setMenuBar(menuBar);
	      
		//---- event loop
		getShell().open();
		while( !getShell().isDisposed())
			if(!display.readAndDispatch()){}
		display.dispose();
	} 

	/**
	 * updateUI
	 */
	public void updateUI()
	{
		System.out.println("updateUI");
		canvas.redraw();
	}
	
	/**
	 * setCurrentDoc
	 */
	public void setCurrentDoc(LilLexiDoc cd) { currentDoc = cd; }
	
	/**
	 * setController
	 */
	public void setController(LilLexiControl lc) { lexiControl = lc; }

	public Shell getShell() {return shell;}

	public void setShell(Shell shell) {this.shell = shell;}
}

