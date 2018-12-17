/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utils.OntologyDefiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author CodeFox
 */
public abstract class Types {
    
    protected final String header = 
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
	"PREFIX : <http://dbpedia.org/resource/>\n" + 
	"PREFIX property: <http://dbpedia.org/property/>\n" +
	"PREFIX ontology: <http://dbpedia.org/ontology/>\n" +
	"PREFIX resource: <http://dbpedia.org/resource/>\n";
    
    protected String[] keysEn;
    protected String[] keysFr;
    protected OntologyDefiner ontologyDefiner ;
    public Types(String[] keysFr, String[] keysEn) {
        this.keysEn = keysEn;
        this.keysFr = keysFr;
        this.ontologyDefiner = new OntologyDefiner();
    }
    
    public boolean belongToFr(String str) {
        
        String tmp = str.toLowerCase();
        for(int i = 0; i < keysFr.length; i++){
            Pattern pattern = Pattern.compile(keysFr[i]);
            Matcher matcher = pattern.matcher(tmp);
            if(matcher.matches())
                return true;
        }
        return false;
    }
    
    public boolean belongToEn(String str) {
        String tmp = str.toLowerCase();
                System.out.println("elt: " + tmp);
        for(int i = 0; i < keysEn.length; i++) {
            System.out.println("key:" + keysEn[i]);
            Pattern pattern = Pattern.compile(keysEn[i]);
            Matcher matcher = pattern.matcher(tmp);
            if(matcher.matches()){
                return true;
            }
        }
        return false;
    }
    
    public String sparqlQuery(UserRequest request) {
        return header + getSparqlQuery(request);
    }
    
    public abstract String getSparqlQuery(UserRequest request);

    public abstract String[] extractValues(UserRequest request);

}
