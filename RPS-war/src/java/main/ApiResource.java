/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author Jeans
 */
@Path("api")
public class ApiResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
    }

    /**
     * Retrieves representation of an instance of main.ApiResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    @Path("/api/championship/result")
    @POST
    @Produces("application/json")
    public String result(@FormParam("first") String first, @FormParam("second") String second) {
        Connection.storeWinner(first);
        return "{ status : 'success'}";
    }

    @Path("/api/championship/new")
    @POST
    @Produces("application/json")
    public String newtourney(@FormParam("data") String data) {
        String result = null;
        try {
            result = TournamentDecider.decide(data);
        } catch (Exception ex) {
            Logger.getLogger(ApiResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "{ winner : " + result + " }";
    }

    @Path("/api/championship/top")
    @GET
    @Produces("application/json")
    public String top(@DefaultValue("10") @QueryParam("count") int count) {
        java.sql.Connection conn = Connection.getConnection();
        PreparedStatement ps;
        String result =  "{ players : ";
        try {
            ps = conn.prepareStatement("select username from position order by points desc limit 10");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = result + "\"" + rs.getString("username") + "\",";
            }

        } catch (SQLException ex) {
            Logger.getLogger(ApiResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;

    }
}
