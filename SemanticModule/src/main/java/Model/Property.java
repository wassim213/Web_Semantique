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
public abstract class Property extends Types{
  
    public Property(String[] keysFr, String[] keysEn) {
        super(keysFr, keysEn);
    }
  
    @Override
    public abstract String getSparqlQuery(UserRequest request);

}
