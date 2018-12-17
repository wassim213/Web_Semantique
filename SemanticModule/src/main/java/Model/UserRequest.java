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
public class UserRequest {
    private String request;
    private Types type;
    private String[] params;
    private String language ; 
    
    public UserRequest(String request, Types type) {
        this.request = request;
        this.type = type;
    }
    
    public UserRequest(String request) {
        this.request = request.toLowerCase();
    }
    
    public String[] getParams(){
        return params;
    }
    
    public void setType(Types type) {
        this.type = type;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setParams(String[] params) {
        for(int i = 0; i < params.length; i++) {
            String[] tmp = params[i].split(" ");
            if(tmp.length > 0) {
                String ss = "";
                for(int j = 0; j < tmp.length; j++) {
                    ss += tmp[j].substring(0, 1).toUpperCase() + tmp[j].substring(1) + " ";
                }
                ss = ss.substring(0,ss.length() - 1);
                params[i] = ss;
            } else
                params[i] = params[i].substring(0, 1).toUpperCase() + params[i].substring(1);
        }
        this.params = params;
    }
    
    public String getRequest() {
        return request;
    }
    
    public Types getTypes() {
        return type;
    }
    
    public void debugg() {
        System.out.println("Request: " + request);
        System.out.println("Language:" + language);
        System.out.println("Params:");
        System.out.println("Type:" + type.getClass().getCanonicalName());
        for(int i = 0; i < params.length; i++)
            System.out.println(params[i]);
    }
}
