package main;

public class Constants {
    
    public static final String GAME = "(\\s)*\\[(\\s)*(\\[(\\s)*\"(\\w)+\"(\\s)*,(\\s)*\"\\w+\"(\\s)*\\](\\s)*,*(\\s)*){2}\\]";    
    public static final String TOURNEY = "\\[(\\s)*(\\[(\\s)*("+GAME+",*){1,2}(\\s)*\\](\\s)*,*(\\s)*)+\\]";    
    public static final String CONTENT = "(\\w+)";
    public static final String STRATEGY = "[RPS]";
   
}
