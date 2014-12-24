package main;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tournament {
    
    List<Game> tourney ;
    
    public static void main (String[] args) {
        String tourney = "["
                + " ["
                + " [ [\"Armando\", \"P\"], [\"Dave\", \"S\"] ],"
                + " [ [\"Richard\", \"R\"], [\"Michael\", \"S\"] ], ],"
                + " ["
                + " [ [\"Allen\", \"S\"], [\"Omer\", \"P\"] ],"
                + " [ [\"John\", \"R\"], [\"Robert\", \"P\"] ] ]]";
        
        try {
            System.out.println(parseResult(TournamentDecider.decide(tourney)));
        } catch (Exception ex) {
            Logger.getLogger(Tournament.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String parseResult(String winner) {
        Pattern p = Pattern.compile(Constants.CONTENT);
        Matcher m = p.matcher(winner);
        StringBuffer result = new StringBuffer("[\"");
        String nameWinner = "";
        int i = 0;
        while (m.find()) {
            switch(i) {
                case 0 : nameWinner = m.group(); 
                result.append(nameWinner).append("\",\""); 
                Connection.storeWinner(nameWinner);
                break;
                case 1 : result.append(m.group()).append("\"]"); break;
                default : break;
            }
            i++;
        }
        return result.toString();
    }
    
    public String toString() {
        StringBuffer result = new StringBuffer("");
        for (Game g : tourney) {
            result.append(g.getPlayer1() + g.getStrategy1() + g.getPlayer2() + g.getStrategy2());
        }
        return result.toString();
    }
}
