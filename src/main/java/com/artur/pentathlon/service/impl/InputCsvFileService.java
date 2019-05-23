package com.artur.pentathlon.service.impl;

import com.artur.pentathlon.model.Competitor;
import com.artur.pentathlon.service.InputService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class InputCsvFileService implements InputService {

    @Override
    public List<Competitor> parseCompetitors(final String filename) throws FileNotFoundException {

        URL url = ClassLoader.getSystemClassLoader().getResource(filename);

        File file;

        if (url != null) {
            file = new File(url.getFile());
        } else {
            throw new FileNotFoundException("Cannot find file");
        }

        Scanner input = new Scanner(file);
        List<Competitor> competitors = new ArrayList<>();
        while(input.hasNextLine()) {
            String[] competitorArray = input.nextLine().split(",");
            String name = competitorArray[0];
            int fencingVictories = Integer.valueOf(competitorArray[1]);
            String swimmingTime = competitorArray[2];
            int ridingKnockDown = Integer.valueOf(competitorArray[3]);
            int ridingRefusal = Integer.valueOf(competitorArray[4]);
            int ridingDisobedience = Integer.valueOf(competitorArray[5]);
            int shootingScore = Integer.valueOf(competitorArray[6]);
            String runningTime = competitorArray[7];
            int points = 0;
            int concludingEvTime = 0;

            competitors.add(new Competitor(name, fencingVictories,  swimmingTime,
                    ridingKnockDown,  ridingRefusal,  ridingDisobedience,  shootingScore,
                    runningTime, points, null, concludingEvTime));
        }
        return competitors;
    }
}
