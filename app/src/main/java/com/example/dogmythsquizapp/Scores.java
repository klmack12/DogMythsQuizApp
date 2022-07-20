package com.example.dogmythsquizapp;

public class Scores {
        String playerName;
        int playerScore;

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

