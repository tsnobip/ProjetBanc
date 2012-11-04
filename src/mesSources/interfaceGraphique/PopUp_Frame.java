package mesSources.interfaceGraphique;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;



public class PopUp_Frame extends JInternalFrame {
	//X-Y Coordinates visually pointed by he marker
		private int X;
		private int Y;
		//  X-Y Coordinates of the upper-left corner of the marker 
		private int X_CORNER;
		private int Y_CORNER;
		private String Title;
		private final static String line="\n";
		private JTextArea texte;
		
		private String Icon_Pieton= "/logo-petit-pieton.png";
	public PopUp_Frame(int X,int Y){
		this("John Doe Banc",X,Y);
	}
	public PopUp_Frame(String title ,int X,int Y){
		super(title,false,false,false,false);
		this.Title=title;
		InternalFrameUI ui = (InternalFrameUI) getUI();
		this.X=X;
		this.Y=Y;
		setLocation(X, Y);
		setContentPane(buildIHM());
		this.pack();
//		X_CORNER= (int) (X-(this.getSize().getWidth()/2));
//		Y_CORNER=(int) (Y-this.getSize().getHeight());
	}
	private JPanel buildIHM(){
		JPanel pane= new JPanel(new GridBagLayout());
		GridBagConstraints g= new GridBagConstraints();
//		pane.add(new JButton("S'asseoire"));
		Insets ins= new Insets(0, 0, 0, 0);
		g.insets=ins;
		g.gridx=0;
		g.gridy=0;
		g.gridheight=2;
		pane.add(buildIcButton(),g);
		
		g.gridx=1;
		g.gridy=0;
		g.gridheight=2;
		g.gridwidth=1;
		g.anchor=g.WEST;
		ins.set(0, 3, 0, 5);
		texte= new JTextArea();
		texte.setOpaque(false);
		texte.append(" - Palais Prygiel"+line);
		texte.append(" - Place Tsnobiladzé");
		texte.setLineWrap(false);
		pane.add(texte,g);
		g.gridx=1;
		g.gridy=1;
//		pane.add(new JLabel(" - Place Tsnobiladzé"),g);
		return pane;
	}
	public JPanel buildIcButton(){
		JPanel pane= new JPanel(new GridBagLayout());
		GridBagConstraints g= new GridBagConstraints();
		pane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
//		pane.add(new JButton("S'asseoire"));
		Insets ins= new Insets(0, 0, 0, 0);
		g.insets=ins;
		g.gridx=0;
		g.gridy=0;
		ins.set(3, 3, 3, 3);
		pane.add(new JLabel(new ImageIcon(this.getClass().getResource(Icon_Pieton))),g);
		g.gridx=1;
		g.gridy=0;
		pane.add(new JLabel(" 10 min"),g);
		g.gridx=0;
		g.gridy=1;
		g.gridwidth=2;
		ins.set(5, 5, 5, 5);
		g.ipadx=0;
		JButton jb=new JButton("M'y rendre !");
		jb.setMargin(new Insets(1, 1, 1, 1));
		pane.add(jb,g);
		
		return pane;
	}
	public void setTitle(String title){
		this.setTitle(title);
	}
	
}
