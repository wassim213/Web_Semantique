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
public class Location extends Types{

    public Location(String[] keysFr, String[] keysEn) {
        super(keysFr, keysEn);
    }
    
    public String getSparqlQuery(UserRequest request) {
        String[] params = request.getParams();
        return "SELECT ?result\n"+
        	"WHERE { ?x rdfs:label \""+params[0]+"\"@"+request.getLanguage()+".\n " +
        	"{ ?x dbo:country ?location }\nUNION\n"+
        	"{ ?x ontology:birthPlace/ontology:country ?location }\n UNION \n" +
        	"{ ?x ontology:location ?location }.\n" +
        	"?location rdfs:label ?result.\n" +
                "FILTER(LANG(?reponse) = \""+request.getLanguage()+"\")\n}";
    }    

    @Override
    public String[] extractValues(UserRequest request) {
        String[] tmp = new String[1];
        tmp[0] = "";
        String req = request.getRequest().replaceAll("( )+", " ").replace("?", "").replace("where's", "where is");
        String[] tokens = req.split(" ");
        switch(request.getLanguage()) {
            case "en" :
                for(int i = 2; i < tokens.length; i++) {
                    tmp[0] += tokens[i] + " ";
                }
                break;
            case "fr" :
                if(tokens[1].compareTo("est")==0){
                    for(int i = 2; i < tokens.length; i++)
                        tmp[0] += tokens[i] + " ";
                } else {
                    for(int i = 3 ;i < tokens.length; i++)
                        tmp[0] += tokens[i] + " ";
                }
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
        tmp[0] = tmp[0].substring(0,tmp[0].length() - 1); // remove whitespace
        return tmp;
    }
}
