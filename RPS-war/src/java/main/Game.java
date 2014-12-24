/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

public class Game {

    private String player1, player2;
    private String strategy1, strategy2;

    public Game(GameBuilder builder) {

        this.player1 = builder.player1;
        this.player2 = builder.player2;
        this.strategy1 = builder.strategy1;
        this.strategy2 = builder.strategy2;
                
    }

    public static class GameBuilder {

        private String player1, player2;
        private String strategy1, strategy2;

        
        public  GameBuilder player1 (String player1) {
            this.player1 = player1;
            return this;
        }
        public GameBuilder player2(String player2) {
            this.player2 = player2;
            return this;
        }
        public GameBuilder strategy1(String strategy1) {
            this.strategy1 = strategy1;
            return this;
        }
        public GameBuilder strategy2(String strategy2) {
            this.strategy2 = strategy2;
            return this;
        }
        public Game build() {
            return new Game(this);
        }
    }
    
    public String toString() {
        return "[[\""+player1+"\", \""+strategy1+"\"], [\""+player2+"\", \""+strategy2+"\"]]";
    }

    public String getPlayer1() {
        return player1;
    }
    
    public String getPlayer2() {
        return player2;
    }

    public String getStrategy1() {
        return strategy1;
    }

    public String getStrategy2() {
        return strategy2;
    }

}
