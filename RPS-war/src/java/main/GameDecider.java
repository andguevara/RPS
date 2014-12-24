/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameDecider {

    public static void main(String[] args) {
        try {
            System.out.println(GameDecider.decide("[[ \"Armando\", \"P\" ], [ \"Dave\", \"S\" ]]"));

        } catch (Exception ex) {
            Logger.getLogger(GameDecider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String decide(Game g) throws Exception {
        return decide("[[\""+g.getPlayer1()+"\", \""+g.getStrategy1()+"\"], [\""+g.getPlayer2()+"\", \""+g.getStrategy2()+"\"]]");
    }
 
    public static String decide(String input) throws Exception {

        if (Pattern.matches(Constants.GAME, input)) {
            Game.GameBuilder gb = new Game.GameBuilder();
            Pattern p = Pattern.compile(Constants.CONTENT);
            Matcher m = p.matcher(input);
            int i = 0;
            String p1 = "", p2 = "", s1 = "", s2 = "";
            while (m.find()) {

                switch (i) {
                    case 0:
                        p1 = m.group();
                        break;
                    case 1:
                        s1 = m.group();
                        break;
                    case 2:
                        p2 = m.group();
                        break;
                    case 3:
                        s2 = m.group();
                        break;
                    default:
                        break;
                }

                i++;

            }

            if (Pattern.matches(Constants.STRATEGY, s1) && Pattern.matches(Constants.STRATEGY, s2)) {

                Game g = new Game(gb.player1(p1).player2(p2).strategy1(s1).strategy2(s2));

                if (g.getStrategy1().equals(g.getStrategy2()) || beats(g.getStrategy1(), g.getStrategy2())) {
                    return winner(g.getPlayer1(), g.getStrategy1());
                } else {
                    return winner(g.getPlayer2(), g.getStrategy2());
                }
            } else {
                throw new Exception("Wrong strategy");
            }
        } else {
            throw new Exception("Bad formatted Game");
        }

    }

    public static String winner(String p1, String s1) {
        return "[\"" + p1 + "\", \"" + s1 + "\"]";
    }

    public static boolean beats(String p1, String p2) {
        switch (p1) {
            case "R":
                if (p2.equals("S")) {
                    return true;
                } else {
                    return false;
                }
            case "P":
                if (p2.equals("R")) {
                    return true;
                } else {
                    return false;
                }
            case "S":
                if (p2.equals("P")) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }

    }

    public static Game beats(Game g1, Game g2) {
        if (beats(g1.getStrategy1(), g2.getStrategy2())) {
            return g1;
        }
        else 
            return g2;
    }
}
