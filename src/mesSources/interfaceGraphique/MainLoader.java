package mesSources.interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;


import mesSources.model.*;
public class MainLoader {

	private final String APIKeyWeatherOnline= "80a15653bf074854120810";
	private final String Ville= "Versailles";
	private final String WeatherBaseURL ="http://free.worldweatheronline.com/feed/weather.ashx";
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		new MainLoader();
	}

	public MainLoader() throws IOException {
		buildIhm();
		
		
//		System.out.println(TexteFileLoader.Read(fileName));
	}
	private void buildIhm(){
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(800, 600);
		frm.setTitle("Ma Première carte avec GeoTools");
		
		frm.setContentPane(buildMainPane());
		
		frm.setJMenuBar(buildMenu());
		frm.setVisible(true);
	}
	

	private JPanel buildMainPane(){
		JPanel mainPane = new JPanel(new BorderLayout());
		
		mainPane.add(buildMETEOPane(),BorderLayout.NORTH);
		mainPane.add(buildMapPane(),BorderLayout.CENTER);
		mainPane.add(buildBoutonPane(),BorderLayout.SOUTH);
		
		return mainPane;
	}
	private JPanel buildMapPane(){
		JPanel mapPane = new JPanel();
		mapPane.setBorder(BorderFactory.createLineBorder(Color.black));
		mapPane.add(new JLabel("LA MAP"));
		
		return mapPane;
	}
	
	/**
	 * @category METEO
	 * @return
	 */
	private JPanel buildMETEOPane(){
		JPanel meteoPane = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();

		//Obtention des infos Météos
		MeteoProvider mp=new MeteoProvider(WeatherBaseURL, Ville, MeteoProvider.FileType.JSON, APIKeyWeatherOnline);
		Hashtable<String, Object> dataMeteo = mp.getData();
		
//		Affichage des infos météos
		g.gridheight=1;g.gridwidth=2;
		g.gridx=GridBagConstraints.RELATIVE;
		g.anchor=g.WEST;
		g.ipady=3;
//		g.ipadx=5;
		meteoPane.add(new JLabel((ImageIcon) dataMeteo.get("icone")),g);
		
		g.fill=g.BOTH;
//		g.ipady=5;
		g.insets=new Insets(0, 0, 0, 0);
		meteoPane.add(buildMeteoInfo(dataMeteo),g);
		
//		
		return meteoPane;
	}
	/**
	 * @category METEO
	 * @param data
	 * @return
	 */
	private JPanel buildMeteoInfo(Hashtable data){
		JPanel mainPane = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
//		mainPane.setBackground(Color.red);
		g.fill=g.BOTH;
		JLabel J=new JLabel(); 
		
		mainPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 8, 2, 2,  Color.cyan), "  Météo  ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER, new Font(J.getFont().getFamily(), Font.BOLD, 15)));
		g.insets=new Insets(5, 10, 5, 10);
		mainPane.add(new JLabel((String) data.get("weatherDesc")),g);
		
		g.insets=new Insets(5, 10, 5, 10);
		mainPane.add(new JLabel("Température : "+(String) data.get("temperature")),g);
		
		mainPane.add(new JLabel("Couverture nuageuse : "+(String) data.get("cloudcover")+ " %"),g);
		return mainPane;
	}
	private JPanel buildBoutonPane(){
		JPanel boutonPane = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		boutonPane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		boutonPane.add(new JLabel("Les Boutons de Menus ou autres outils"));
		return boutonPane;
	}
	/**
	 * Juste pour garder uen trace de mon ancien code
	 */
	private void shapeLect(){
		URL shapeURL = getClass().getResource("/environnement.shp");

//		com.bbn.openmap.layer.shape.ShapeFile shap = new ShapeFile(new File(
//				shapeURL.getPath()));

		// new ShapeIndex(shapeURL.getPath()).dumpIndex();
		// System.out.println(shapeURL.getPath());
//		ShapefileDataStore store = new ShapefileDataStore(shapeURL);
		// // store.
//		String name = store.getTypeNames()[0];
		// // System.out.println(name);
//		FeatureSource source = store.getFeatureSource(name);
//		FeatureCollection<FeatureType, Feature> featCollec = source
//				.getFeatures();
		//
//		FeatureIterator<Feature> iterator = featCollec.features();
		// PrjFileReader prj= new PrjFileReader(new
		// ShpFiles(getClass().getResource("/environnement.prj")));
		// CoordinateReferenceSystem coor= prj.getCoodinateSystem();
		// coor.toWKT().
		//
//		LatLonPoint centerLatLonPoint = new LatLonPoint.Double(49.5, 0);
//		// 13.43 2.95
//		LambertConformal lb = new LambertConformal(centerLatLonPoint, 1,
//				998086, 337355, 2.3372291667, 50.39591166670001, 48.5985227778,
//				49.5, 600000.0, 200000.0,
//				com.bbn.openmap.proj.Ellipsoid.CLARKE_1880);
//		LatLonPoint testPoint = new LatLonPoint.Double();
//		lb.inverse(600325.1435783483, 128861.68064656129, testPoint);
//		// lb.inverse(540653.415583,214233.140745, testPoint);
//		System.out.println(testPoint.toString());

		// new com.bbn.openmap.proj.Ellipsoid("Clarke 1880", 6378249.2,
		// 293.46602));

		// LambertConformalConicProjection lambPro= new
		// LambertConformalConicProjection

		// new Ellipsoid("Clarke_1880_IGN", 6378249.2, 293.46602,"aaaaa"), 49.5,
		// 2.3372291667, 50.39591166670001, 48.5985227778, 600000.0, 200000.0);
		// // lambPro.inverseTransform(new 605781.2135908165,
		// (Point2D.Double)128046.79420748491);
		// lambPro.inverseTransform(new
		// Point2D.Double(605781.2135908165,128046.79420748491) , new
		// Point2D.Double(0,0));

		// System.out.println(coor.toWKT());
		// CoordinateSystem c = coor.getCoordinateSystem();

		// System.out.println(c.getDimension());
//		while (iterator.hasNext()) {
//			// break;
//			Feature feature = (Feature) iterator.next();
//			Collection<Property> collec_prop = feature.getProperties();
//			Iterator<Property> ite_prop = collec_prop.iterator();
//			if (feature.getProperties("Libelle").toString().contains("Banc")) {
//				// while (ite_prop.hasNext()){
//				Property prop = ite_prop.next();
//				MultiLineString ml = (MultiLineString) prop.getValue();
//				Point pt = ml.getCentroid();
//				// pt.ge
//				System.out.println(pt);
//				System.out.println(prop.getName() + "  _______   "
//						+ prop.getValue());
//				// break;
//				// }
//				System.out.println();
//				break;
//			}
//			// else{
//			//
//			// }
//		}
//		iterator.close();
	}

	/**
	 * Construction de la barre de Menu
	 * 
	 * @return la {@link JMenuBar}
	 */	
	private JMenuBar buildMenu() {
		JMenuBar menuBar = new JMenuBar();

		menuBar.add(buildMenuFichier());
		menuBar.add(buildMenuAide());

		return menuBar;

	}

	/**
	 * Construction du menu <i>Fichier</i>
	 * 
	 * @return le {@link JMenu}
	 */
	private JMenu buildMenuFichier() {
		JMenu menu = new JMenu("Fichier");
		JMenuItem menuItem = new JMenuItem("Quitter");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke('q'));
		menu.add(menuItem);
		return menu;
	}

	/**
	 * Construction du menu <i>Aide</i>
	 * 
	 * @return le {@link JMenu}
	 */
	private JMenu buildMenuAide() {
		JMenu menu = new JMenu("Aide");
		JMenuItem menuItem = new JMenuItem("A Propos");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		menu.add(menuItem);
		return menu;
	}

}
