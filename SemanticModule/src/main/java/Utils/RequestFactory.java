/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import Model.UserRequest;
import Model.Types;

/**
 *
 * @author CodeFox
 */
public class RequestFactory extends Fabrique<UserRequest, String>{
    
    private Tagger tagger;
    
    public RequestFactory() {
        this.tagger = new Tagger();
    }
    
    @Override
    public UserRequest make(String sentence) {
        UserRequest userRequest = new UserRequest(sentence);
        tagger.tag(userRequest);
        userRequest.setParams(tagger.extractValues(userRequest));
        return userRequest;
    }
}
