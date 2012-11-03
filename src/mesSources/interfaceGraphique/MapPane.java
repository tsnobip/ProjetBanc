package mesSources.interfaceGraphique;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JPanel;


public class MapPane extends JEditorPane implements MouseListener {
	
	public MapPane(){
		super();
		setEditable(false);
		setText("s<dfqsgqsdfgs");
		addMouseListener(this);
		
	}
	@Override public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Marqueur ma=new Marqueur(200,200);
        ma.paintIcon(this, g);
        Marqueur maa=new Marqueur(200,190);
        maa.paintIcon(this, g);
        // paints background
         // do your drawing here
   }
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() +"  "+ e.getY());
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
