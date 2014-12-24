package main;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {
    
    public static java.sql.Connection getConnection() {
        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          return DriverManager.getConnection("jdbc:mysql://localhost:3306/users","dev","HpT3st%");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void storeWinner(String name) {
        java.sql.Connection conn = getConnection();
        int i = 1;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM POSITION WHERE USERNAME = ?");
            ps.setString(i, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PreparedStatement ps2 = conn.prepareStatement("UPDATE POSITION SET POINTS = POINTS + 3 WHERE USERNAME = ?");
                ps2.setString(i, name);
                ps2.executeUpdate();
            }
            else {
                PreparedStatement ps2 = conn.prepareStatement("INSERT INTO POSITION (USERNAME, POINTS) VALUES ('"+name+"', 3)");
                ps2.executeUpdate();
            }
            conn.close();
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
    
}
