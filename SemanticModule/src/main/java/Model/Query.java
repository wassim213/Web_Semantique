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
public class Query {
    private String query;
    
    public Query(String query) {
        this.query = query;
    }
    
    public String getQuery() {
        return query;
    }

    public void debugg() {
        System.out.println(query);
    }
}
