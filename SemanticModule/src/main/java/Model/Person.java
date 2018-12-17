/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CodeFox
 */
public class Person extends Identity{
    
    public Person(String[] keysFr, String[] keysEn){
        super(keysFr,keysEn);
    }

    @Override
    public String getSparqlQuery(UserRequest request) {
        String[] values = request.getParams();
        return "SELECT ?result WHERE {\n" +
               "?p rdfs:label \"" + values[0] + "\"@" + request.getLanguage() + ".\n" +
               "?p a ontology:Person .\n" +
               "?p rdfs:comment ?result .\n" +
               "FILTER(LANG(?result) = \"" + request.getLanguage() + "\")\n}\n";
    }

    @Override
    public String[] extractValues(UserRequest request) {
        String[] tmp = new String[1];
        tmp[0] = "";
        String req = request.getRequest().replaceAll("( )+", " ").replace("?", "").replaceAll("who's", "who is");
        String[] tokens = req.split(" ");
        System.out.println("Spou: " + req);
        switch(request.getLanguage()) {
            case "fr" :
            case "en" :
                for(int i = 2; i < tokens.length; i++) 
                    tmp[0] += tokens[i] + " ";
                break;
            default :
                {
                    try {
                        throw new Exception("UnknownLanguageSetException: Unknown Language at " + this.getClass().getName());
                    } catch (Exception ex) {
                        Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
        tmp[0] = tmp[0].substring(0, tmp[0].length()-1);
        return tmp;
    }
}
