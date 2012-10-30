package interfaceGraphique;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Afficher une carte GoogleMap dans un JEditorPane
 * @author fobec 2010
 */
public class GMapPanel extends JEditorPane {

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
    public GMapPanel() {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) kit.createDefaultDocument();
        this.setEditable(false);
        this.setContentType("text/html");
        this.setEditorKit(kit);
        this.setDocument(htmlDoc);
    }

    /**
     * Fixer le zoom
     * @param zoom valeur de 0 � 21
     */
    public void setZoom(int zoom) {
        this.zoomFactor = zoom;
    }

    /**
     * Fixer la cl� de developpeur
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
     * Afficher la carte d'apr�s des coordonn�es GPS
     * @param latitude
     * @param longitude
     * @param width
     * @param height
     * @throws Exception erreur si la APIKey est non renseign�e
     */
    public void showCoordinate(String latitude, String longitude, int width, int height) throws Exception {
        this.setMap(latitude, longitude, width, height);
    }

    /**
     * Afficher la carte d'apr�s une ville et son pays
     * @param city
     * @param country
     * @param width
     * @param height
     * @throws Exception erreur si la APIKey est non renseign�e
     */
    public void showLocation(String city, String country, int width, int height) throws Exception {
        this.setMap(city, country, width, height);
    }

    /**
     * Assembler l'url et G�n�rer le code HTML
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

       // String url = "http://maps.google.com/maps/api/staticmap?";
//        String url ="http://maps.google.com/maps/ms?vps=3&hl=fr&ie=UTF8&oe=UTF8&msa=0&msid=217932113684213302959.0004cd49e9e419b0271f1";
//        url += "&center=" + x + "," + y;
//        url += "&zoom=" + this.zoomFactor;
//        url += "&size=" + width.toString() + "x" + height.toString();
//        url += "&maptype=" + this.roadmap;
//        url += "&markers=color:blue" + x + "," + y;
//        url += "&sensor=false";
//        url += "&amp;key=" + this.ApiKey;

//        String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
//        html += "<html><head></head><body>";
//        html += "<img src='" + url + "'>";
//        html += "</body></html>";
        
        String html = "<iframe width=\"425\" height=\"350\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" src=\"https://maps.google.com/maps/ms?hl=fr&amp;ie=UTF8&amp;oe=UTF8&amp;msa=0&amp;msid=217932113684213302959.0004cd49e9e419b0271f1&amp;ll=48.799878,2.137395&amp;spn=0.038281,0.049426&amp;t=h&amp;output=embed\"></iframe>";
        this.setText(html);
        System.out.println(html+"\n");
    }

    /**
     * Exemple : JGoogleMapEditorPan dans un JFrame
     */
    public static void main(String[] args) {
        GMapPanel googleMap = new GMapPanel();
        try {
            googleMap.setApiKey("maCleGoogleMap");
            //  googleMap.setRoadmap(googleMap.viewHybrid);

            /**
            Afficher la ville de Strabourg
             */
         //   googleMap.showLocation("strasbourg", "france", 390, 400);
            /**
             * Afficher Paris en fonction ses coordonn�es GPS
             */
            googleMap.showCoordinate("48.8667", "2.3333",425, 350);
        } catch (Exception ex) {
            Logger.getLogger(JGoogleMapEditorPan.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(googleMap);
        frame.setSize(425, 350);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
}
