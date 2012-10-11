package model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShpFiles;
import org.geotools.data.shapefile.prj.PrjFileReader;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Feature;
import org.opengis.feature.Property;
import org.opengis.feature.type.FeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

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
	public ShapeReader() throws IOException{
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(800,600);
		frm.setTitle("Ma Première carte avec GeoTools");
		
	     
		JPanel pfond = new JPanel(new BorderLayout());
		frm.setContentPane(pfond);
		
		frm.setJMenuBar(buildMenu());
		
		
		 URL shapeURL = getClass().getResource("/environnement.shp");
//          System.out.println(shapeURL.getPath());
         ShapefileDataStore store = new ShapefileDataStore(shapeURL);
//         store.
         String name = store.getTypeNames()[0];
//         System.out.println(name);
         FeatureSource source = store.getFeatureSource(name);
         FeatureCollection<FeatureType, Feature> featCollec =source.getFeatures();
         
         FeatureIterator<Feature> iterator = featCollec.features(); 
         PrjFileReader prj= new PrjFileReader(new  ShpFiles(getClass().getResource("/environnement.prj")));
         CoordinateReferenceSystem coor= prj.getCoodinateSystem();
//         prj.
//         System.out.println(coor.);
         CoordinateSystem c = coor.getCoordinateSystem();
         
//        System.out.println(c.);
//         System.out.println( c.);
//         System.out.println(c.getDimension());
//         while(iterator.hasNext()){
////        	 break;
//        	 Feature feature= (Feature) iterator.next();
//        	 Collection<Property> collec_prop= feature.getProperties();
//        	 Iterator<Property> ite_prop = collec_prop.iterator();
//        	 if (feature.getProperties("Libelle").toString().contains("Banc")){
//        		 while (ite_prop.hasNext()){
//            		 Property prop=ite_prop.next();
//            		 MultiLineString ml= (MultiLineString) prop.getValue();
//            		 Point pt= ml.getCentroid();
//            		 pt.ge
//            		 System.out.println(pt);
//            		 System.out.println(prop.getName()+"  _______   "+prop.getValue());
//            		 break;
//            	 }
//        		 System.out.println();
//        	 }
//        	 else{
//        		 
//        	 }
//         }
         iterator.close();
         
     
	}
	private JMenuBar buildMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu mfichier = new JMenu("Fichier");
		JMenuItem iquitter = new JMenuItem("Quitter");
		iquitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mfichier.add(iquitter);
		menu.add(mfichier);
		    
	    return menu;
	
	}

}
