package mesSources.model;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShpFiles;
import org.geotools.data.shapefile.prj.PrjFileReader;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.referencing.operation.projection.Mollweide;
import org.opengis.feature.Feature;
import org.opengis.feature.Property;
import org.opengis.feature.type.FeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.bbn.openmap.layer.shape.ShapeFile;
import com.bbn.openmap.layer.shape.ShapeIndex;
import com.bbn.openmap.proj.LambertConformal;
import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.LatLonPoint.Double;
import com.jhlabs.map.proj.Ellipsoid;
import com.jhlabs.map.proj.LambertConformalConicProjection;
import com.jhlabs.map.proj.MolleweideProjection;
import com.sun.management.jmx.JMProperties;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.Point;

public class ShapeReader {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		new ShapeReader();
	}

	public ShapeReader() throws IOException {
		buildIhm();
		

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
		
		mainPane.add(buildMapPane(),BorderLayout.NORTH);
		mainPane.add(buildBoutonPane(),BorderLayout.SOUTH);
		
		return mainPane;
	}
	private JPanel buildMapPane(){
		JPanel mapPane = new JPanel();
		
		return mapPane;
	}
	private JPanel buildBoutonPane(){
		JPanel boutonPane = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		
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
		LatLonPoint centerLatLonPoint = new LatLonPoint.Double(49.5, 0);
		// 13.43 2.95
		LambertConformal lb = new LambertConformal(centerLatLonPoint, 1,
				998086, 337355, 2.3372291667, 50.39591166670001, 48.5985227778,
				49.5, 600000.0, 200000.0,
				com.bbn.openmap.proj.Ellipsoid.CLARKE_1880);
		LatLonPoint testPoint = new LatLonPoint.Double();
		lb.inverse(600325.1435783483, 128861.68064656129, testPoint);
		// lb.inverse(540653.415583,214233.140745, testPoint);
		System.out.println(testPoint.toString());

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
