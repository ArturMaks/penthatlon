package com.artur.pentathlon.model;

public class Competitor {
    private String name;
    private int points;
    private int fencingVictories;
    private String swimmingTime;
    private int ridingKnockDown;
    private int ridingRefusal;
    private int ridingDisobedience;
    private int shootingScore;
    private String runningTime;
    private String place;
    private int concludingEvTime;

    public Competitor(String name, int fencingVictories, String swimmingTime, int ridingKnockDown, int ridingRefusal, int ridingDisobedience, int shootingScore, String runningTime, int points, String place, int concludingEvTime) {
        this.name = name;
        this.points = points;
        this.fencingVictories = fencingVictories;
        this.swimmingTime = swimmingTime;
        this.ridingKnockDown = ridingKnockDown;
        this.ridingRefusal = ridingRefusal;
        this.ridingDisobedience = ridingDisobedience;
        this.shootingScore = shootingScore;
        this.runningTime = runningTime;
        this.place = place;
        this.concludingEvTime = concludingEvTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFencingVictories() {
        return fencingVictories;
    }

    public void setFencingVictories(int fencingVictories) {
        this.fencingVictories = fencingVictories;
    }

    public String getSwimmingTime() {
        return swimmingTime;
    }

    public void setSwimmingTime(String swimmingTime) {
        this.swimmingTime = swimmingTime;
    }

    public int getRidingKnockDown() {
        return ridingKnockDown;
    }

    public void setRidingKnockDown(int ridingKnockDown) {
        this.ridingKnockDown = ridingKnockDown;
    }

    public int getRidingRefusal() {
        return ridingRefusal;
    }

    public void setRidingRefusal(int ridingRefusal) {
        this.ridingRefusal = ridingRefusal;
    }

    public int getRidingDisobedience() {
        return ridingDisobedience;
    }

    public void setRidingDisobedience(int ridingDisobedience) {
        this.ridingDisobedience = ridingDisobedience;
    }

    public int getShootingScore() {
        return shootingScore;
    }

    public void setShootingScore(int shootingScore) {
        this.shootingScore = shootingScore;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getConcludingEvTime() {
        return concludingEvTime;
    }

    public void setConcludingEvTime(int concludingEvTime) {
        this.concludingEvTime = concludingEvTime;
    }
}
