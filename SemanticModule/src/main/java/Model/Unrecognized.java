/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author CodeFox
 */
public class Unrecognized extends Types {

    public Unrecognized(String[] keysFr, String[] keysEn) {
        super(keysFr, keysEn);
    }
  
    public boolean belongTo(String str) {
        return true;
    }

    @Override
    public String getSparqlQuery(UserRequest request) {
        return "SELECT * WHERE { ?x ?y ?z} LIMIT(10).";
    }

    @Override
    public String[] extractValues(UserRequest request) {
        return new String[0];
    }
}
