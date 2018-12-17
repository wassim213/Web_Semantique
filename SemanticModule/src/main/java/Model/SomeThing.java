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
public class SomeThing extends Identity{

    public SomeThing(String[] keysFr, String[] keysEn) {
        super(keysFr, keysEn);
    }
    
   
    @Override
    public String getSparqlQuery(UserRequest request)   {
        String[] values = request.getParams();
        return  "SELECT ?result WHERE { " +
                "?something rdfs:label \""+ values[0] +"\"@" + request.getLanguage() + "." +
                "?something rdfs:comment ?result" +
                "FILTER(LANG(?result)=\"" + request.getLanguage() + "\")}"; 
            }

    @Override
    public String[] extractValues(UserRequest request) {
        String[] tmp = new String[1];
        tmp[0] = ""; 
        String req = request.getRequest().replaceAll("( )+", " ")
                            .replace("?", "")
                            .replace("what's", "what is")
                            .replace("qu'est ce", "qu'est-ce");
        String[] tokens = req.split(" ");
        switch(request.getLanguage()) {
            case "en" :
                
                
            case "fr" :
                for(int i = 2; i < tokens.length; i++) {
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
        tmp[0] = tmp[0].substring(0, tmp[0].length() - 1);
        return tmp;
    }




   
}
