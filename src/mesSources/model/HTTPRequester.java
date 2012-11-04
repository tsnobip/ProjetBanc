package mesSources.model;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HTTPRequester {
	
	/**
	 * Establish a connection with the following parameters and return an {@link URLConnection}
	 * @param URL base URL 
	 * @param requestParameters parameters (without <i>?</i> or null
	 * @return the {@link URLConnection} associated
	 *  null if failure
	 * @throws IOException 
	 */
	public static URLConnection connect(String endpoint, String requestParameters) throws IOException
	{
		String result = null;
		if (endpoint.startsWith("http://"))	{
				// Send data
				String urlStr = endpoint;
				if (requestParameters != null && requestParameters.length () > 0){
					urlStr += "?" + requestParameters;
				}
			URL url = new URL(urlStr);
			
			URLConnection conn=null;
			conn = url.openConnection ();
			conn.setConnectTimeout(5000);
			return conn;
		}
		else return null;		 
	
	}
	public static InputStream getStream(URLConnection conn){
		InputStream inStream=null;
		Image Data_Image=null;
		try{
		inStream=conn.getInputStream();
		}catch(IOException e){
			JOptionPane.showMessageDialog(new JFrame(), "La connexion a échoué","Echec de Connexion", JOptionPane.ERROR_MESSAGE);
//			connexion=false;
			
			System.out.println(e.toString());
			return null;
		}
		return inStream;
	}
	public static Image getAnImage(InputStream inStream){
		Image Data_Image=null;
			try {
				Data_Image= ImageIO.read(inStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return Data_Image;
		}
	public static String getAString(InputStream inStream){
		
			BufferedReader rd = new BufferedReader(new InputStreamReader(inStream));
			StringBuffer sb = new StringBuffer();
			String line=" ";
//			boolean  first=true;
			while (line != null){
				try {
					line = rd.readLine();
				} catch (IOException e) {
					System.out.println(e.toString());
					return null;
				}
				if(line !=null){
					sb.append(line);
					sb.append('\n');
				}
			}
			try {
				rd.close();
			} catch (IOException e) {
				System.out.println(e.toString());
				return null;
			}
			return sb.toString();
	}
	
	
}
