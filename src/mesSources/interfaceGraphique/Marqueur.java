package mesSources.interfaceGraphique;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
//import astro

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

import com.bbn.openmap.app.Main;

import mesSources.model.Circle;
import mesSources.model.Point;

public class Marqueur extends ImageIcon{
	private final static String ICON_NORMAL_PATH="green";
	private final static String ICON_CLICKED_PATH="green-dot";
	private final String ICON_SHADOW_PATH="shadow";
	private boolean SHADOWED=true;
	//X-Y Coordinates visually pointed by he marker
	private int X;
	private int Y;
	//  X-Y Coordinates of the upper-left corner of the marker 
	private int X_CORNER;
	private int Y_CORNER;
	//  Lat-Lon Coordinates visually pointed by the marker
	private int X_GEO;
	private int Y_GEO;
	private PopUp_Frame pop;
	public enum  state{NORMAL,CLICKED}
	
	public Marqueur(int X_GEO,int Y_GEO){
		super(MainLoader.class.getResource("/"+ICON_NORMAL_PATH+".png"));
//		new Image(MainLoader.class.getResource("/"+ICON_NORMAL_PATH+".png")
		
		this.setImage(new ImageIcon(MainLoader.class.getResource("/"+ICON_NORMAL_PATH+".png")).getImage());
		this.X_GEO=X_GEO;
		this.Y_GEO=Y_GEO;
	
		// La on met ta fonction de calcul
		this.X=this.X_GEO;
		this.Y=this.Y_GEO;
		
		X_CORNER= X-(this.getIconWidth()/2);
		Y_CORNER=Y-this.getIconHeight();
		
		
	}
	public void setPopUp(boolean display, JDesktopPane desk){
		
		if (display) {
			pop = new PopUp_Frame(X,Y);
			pop.setLocation(X, Y);
			pop.pack();
			desk.add(pop);
			pop.setVisible(true);
		} else {
			pop.setVisible(false);
		}
		
	}
 	public boolean isSHADOWED() {
		return SHADOWED;
	}
	public void setSHADOWED(boolean sHADOWED) {
		SHADOWED = sHADOWED;
	}
	public void setState(state state){
		switch (state){
		case CLICKED:this.setImage(new ImageIcon(MainLoader.class.getResource("/"+ICON_CLICKED_PATH+".png")).getImage());
			break;
		case NORMAL:this.setImage(new ImageIcon(MainLoader.class.getResource("/"+ICON_NORMAL_PATH+".png")).getImage());
			break;
		
		}
	}
	public void paintIcon(Component c, Graphics g) {
		if(SHADOWED){
			new ImageIcon(MainLoader.class.getResource("/"+ICON_SHADOW_PATH+".png")).paintIcon(c, g, X_CORNER, Y_CORNER);
		}
		
		paintIcon(c, g, X_CORNER, Y_CORNER);
		
		
	}
	public boolean isClickIn(int x_click,int y_click){
		Circle cir=new Circle(new Point(X, Y-(23*(this.getIconHeight()/32))), (this.getIconWidth()/2)-6);
		return cir.contains(new Point(x_click, y_click));
	}
 	public int getY() {
		return Y;
	}
 	public int getX() {
 		return X;
 	}
	public void setY(int y) {
		Y = y;
	}
	public int getX_CORNER() {
		X_CORNER= X-(this.getIconWidth()/2);
		return X_CORNER;
	}
	public int getY_CORNER() {
		Y_CORNER=Y-this.getIconHeight();
		return Y_CORNER;
	}
	public void setX(int x) {
		X = x;
	}
}
