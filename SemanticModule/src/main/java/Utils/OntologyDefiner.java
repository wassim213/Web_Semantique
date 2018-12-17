/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CodeFox
 */
public class OntologyDefiner {
    private HashMap<String, String> map;
    
    public OntologyDefiner() {
        this.map = new HashMap<>();
        // Part taken from Aziz 
        map.put("property:populationCensus", "ontology:Country"); 
        map.put("ontology:capital","ontology:Country");
        map.put("ontology:areaTotal", "ontology:Country");
        map.put("ontology:Leader", "ontology:Person");
    }
    
    public String[] getOntology(String key) {
        key = key.toLowerCase();
        switch(key) {
            case "population" :
                return new String[] {"property:populationCensus", map.get("property:populationCensus")};
            case "capital":
            case "capitale":
                return new String[] {"ontology:capital", map.get("ontology:capital")};
            case "surface" :
            case "taille" :
                return new String[] {"ontology:areaTotal", map.get("ontology:areaTotal")};
            case "président" :
            case "president" :
                return new String[] {"ontology:Leader", map.get("ontology:Leader")};
            default :
                {
                    try {
                        throw new Exception(key + " is unrecognized at " + this.getClass().getCanonicalName());
                    } catch (Exception ex) {
                        Logger.getLogger(OntologyDefiner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
        return null;
    }
}
