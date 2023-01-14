package com.simon.sporttask.model;

public enum MatchResult {
    HOME_TEAM_WINNER(0),
    DRAW(1),
    AWAY_TEAM_WINNER(2);

    private final int index;

    MatchResult(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
}