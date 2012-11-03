package mesSources.interfaceGraphique;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Marqueur extends ImageIcon{
	private final static String ICON_PATH="purple";
	private int X;
	private int Y;
	private int X_CORNER;
	private int Y_CORNER;
	
	public Marqueur(int X,int Y){
		super(MainLoader.class.getResource("/"+ICON_PATH+".png"));
		this.X=X;
		X_CORNER= X-(this.getIconWidth()/2);
		this.Y=Y;
		Y_CORNER=Y-this.getIconHeight();
	}
	public void paintIcon(Component c, Graphics g) {
		super.paintIcon(c, g, X_CORNER, Y_CORNER);
	}
	public int getY() {
		return Y;
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
