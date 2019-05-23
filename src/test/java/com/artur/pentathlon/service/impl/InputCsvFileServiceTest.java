package com.artur.pentathlon.service.impl;

import com.artur.pentathlon.model.Competitor;
import com.artur.pentathlon.service.InputService;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class InputCsvFileServiceTest {

    private static final String TEST_NAME = "Dennis Bowsher";

    private final InputService inputService = new InputCsvFileService();
    private final DefaultScoreCalculationService scoreCalculationService = new DefaultScoreCalculationService();

    @Test
    public void testParseFile_success() throws Exception {
        List<Competitor> results = inputService.parseCompetitors("test_Athlete_Results.csv");

        assertFalse("Results should not be empty", results.isEmpty());
        assertTrue("First results element name should be '"+ TEST_NAME +"'",
                results.get(0).getName().equals(TEST_NAME));
    }

    @Test
    public void testConcludingEvTimeCalculation () throws FileNotFoundException, ParseException {
        List<Competitor> competitors = inputService.parseCompetitors("test_Athlete_Results.csv");
        List<Competitor> result = scoreCalculationService.calculateScores(competitors);
        Assert.assertEquals("Should be -12", result.get(0).getConcludingEvTime(),-12);
    }
}
