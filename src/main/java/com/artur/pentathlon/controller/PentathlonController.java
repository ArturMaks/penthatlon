package com.artur.pentathlon.controller;

import com.artur.pentathlon.model.Competitor;
import com.artur.pentathlon.service.InputService;
import com.artur.pentathlon.service.ScoreCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

@Controller
public class PentathlonController {

    private final InputService inputService;
    private final ScoreCalculationService scoreCalculationService;

    @Autowired
    public PentathlonController(final InputService inputService, final ScoreCalculationService scoreCalculationService) {
        this.inputService = inputService;
        this.scoreCalculationService = scoreCalculationService;
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<Competitor>> entry() {
        String filename = "static/Athlete_Results.csv";
        List<Competitor> competitors = null;
        try {
            competitors = inputService.parseCompetitors(filename);
            competitors = scoreCalculationService.calculateScores(competitors);
        } catch (FileNotFoundException | ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(competitors, HttpStatus.OK);
    }
}
