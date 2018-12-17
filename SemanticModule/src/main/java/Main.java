
import Model.Query;
import Model.UserRequest;
import Utils.DataBaseHandler;
import Utils.RequestFactory;
import Utils.SparqlFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CodeFox
 */
public class Main {
    
    public static void main(String[] args) {
        String elt = "Quelle est la capitale de France?";
        RequestFactory factory = new RequestFactory();
        UserRequest r = factory.make(elt);
        r.debugg();
        SparqlFactory sp = new SparqlFactory();
        Query q = sp.make(r);
        q.debugg();
        DataBaseHandler db = new DataBaseHandler();
        try {    
            db.connect(q);
            ArrayList<String> p = db.getResults();
            if(p.size() == 0) {
                System.out.println("No matching results found!");
            }
            for(int i = 0; i < p.size(); i++) {
                System.out.println(p.get(i));
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

