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
public class PersonProperty extends Property{

    public PersonProperty(String[] keysFr, String[] keysEn) {
        super(keysFr, keysEn);
    }

   

    @Override
    public String getSparqlQuery(UserRequest request) {
        String[] params = request.getParams();
        return "SELECT ?result WHERE {\n" +
	       "?complement rdfs:label \"" + params[0] + "\"@" + request.getLanguage() + ".\n" +
	       "?complement \"" + params[1] + "\" ?p.\n" + 
               "?p ?c ontology:Person.\n" +
               "?p rdfs:label ?result.\n" +
               "FILTER(LANG(?result) = \"" + request.getLanguage() +"\")\n}\n";
    }

    @Override
    public String[] extractValues(UserRequest request) {
        String[] tmp = new String[2];
        tmp[0] = ""; tmp[1] = "";
        String req = request.getRequest().replaceAll("( )+", " ").replace("?", "").replace("'s", " is");
        String[] tokens = req.split(" ");
        switch(request.getLanguage()) {
            case "en" :
                if(tokens[2].compareTo("the") == 0) {
                    int i = 3;
                    for(; i < tokens.length && (tokens[i].compareTo("of")!=0); i++) {
                        tmp[0] += tokens[i] + " ";
                    }
                    for( i = i + 1; i < tokens.length; i++) {
                        tmp[1] += tokens[i]+ " ";
                    }
                } else {
                    int i = 2;
                    for(; i < tokens.length && (tokens[i].compareTo("is")!=0); i++) {
                        tmp[1] += tokens[i]+ " ";
                    }
                    for( i = i + 1; i < tokens.length; i++) {
                        tmp[0] += tokens[i]+ " ";
                    }
                }
                break;
            case "fr" :
                int i = 3;
                for(; i < tokens.length && (tokens[i].compareTo("de")!=0); i++){
                    tmp[0] += tokens[i]+ " ";
                } for(i = i + 1; i < tokens.length; i++)
                    tmp[1] += tokens[i]+ " ";
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
        tmp[1] = tmp[1].substring(0, tmp[1].length()-1);
        return tmp;
    }

    
    
}
