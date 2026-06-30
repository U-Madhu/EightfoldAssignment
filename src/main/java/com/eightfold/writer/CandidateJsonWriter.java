package com.eightfold.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;

public class CandidateJsonWriter {

    public void write(Object object,
                      String outputPath) {

        try {

            File outputFile = new File(outputPath);

            if (outputFile.getParentFile() != null &&
                    !outputFile.getParentFile().exists()) {

                outputFile.getParentFile().mkdirs();

            }

            ObjectMapper mapper = new ObjectMapper();

            mapper.enable(
                    SerializationFeature.INDENT_OUTPUT);

            mapper.writeValue(outputFile,
                    object);

            System.out.println();

            System.out.println(
                    "======================================");

            System.out.println(
                    "Generating Canonical Candidate Profile...\n" +
                            "\n" +
                            "Projection Applied\n" +
                            "\n" +
                            "Writing JSON...\n" +
                            "\n" +
                            "Done.");

            System.out.println(
                    "Location : "
                            + outputFile.getAbsolutePath());

            System.out.println(
                    "======================================");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}