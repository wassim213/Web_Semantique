/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Query;
import Model.UserRequest;
/**
 *
 * @author CodeFox
 */
public class SparqlFactory extends Fabrique<Query, UserRequest>{
    private Tagger tagger;
    
    public SparqlFactory() {
        this.tagger = new Tagger();
    }
    
    @Override
    public Query make(UserRequest request) {
        return new Query(request.getTypes().sparqlQuery(request));
    }
}
