/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author CodeFox
 */
public class DataBaseHandler {
    
    private URL url;
    private JsonParser jsonParser;
    
    public DataBaseHandler() {
        this.jsonParser = new JsonParser();
    }
    
    public void connect(Query question) throws UnsupportedEncodingException, MalformedURLException {
        String query = question.getQuery();
        String graphEncoded = URLEncoder.encode("http://dbpedia.org", "UTF-8");
        String formatEncoded = URLEncoder.encode("application/sparql-results+json", "UTF-8");
        String queryEncoded = URLEncoder.encode(query, "UTF-8");
        String url = "http://dbpedia.org/sparql?default-graph-uri=" +
                     graphEncoded+"&query="+queryEncoded+"&format=" +
                     formatEncoded+"&debug=on&timeout=";
        this.url = new URL(url);
    }
    
    public ArrayList<String> getResults() throws IOException {
       	ArrayList<String> response = new ArrayList<>();
        HttpURLConnection httpcon = (HttpURLConnection) ((url.openConnection()));
    	httpcon.setDoOutput(true);
    	httpcon.setRequestMethod("POST");
    	httpcon.connect();
    	int status = httpcon.getResponseCode();
    	switch (status) {
            case 200: // success
            case 201:
            	BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
                String str = "";
                String line;
                while ((line = br.readLine()) != null) {
                    str += line + "\n";
                }
                br.close();
                response = jsonParser.normalize(str);
        	break;
            default :
               {
                   try {
                       throw new Exception("Error: bad status");
                   } catch (Exception ex) {
                       Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
        }
        return response;
    }
}