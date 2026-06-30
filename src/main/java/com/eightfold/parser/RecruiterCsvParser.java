package com.eightfold.parser;

import com.eightfold.model.Candidate;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.Collections;

public class RecruiterCsvParser {

    public Candidate parse(String filePath) throws Exception {

        CSVReader reader = new CSVReader(new FileReader(filePath));

        reader.readNext(); // Skip Header

        String[] row = reader.readNext();

        Candidate candidate = new Candidate();

        candidate.setCandidateId("1");
        candidate.setFullName(row[0]);
        candidate.setEmails(Collections.singletonList(row[1]));
        candidate.setPhones(Collections.singletonList(row[2]));

        reader.close();

        return candidate;
    }
}