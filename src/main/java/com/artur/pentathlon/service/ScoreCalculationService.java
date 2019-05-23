package com.artur.pentathlon.service;

import com.artur.pentathlon.model.Competitor;

import java.text.ParseException;
import java.util.List;

public interface ScoreCalculationService {

    List<Competitor> calculateScores(List<Competitor> competitors) throws ParseException;
}
