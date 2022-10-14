import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

import java.io.FileNotFoundException;
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
		setShell(new Shell(display, SWT.SHELL_TRIM | SWT.NO_BACKGROUND | 
						SWT.NO_REDRAW_RESIZE | SWT.V_SCROLL));
	    getShell().setText("Lil Lexi");
		getShell().setSize(675,840);
		getShell().setLayout(new GridLayout());	
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
		canvas.setSize(800,700);

		canvas.addPaintListener(e -> {
			System.out.println("PaintListener");
			Rectangle rect = getShell().getClientArea();
			/**
			 * TODO move this to Char and implement on an individual basis
			 *                         |
			 *                         V
			 */
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
     
        		// Conditional for special keys (BACKSPACE, ENTER, <-, ->,)
        		if (e.keyCode == 8) {
        			lexiControl.backspace();
        		} else if (e.keyCode == 13) {
        			lexiControl.enter();
        		} else if (e.keyCode == 16777219) {
        			System.out.println("<-");
        		} else if (e.keyCode == 16777220) {
        			System.out.println("->"); // 
        		} else {
        			System.out.println("key " + e.keyCode);
        			lexiControl.add(c);        			
        		}
        	}
        	public void keyReleased(KeyEvent e) {}
        });
		        
        //---- scroll bar
        Point origin = new Point(0,0);
        ScrollBar vBar = shell.getVerticalBar();
        vBar.addListener(SWT.Selection, e -> {
        	int vSelection = vBar.getSelection();
        	int destY = -vSelection + origin.y;
        	canvas.scroll(0, destY, 0, 0, 800, 800, false);
        	origin.y = -vSelection;
        });
        
        canvas.addListener(SWT.Resize, e -> {
        	Rectangle rect = canvas.getClientArea();
        	Rectangle client = canvas.getClientArea();
        	
        	vBar.setMaximum (rect.height);
        	vBar.setThumb(Math.min (rect.height, client.height));
        	int vPage = rect.height - client.height;
        	int vSelection = vBar.getSelection();
        	
        	if (vSelection >= vPage) {
        		if (vPage <= 0) vSelection = 0;
        		origin.y = -vSelection;
        	}
        });
        
        
        //---- status label
        lowerComp.setLayout(new RowLayout());
        lowerComp.setSize(800, 200);
        statusLabel = new Label(lowerComp, SWT.NONE);		
        statusLabel.setSize(200,800);
        
		//FontData[] fD = statusLabel.getFont().getFontData();
		//fD[0].setHeight(24);
		statusLabel.setFont(new Font(display,"Arial", 20,20));
		statusLabel.setText("Ready to edit!                                      ");
		
		//---- main menu
		Menu 
			menuBar, 
			fileMenu, 
			insertMenu, 
			controlMenu;
		MenuItem 
			fileMenuHeader, 
			insertMenuHeader, 
			controlMenuHeader;
		MenuItem  
			fileExitItem,
			insertImageItem, insertRectItem, 
			controlUndoItem, controlRedoItem, controlSpellCheckItem;

		menuBar = new Menu(getShell(), SWT.BAR);
		
		//---- Set File, Insert, and Control menu headers
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("File");
		fileMenu = new Menu(getShell(), SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);
		insertMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		insertMenuHeader.setText("Insert");
		insertMenu = new Menu(getShell(), SWT.DROP_DOWN);
		insertMenuHeader.setMenu(insertMenu);
		controlMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		controlMenuHeader.setText("Control");
		controlMenu = new Menu(getShell(), SWT.DROP_DOWN);
		controlMenuHeader.setMenu(controlMenu);
		
		//---- Insert -> Image Menu Item
	    insertImageItem = new MenuItem(insertMenu, SWT.PUSH);
	    insertImageItem.setText("Image");
	    insertImageItem.addSelectionListener(new SelectionListener() {
	    	
    	public void widgetSelected(SelectionEvent event) {
    		
    		//-------- Opens Window Finder to select image
    		Image originalImage = null;
    		  FileDialog dialog = new FileDialog(shell, SWT.OPEN);
    		  dialog.setText("Open an .jpeg File");
    		  String string = dialog.open();
    		  if (string != null) {
    		    originalImage = new Image(display, string);
    		  }
    		  if (originalImage == null) {
    			  originalImage = new Image(display, 
    					  LilLexiUI.class.getResourceAsStream("image.jpeg"));
    		  }
    		  lexiControl.addPicture(display,originalImage);
    		updateUI();
    	}
    	public void widgetDefaultSelected(SelectionEvent event) {
    		//----------------------------------
    		Image originalImage = null;
    		  FileDialog dialog = new FileDialog(shell, SWT.OPEN);
    		  dialog.setText("Open an .jpeg File");
    		  String string = dialog.open();
    		  if (string != null) {
    		    originalImage = new Image(display, string);
    		  }
    		  if (originalImage == null) {
    			  originalImage = new Image(display, 
    					  LilLexiUI.class.getResourceAsStream("image.jpeg"));
    		  }
    		lexiControl.addPicture(display,originalImage);
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
	    
	    //---- Control -> Undo Menu Item
	    controlUndoItem = new MenuItem(controlMenu, SWT.PUSH);
	    controlUndoItem.setText("Undo");
	    controlUndoItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		currentDoc.undo();
	    		updateUI();	
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		currentDoc.undo();
	    		updateUI();
	    	}
	    });
	    
	    //---- Control -> Redo Menu Item
	    controlRedoItem = new MenuItem(controlMenu, SWT.PUSH);
	    controlRedoItem.setText("Redo");
	    controlRedoItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		currentDoc.redo();
	    		updateUI();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		currentDoc.redo();
	    		updateUI();
	    	}
	    });
	    
	  //---- Control -> Spell Check Menu Item
	    controlSpellCheckItem = new MenuItem(controlMenu, SWT.PUSH);
	    controlSpellCheckItem.setText("Spell Check");
	    controlSpellCheckItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		try {
					if (currentDoc.spellCheck()) {
						statusLabel.setText("No errors found!");
					} else {
						statusLabel.setForeground(display.getSystemColor(SWT.COLOR_RED));
						statusLabel.setText("At least one spelling mistake found!");
					}
				} catch (FileNotFoundException e) {
					System.out.println("Sorry, dictionary not found.");
					e.printStackTrace();
				};
	    		updateUI();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		try {
					if (currentDoc.spellCheck()) {
						statusLabel.setText("No errors found!");
					} else {
						statusLabel.setForeground(display.getSystemColor(SWT.COLOR_RED));
						statusLabel.setText("At least one spelling mistake found!");
					}
				} catch (FileNotFoundException e) {
					System.out.println("Sorry, dictionary not found.");
					e.printStackTrace();
				};
	    		updateUI();
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