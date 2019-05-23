package com.artur.pentathlon.service;

import com.artur.pentathlon.model.Competitor;

import java.io.FileNotFoundException;
import java.util.List;

public interface InputService {

    List<Competitor> parseCompetitors(String filename) throws FileNotFoundException;
}
