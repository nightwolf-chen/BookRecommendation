/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nightwolf
 */
public class JSONPRequestProccessor {

    public static void proccessJSONPRequest(HttpServletRequest request, HttpServletResponse response, String json) throws IOException {

        PrintWriter out = response.getWriter();
        String callback = request.getParameter("callback");
        JSONPGenerator jsonpG = new JSONPGenerator(json, callback);
       
        try {
            out.write(jsonpG.getJsonp());
        } finally {
            out.close();
        }
        
    }
}
