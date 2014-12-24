package main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TournamentDecider {

    public static String decide(String tourney) throws Exception {
        if (Pattern.matches(Constants.TOURNEY, tourney)) {
            Tournament t1 = buildTourney(tourney);
            int i = 0;

            StringBuffer temp = new StringBuffer("[[[");

            while (i < t1.tourney.size()) {

                temp.append(GameDecider.decide(t1.tourney.get(i)));

                if (i % 2 == 1 && i != t1.tourney.size() - 1) {
                    temp.append("],[");
                } else if (i == t1.tourney.size() - 1) {
                    temp.append("]]");
                } else {
                    temp.append(",");
                }
                i++;

            }
            temp.append("]");
            if (Pattern.matches(Constants.TOURNEY, temp.toString())) {

                return decide(temp.toString());
            } else {
                return temp.toString();
            }
        }
        return null;
    }

    public static Tournament buildTourney(String t1) {

        Tournament t = new Tournament();
        t.tourney = new ArrayList<Game>();
        Game.GameBuilder gb = new Game.GameBuilder();
        Pattern p = Pattern.compile(Constants.CONTENT);
        Matcher m = p.matcher(t1);
        String p1 = "", p2 = "", s1 = "", s2 = "";
        int i = 0;

        while (m.find()) {
            int helper = i % 4;
            switch (helper) {
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
            if (Pattern.matches(Constants.STRATEGY, s1) && Pattern.matches(Constants.STRATEGY, s2)) {

                Game g = new Game(gb.player1(p1).player2(p2).strategy1(s1).strategy2(s2));
                t.tourney.add(g);
                p1 = "";
                p2 = "";
                s1 = "";
                s2 = "";

            }
        }

        return t;
    }
}
