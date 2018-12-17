/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Location;
import Model.Person;
import Model.PersonProperty;
import Model.Types;
import Model.SomeThing;
import Model.SomeThingProperty;
import Model.Unrecognized;
import Model.UserRequest;
import java.util.ArrayList;

/**
 *
 * @author CodeFox
 */
public class Tagger {
    
    private final String[] somethingPropertyFr = {"quel est le.*de.*$", "quelle est la.*de.*$"};
    private final String[] somethingPropertyEn = { "what is the.*of.*$","what is.*'s.*$",
                                                   "what's the.*of.*","what's the.*'s.*$", "what's .*'s.*$"};
    private final String[] somethingFr = {"qu'est-ce que.*$"};
    private final String[] somethingEn = {"what's.*$", "what is.*$"};
    private final String[] personPropertyFr = {"qui est le .* de.*$"};
    private final String[] personPropertyEn = {"who is the .* of.*$", "who is .*'s.*$", 
                                       "who's the .* of.*$", "who's .*'s.*$"};
    private final String[] locationFr = {"où est.*$", "où se trouve.*$" } ;
    private final String[] locationEn = {"where is.*$", "where's.*$"};
    private final String[] personFr = {"qui est.*$" };
    private final String[] personEn = {"who's.*$", "who is.*$"};
    
    private ArrayList<Types> tags;
    private Unrecognized unrecognized;
    
    public Tagger() {
        this.tags = new ArrayList<>();
        tags.add(new SomeThingProperty(somethingPropertyFr,somethingPropertyEn));
        tags.add(new PersonProperty(personPropertyFr, personPropertyEn));
        tags.add(new SomeThing(somethingFr, somethingEn));
        tags.add(new Location(locationFr, locationEn));
        tags.add(new Person(personFr,personEn));
        this.unrecognized = new Unrecognized(null,null);
    }
    
    public void tag(UserRequest request) {
        boolean ok = false;
        for(int i = 0; i < tags.size() && !ok; i++) {
            if(tags.get(i).belongToFr(request.getRequest())) {
                request.setType(tags.get(i)); 
                request.setLanguage("fr");
                ok = true;
            } else if(tags.get(i).belongToEn(request.getRequest())) {
                request.setType(tags.get(i)); 
                request.setLanguage("en");
                ok = true;
            }
        }
        if(!ok)
            request.setType(unrecognized);
    }
    

    
    public String[] extractValues(UserRequest request) {
        return request.getTypes().extractValues(request);
    }

}
