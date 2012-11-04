package mesSources.interfaceGraphique;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;


public class MapPane extends JDesktopPane implements MouseListener,MouseMotionListener {
	
	private List<Marqueur> marqueurs;
	private ListIterator<Marqueur> iterator;
	private Marqueur Current_Marker_Clicked;
	public MapPane(){
		super();
		addMouseListener(this);
		setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		marqueurs= new ArrayList<Marqueur>();
		
//		iterator();
		
		marqueurs.add(new Marqueur(200,200));
		marqueurs.add(new Marqueur(100,300));
//		iterator= marqueurs.listIterator();
	}
	@Override public void paintComponent(Graphics g) {
		
        super.paintComponent(g);
        
        iterator= marqueurs.listIterator();
        while(iterator.hasNext()){
        	Marqueur temp1=iterator.next();
        	temp1.paintIcon(this, g);
        }
   }
	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println(e.getX() +"  "+ e.getY());
		iterator= marqueurs.listIterator();
		if(Current_Marker_Clicked!=null){
			Current_Marker_Clicked.setState(Marqueur.state.NORMAL);
			Current_Marker_Clicked.setPopUp(false, null);
//			this.getAllFrames()[0].setVisible(false);
		}
		while (iterator.hasNext()) {
			Marqueur marqueur = (Marqueur) iterator.next();
			if (marqueur.isClickIn(e.getX(), e.getY())){
				marqueur.setState(Marqueur.state.CLICKED);
				Current_Marker_Clicked=marqueur;
				Current_Marker_Clicked.setPopUp(true,this);
				System.out.println("IN IT");
//				
				
			}
			else{
				System.out.println("NOT IN");
			}
		}
		this.repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println(e.getX() +"  "+ e.getY());
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() +"  "+ e.getY());
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getX() +"  "+ e.getY());
	}
	
	
}
