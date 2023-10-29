package com.adekpopiel.worldcup.domain.entity;

import java.util.UUID;

public class Game {
    private static final String GAME_TEMPLATE = "%s %d - %s %d";
    private final UUID id;
    private final String homeTeam;
    private final String visitors;
    private final Integer homeTeamScore;
    private final Integer visitorsScore;
    private final String startTime;

    private Game(UUID id, String homeTeam, String visitors, Integer homeTeamScore, Integer visitorsScore, String startTime) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.visitors = visitors;
        this.homeTeamScore = homeTeamScore;
        this.visitorsScore = visitorsScore;
        this.startTime = startTime;
    }

    public static GameBuilder builder() {
        return new GameBuilder();
    }
    public static class GameBuilder {
        private UUID id;
        private String homeTeam;
        private String visitors;
        private Integer homeTeamScore;
        private Integer visitorsScore;
        private String startTime;

        GameBuilder() {

        }

        public GameBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        public GameBuilder homeTeam(final String homeTeam) {
            this.homeTeam = homeTeam;
            return this;
        }

        public GameBuilder visitors(final String visitors) {
            this.visitors = visitors;
            return this;
        }

        public GameBuilder homeTeamScore(final Integer homeTeamScore) {
            this.homeTeamScore = homeTeamScore;
            return this;
        }

        public GameBuilder visitorsScore(final Integer visitorsScore) {
            this.visitorsScore = visitorsScore;
            return this;
        }

        public GameBuilder startTime(final String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Game build() {
            return new Game(id, homeTeam, visitors, homeTeamScore, visitorsScore, startTime);
        }
    }

    public UUID getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getVisitors() {
        return visitors;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public Integer getVisitorsScore() {
        return visitorsScore;
    }

    public String getStartTime() {
        return startTime;
    }

    public Integer getTotalScore() {
        return homeTeamScore + visitorsScore;
    }

    @Override
    public String toString() {
        return String.format(GAME_TEMPLATE,
                homeTeam,
                homeTeamScore,
                visitors,
                visitorsScore);
    }
}
