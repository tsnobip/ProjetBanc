package interfaceGraphique;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Afficher une carte GoogleMap dans un JEditorPane
 * @author fobec 2010
 */
public class JGoogleMapEditorPan extends JEditorPane {

	public static int TILE_SIZE = 256;
    private int zoomFactor = 7;
    private String ApiKey = "";
    private String roadmap = "roadmap";
    public final String viewTerrain = "terrain";
    public final String viewSatellite = "satellite";
    public final String viewHybrid = "hybrid";
    public final String viewRoadmap = "roadmap";

    /**
     * Constructeur: initialisation du EditorKit
     */
    public JGoogleMapEditorPan() {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) kit.createDefaultDocument();
        this.setEditable(false);
        this.setContentType("text/html");
        this.setEditorKit(kit);
        this.setDocument(htmlDoc);
    }
    
    public void paint(Graphics g)
	{
		super.paint(g);
		ImageIcon marker = new ImageIcon("/Users/paul/Documents/workspace/ProjetBanc/blackMarker.png");
		marker.paintIcon(this,g,188+8,387-32);
	}

    /**
     * Fixer le zoom
     * @param zoom valeur de 0 a 21
     */
    public void setZoom(int zoom) {
        this.zoomFactor = zoom;
    }

    /**
     * Fixer la clef de developpeur
     * @param key APIKey fourni par Google
     */
    public void setApiKey(String key) {
        this.ApiKey = key;
    }

    /**
     * Fixer le type de vue
     * @param roadMap
     */
    public void setRoadmap(String roadMap) {
        this.roadmap = roadMap;
    }

    /**
     * Afficher la carte d'apres des coordonnees GPS
     * @param latitude
     * @param longitude
     * @param width
     * @param height
     * @throws Exception erreur si la APIKey est non renseignee
     */
    public void showCoordinate(String latitude, String longitude, int width, int height) throws Exception {
        this.setMap(latitude, longitude, width, height);
    }

    /**
     * Afficher la carte d'apres une ville et son pays
     * @param city
     * @param country
     * @param width
     * @param height
     * @throws Exception erreur si la APIKey est non renseignee
     */
    public void showLocation(String city, String country, int width, int height) throws Exception {
        this.setMap(city, country, width, height);
    }

    /**
     * Assembler l'url et Generer le code HTML
     * @param x
     * @param y
     * @param width
     * @param height
     * @throws Exception
     */
    private void setMap(String x, String y, Integer width, Integer height) throws Exception {
        if (this.ApiKey.isEmpty()) {
            throw new Exception("Developper API Key not set !!!!");
        }

        String url = "http://maps.google.com/maps/api/staticmap?";
        url += "center=" + x + "," + y;
        url += "&amp;zoom=" + this.zoomFactor;
        url += "&amp;size=" + width.toString() + "x" + height.toString();
        url += "&amp;maptype=" + this.roadmap;
//        url += "&amp;markers=color:blue|" + x + "," + y;
        url += "&amp;markers=color:blue|48.79554695,2.13075301";
        url += "&amp;sensor=false";
       // url += "&amp;key=" + this.ApiKey;

        String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
        html += "<html><head></head><body>";
        html += "<img src='" + url + "'>";
        html += "</body></html>";
//        html = "<iframe width=\"425\" height=\"350\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" src=\"https://maps.google.com/maps/ms?hl=fr&amp;static=true&amp;ie=UTF8&amp;oe=UTF8&amp;msa=0&amp;msid=217932113684213302959.0004cd49e9e419b0271f1&amp;ll=48.799878,2.137395&amp;spn=0.038281,0.049426&amp;t=h&amp;output=embed\"></iframe>";
        this.setText(html);
    }
    
    
    
    public Point latLngToPixel(double lat, double lng, double centerLat, double centerLng, int mapWidth, int mapHeight, int zoomFactor){
//    	int x = (int) Math.floor((zoomFactor / 360) * (lng + 180));
//    	int y = (int) Math.floor(Math.abs((zoomFactor / 2 / 180) * (lat - 90)));
    	int x =  lon2position(centerLng, zoomFactor) - lon2position(lng, zoomFactor) + mapWidth/2;
    	int y =  lat2position(lat, zoomFactor) - lat2position(centerLat, zoomFactor)  + mapHeight/2;
    	return (new Point(x,y));
    }

    public static int lon2position(double lon, int z) {
        double xmax = TILE_SIZE * (1 << z);
        return (int) Math.floor((lon + 180) / 360 * xmax);
    }

    public static int lat2position(double lat, int z) {
        double ymax = TILE_SIZE * (1 << z);
        return (int) Math.floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * ymax);
    }
    
    /**
     * Exemple : JGoogleMapEditorPan dans un JFrame
     */
    public static void main(String[] args) {
    	int mapWidth = 400;
    	int mapHeight = 420;
    	double lat = 48.80554695;
    	double lng = 2.12975301;
    	int zoomLevel = 14;
    	
        JGoogleMapEditorPan googleMap = new JGoogleMapEditorPan();
        try {
            googleMap.setApiKey("maCleGoogleMap");
            //  googleMap.setRoadmap(googleMap.viewHybrid);

            /**
            Afficher la ville de versailles
             */
            googleMap.setZoom(zoomLevel);
//            googleMap.showLocation("Versailles", "france", 400, 400);
           
            /**
             * Afficher Versailles en fonction ses coordonnees GPS
             */
            googleMap.showCoordinate(String.valueOf(lat), String.valueOf(lng),mapWidth, mapHeight);
        } catch (Exception ex) {
            Logger.getLogger(JGoogleMapEditorPan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Point pos = googleMap.latLngToPixel(48.79554695, 2.13075301, lat, lng, mapWidth, mapHeight, zoomLevel);
        System.out.println(pos+"\n");
        
        JButton button = new JButton();
        JFrame frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(googleMap);
        frame.setSize(mapWidth, mapHeight+20);
        frame.setLocation(mapWidth/2, mapHeight/2);
//        frame.add(marker);
        frame.setVisible(true);
        
    }
}
