package mesSources.interfaceGraphique;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PopUp_Frame extends JInternalFrame {
	//X-Y Coordinates visually pointed by he marker
		private int X;
		private int Y;
		//  X-Y Coordinates of the upper-left corner of the marker 
		private int X_CORNER;
		private int Y_CORNER;
		
		private String Icon_Pieton= "/logo-petit-pieton.png";
		
	public PopUp_Frame(int X,int Y){
		super("titleeee",false,false,false);
		this.X=X;
		this.Y=Y;
		
		setContentPane(buildIHM());
		this.pack();
		X_CORNER= (int) (X-(this.getSize().getWidth()/2));
		Y_CORNER=(int) (Y-this.getSize().getHeight());
	}
	private JPanel buildIHM(){
		JPanel pane= new JPanel(new GridBagLayout());
		GridBagConstraints g= new GridBagConstraints();
//		pane.add(new JButton("S'asseoire"));
		g.gridx=0;
		g.gridy=0;
		pane.add(new JLabel(new ImageIcon(this.getClass().getResource(Icon_Pieton))),g);
		g.gridx=1;
		g.gridy=0;
		pane.add(new JLabel(" 10 min"),g);
		g.gridx=0;
		g.gridy=1;
		g.gridwidth=2;
		g.insets=new Insets(5, 5, 5, 5);
		g.ipadx=0;
		JButton jb=new JButton("M'y rendre !");
		jb.setMargin(new Insets(1, 1, 1, 1));
		pane.add(jb,g);
		g.gridx=2;
		g.gridy=0;
		g.gridwidth=1;
		pane.add(new JLabel(" - Palais Prygiel"),g);
		g.gridx=2;
		g.gridy=1;
		pane.add(new JLabel(" - Place Tsnobiladzé"),g);
		return pane;
	}
	public void setTitle(String title){
		this.setTitle(title);
	}
	
}
