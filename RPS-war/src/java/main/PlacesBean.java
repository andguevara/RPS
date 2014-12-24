/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeans
 */
public class PlacesBean {

    Map<String, Integer> places;

    public Map<String, Integer> getPlaces() {
        Map result = new HashMap();

        try {
            java.sql.Connection conn = Connection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "select username, points from position order by points desc limit 10");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                Integer points = rs.getInt("points");
                result.put(username, points);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlacesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void setPlaces(Map<String, Integer> places) {
        this.places = places;
    }

}
