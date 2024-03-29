package com.example.dogmythsquizapp;

public class Scores implements Comparable<Scores> {
        public String playerName;
        public int playerScore;

        public int compareTo(Scores m)  //identifies how to sort by the playerScore
        {
                return m.playerScore - this.playerScore;
        }

        public Scores() {
                this.playerName = " ";
                this.playerScore = 0;
        }
        public Scores(String playerName, int playerScore) {
                this.playerName = playerName;
                this.playerScore = playerScore;
        }

        public String getPlayerName() {
                return playerName;
        }

        public void setPlayerName(String playerName) {
                this.playerName = playerName;
        }

        public int getPlayerScore() {
                return playerScore;
        }

        public void setPlayerScore(int playerScore) {
                this.playerScore = playerScore;
        }


}

