/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author nightwolf
 */
public class JSONPGenerator {
    
    private String jsonp = "";
    
    public JSONPGenerator(String jsonStr,String callback) {
        jsonp +=  (callback + "(" + jsonStr + ");");
        jsonp += callback;
    }

    public String getJsonp() {
        return jsonp;
    }
    
}
