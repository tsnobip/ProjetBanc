package mesSources.model;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Gestion des données Météos. Récupération et Traitement
 * @author Ulysse PRYGIEL
 *
 */
public class MeteoProvider   {
	/**
	 * Le texte récuperer avec la requête
	 * @see MeteoProvider#connect(String, String, FileType, String)
	 */
	private String Data_String;
	/**
	 * Image récupérer avec la requete
	 */
	private Image Data_Image;
	/**
	 * Permet de connaitre l'etat de la connexion, est utilisé par une classe exterieur
	 * @see GetHTTPRequest#GetHTTPRequest(JFrame, String, String, boolean)
	 * @see GetHTTPRequest#getCon()
	 */
	public enum  FileType {JSON, XML,CSV};
	private InputStream inStream;
	
	
	/**
	 * @category Constructor
	 * Run the connexion to get the meteo with the wished parameters
	 * @param endpoint base URL
	 * @param ville town
	 * @param format the answer format wished {@link FileType}
	 * @param APIKey WeatherOnline api Key to access data
	 * @see HTTPRequester#connect(String, String)
	 */
	public MeteoProvider(String endpoint, String ville, FileType format, String APIKey){
		URLConnection urlConn = null;
		try {
			urlConn = HTTPRequester.connect(endpoint, getMeteoParameters(ville, format, APIKey));
		}catch (IOException ee) {
			JOptionPane.showMessageDialog(new JFrame(), "La connexion au service Météo à échoué\nMétéo indisponible", "Echec de Connexion", JOptionPane.ERROR_MESSAGE);
			ee.printStackTrace();
		}
		
		inStream = HTTPRequester.getStream(urlConn);
		if(inStream==null){
			JOptionPane.showMessageDialog(new JFrame(), "Erreur Inconnue", "Echec", JOptionPane.ERROR_MESSAGE);
			return;
		}

		
	}
	
 	private String getMeteoParameters(String ville, FileType format, String APIKey){
		
		//q=Versailles,France&format=json&num_of_days=2&key=80a15653bf074854120810
		
		String parametres= "q=";
		parametres+=ville+",France&format=";
				
				
		switch(format){
		case CSV:parametres+="csv&num_of_days=2&key=";
			break;
		case JSON:parametres+="json&num_of_days=2&key=";
			break;
		case XML:parametres+="xml&num_of_days=2&key=";
			break;
		
		}
		
		return parametres+=APIKey;
		
	}

	/**
	 * Return an Hashtable with all pertinent data
	 * @category Accessor
	 * @return an {@link Hashtable} with the following key
	 * <ul> <li> "temperature" - <i>String</i></li>
	 * <li> "cloudcover" - <i>String</i>: Couverture nuageuse en pourcentage</li>
	 * <li> "weatherDesc" - <i>String</i>: Courte description de la condition météorologique</li>
	 * <li> "icone" - <i>ImageIcon</i>: Courte description de la condition météorologique</li>
	 * </ul>
	 */
	public Hashtable getData(){
		JSONObject current=null;
		Hashtable<String,Object> data= new Hashtable<String, Object>();
		try {
			 current = new JSONObject(HTTPRequester.getAString(inStream)).getJSONObject("data").getJSONArray("current_condition").getJSONObject(0);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
			try {
				data.put("temperature",(String) current.getString("temp_C"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				data.put("cloudcover",(String) current.getString("cloudcover"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				data.put("weatherDesc",(String) current.getJSONArray("weatherDesc").getJSONObject(0).getString("value"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			URLConnection urlConn = null;
			try {
				urlConn = HTTPRequester.connect(current.getJSONArray("weatherIconUrl").getJSONObject(0).getString("value"),null);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(new JFrame(), "La connexion au service Météo a échoué", "Echec de Connexion", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			InputStream inStream_im = HTTPRequester.getStream(urlConn);
			if(inStream_im==null){
				JOptionPane.showMessageDialog(new JFrame(), "Erreur Inconnue", "Echec", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			data.put("icone",(ImageIcon)new ImageIcon(HTTPRequester.getAnImage(inStream_im)));
	
		
		return data;
	}

}