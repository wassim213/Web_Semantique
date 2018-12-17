/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author CodeFox
 */
public class JsonParser {
    
    public ArrayList<String> normalize(String res) {
        ArrayList<String> result = new ArrayList<>();
    	JSONObject json = new JSONObject(res.toString());
        JSONObject results = (JSONObject) json.get("results");
        JSONArray bindings = (JSONArray) results.get("bindings");
        for (Object response : bindings) {
            JSONObject reponseObjet = new JSONObject(response.toString());
            JSONObject reponse = reponseObjet.getJSONObject("result");
            result.add((String) reponse.get("value"));
	}
        return result;
    }
    
}
