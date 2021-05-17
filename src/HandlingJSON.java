import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class HandlingJSON {
	
	private JSONArray ja = null;
	private JSONObject jo = null;
	
	public HandlingJSON() {
		this.jo = new JSONObject();
		this.ja = new JSONArray();
	}

	public JSONArray getArray() {
		return ja;
	}

	public void setArray(JSONArray ja) {
		this.ja = ja;
	}

	public JSONObject getObject() {
		return jo;
	}

	public void setObject(JSONObject jo) {
		this.jo = jo;
	}
	
	/**
	 * Add a JSONObject in my JSONArray.
	 * @param jo JSONOnject
	 */
	public void addObject(JSONObject jo) {
		this.ja.add(jo);
	}
	
	/**
	 * Method to save an object to a .json text file.
	 * I can choose whether to save a JSONObject or a JSONArray.
	 * 
	 * @param filename 
	 * @param isObject It specifies if the object to save is a JSONObject or a JSONArray.
	 */
	public void saveFile(String filename, boolean isObject) {
		try {
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			
			if(isObject)
				file_output.println(this.jo);
			else
				file_output.println(this.ja);
			
			file_output.close();
			System.out.println("File Saved!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to read an object from a .json text file.
	 * I can choose whether to load a JSONObject or a JSONArray.
	 * 
	 * @param nome_file Nome del file da cui leggere l'oggetto.
	 * @param isObject It specifies if the object to load is a JSONObject or a JSONArray.
	 */
	public void loadFile(String filename, boolean isObject) {
		String data = "";
		String line = "";
		try {
			Scanner file_input = new Scanner(new BufferedReader(new FileReader(filename)));	  
			String str = file_input.nextLine();
			
			if(isObject) {
				this.jo = (JSONObject) JSONValue.parseWithException(str);	 //parse JSON Object
				System.out.println("JSONObject loaded: "+ this.jo);
			}else{
				this.ja = (JSONArray) JSONValue.parseWithException(str);	//parse JSON Array
				System.out.println("JSONArray loaded: "+ this.ja);
				System.out.println("This JSONArray has got "+ this.ja.size()+" elements!");
			}
			
			file_input.close();
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to download an object using an API call.
	 * I can choose whether to download a JSONObject or a JSONArray.
	 * 
	 * @param url 
	 * @param isObject It specifies if the object to download is a JSONObject or a JSONArray.
	 */
	public void APIcall(String url, boolean isObject) {
		try {
			URLConnection openConnection = new URL(url).openConnection();
			InputStream in = openConnection.getInputStream();
			
			String data = "";
			String line = "";
			try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
			   }
			} finally {
			   in.close();
			}

			if(isObject) {
				this.jo = (JSONObject) JSONValue.parseWithException(data);	 //parse JSON Object
				System.out.println("JSONObject downloaded: "+ this.jo);
			} else {
				this.ja = (JSONArray) JSONValue.parseWithException(data);	//parse JSON Array
				System.out.println("JSONArray downloaded: "+ this.ja);
				System.out.println("The JSONArray has got "+ this.ja.size()+" elements:");
			
				/* Example of a JSONArray:
				 * 
				 * ["Animals","Anime","Anti-Malware","Art \u0026 Design","Books","Business","Calendar",
				 * "Cloud Storage \u0026 File Sharing","Continuous Integration","Cryptocurrency","Currency Exchange",
				 * "Data Validation","Development","Dictionaries","Documents \u0026 Productivity","Environment",
				 * "Events","Finance","Food \u0026 Drink","Games \u0026 Comics","Geocoding","Government","Health",
				 * "Jobs","Machine Learning","Music","News","Open Data","Open Source Projects","Patent","Personality",
				 * "Phone","Photography","Science \u0026 Math","Security","Shopping","Social","Sports \u0026 Fitness",
				 * "Test Data","Text Analysis","Tracking","Transportation","URL Shorteners","Vehicle","Video","Weather"]
				 */
				for(int i=0;i<this.ja.size();i++) {

					System.out.println(i+") "+this.ja.get(i));
				}
			}
				
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
