package interfaceGraphique;

import interfaceGraphique.MapPanel.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TestMapPanel {
	
	

	public static void main(String[] arg){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    // ignore
                }
                launchUI();
            }
        });
	}
	
	
	
    public static void launchUI() {

        final JFrame frame = new JFrame();
        frame.setTitle("Map Panel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(800, 600);
        frame.setLocation((sz.width - frame.getWidth()) / 2, (sz.height - frame.getHeight())/2);

        //Gui gui = new Gui();
        MapPanel map = new MapPanel(new Point(1061843, 721160), 13);
        frame.getContentPane().add(map, BorderLayout.CENTER);

        //JMenuBar menuBar = gui.createMenuBar();
        //frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
	
}
