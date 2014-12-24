package main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Path("delete")
public class DeleteResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DeleteResource
     */
    public DeleteResource() {
    }

    /**
     * Retrieves representation of an instance of main.DeleteResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        java.sql.Connection conn = Connection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM POSITION");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "<done/>";
    }

    /**
     * PUT method for updating or creating an instance of DeleteResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
