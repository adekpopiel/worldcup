package com.adekpopiel.worldcup.controller.model;

import com.adekpopiel.worldcup.domain.entity.Game;

import java.util.UUID;

public class GameDto {
    private UUID id;
    private String homeTeam;
    private String visitors;
    private Integer homeTeamScore;
    private Integer visitorsScore;
    private String startTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getVisitors() {
        return visitors;
    }

    public void setVisitors(String visitors) {
        this.visitors = visitors;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Integer getVisitorsScore() {
        return visitorsScore;
    }

    public void setVisitorsScore(Integer visitorsScore) {
        this.visitorsScore = visitorsScore;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Game toGame() {
        return Game.builder()
                .id(id)
                .homeTeam(homeTeam)
                .homeTeamScore(homeTeamScore)
                .visitors(visitors)
                .visitorsScore(visitorsScore)
                .startTime(startTime)
                .build();
    }

    public static GameDto toGameDto(final Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setHomeTeam(game.getHomeTeam());
        gameDto.setHomeTeamScore(game.getHomeTeamScore());
        gameDto.setVisitors(game.getVisitors());
        gameDto.setVisitorsScore(game.getVisitorsScore());
        gameDto.setStartTime(game.getStartTime());

        return gameDto;
    }
}
