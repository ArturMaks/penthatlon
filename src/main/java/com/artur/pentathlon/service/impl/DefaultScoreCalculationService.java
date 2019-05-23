package com.artur.pentathlon.service.impl;

import com.artur.pentathlon.model.Competitor;
import com.artur.pentathlon.service.ScoreCalculationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@Service
public class DefaultScoreCalculationService implements ScoreCalculationService {

    @Override
    public List<Competitor> calculateScores(List<Competitor> competitors) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss.SSS");
        setPoints(competitors);
        String champTime = competitors.get(0).getRunningTime();
        int champPoints = competitors.get(0).getPoints();
        for (int i = 1; competitors.size() > i; i++){
            if(champPoints < competitors.get(i).getPoints()){
                champPoints = competitors.get(i).getPoints();
                champTime = competitors.get(i).getRunningTime();
            }
        }

        for(Competitor comp : competitors){
            if (champPoints == comp.getPoints()){
                comp.setConcludingEvTime(0); // or comp.setConcludingEvTime(comp.getRunningTime())
            } else {
                Long competitorTime = (format.parse(comp.getRunningTime()).getTime())/-1000;
                Long semiChampTime = (format.parse(champTime).getTime())/-1000;
                int seconds = ((champPoints - comp.getPoints())%3)*3;
                comp.setConcludingEvTime(((int)(semiChampTime - competitorTime)) + seconds);
            }

        }

        competitors.sort(new Comparator<Competitor>() {

            @Override
            public int compare(Competitor o1, Competitor o2) {
                return o1.getConcludingEvTime() - o2.getConcludingEvTime();
            }
        });

        setPlaces(competitors);
        return competitors;
    }

    private int countShootingPoints (int shootingScore){
        int points = 1000;
        int result = (shootingScore - 172)*12;
        return points+result;
    }

    private int countFencingPoints (int fencingVictories, List<Competitor> competitors) {
        int points = 1000;
        int minWins = (competitors.size() * 70)/100;
        int result = (fencingVictories - minWins)*40;
        return points+result;
    }

    private int countSwimPoints(String swimmingTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss.SSS");
        int points = 1000;
        String minTime = "02:30.0";

        Long gotTime = (format.parse(swimmingTime).getTime())/-1000;
        Long needTime = (format.parse(minTime).getTime())/-1000;
        int pointsToAdd = (int)(gotTime - needTime) * 12; //3 parts in second * 4 points
        return points + pointsToAdd;
    }

    private int countRidingPoints(int ridingKnockDown, int ridingRefusal, int ridingDisobedience){
        int points = 1200;
        int pointsAway = ridingKnockDown * 28 + ridingRefusal * 40 + ridingDisobedience * 60;
        return points - pointsAway;
    }

    private void setPoints(List<Competitor> competitors) throws ParseException {
        for(Competitor comp : competitors){
            comp.setPoints(countShootingPoints(comp.getShootingScore()) + countFencingPoints(comp.getFencingVictories(),competitors) +
                    countSwimPoints(comp.getSwimmingTime()) + countRidingPoints(comp.getRidingKnockDown(),comp.getRidingRefusal(),comp.getRidingDisobedience()));
        }
    }


    public void setPlaces(List<Competitor> places) {
        for (int i = 0; i < places.size(); i++) {
            Competitor competitor = places.get(i);
            if (StringUtils.isEmpty(competitor.getPlace())) {

                if (places.size() > i + 1 && places.get(i + 1).getConcludingEvTime() == competitor.getConcludingEvTime()) {
                    competitor.setPlace((i + 1) + "-" + (i + 2));
                    places.get(i + 1).setPlace(competitor.getPlace());
                } else {
                    competitor.setPlace(String.valueOf(i + 1));
                }
            }
        }
    }
}
