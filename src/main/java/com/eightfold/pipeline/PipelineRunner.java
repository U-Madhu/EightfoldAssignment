package com.eightfold.pipeline;

import com.eightfold.config.ConfigLoader;
import com.eightfold.merger.CandidateMerger;
import com.eightfold.model.Candidate;
import com.eightfold.normalizer.CandidateNormalizer;
import com.eightfold.parser.RecruiterCsvParser;
import com.eightfold.parser.ResumeParser;
import com.eightfold.projection.ProjectionEngine;
import com.eightfold.validation.CandidateValidator;
import com.eightfold.writer.CandidateJsonWriter;

import java.util.Map;

public class PipelineRunner {

    public void transform() {

        try {

            System.out.println("==================================");
            System.out.println("Eightfold Candidate Transformer");
            System.out.println("==================================");

            System.out.println("\nStep 1 : Reading Recruiter CSV...");

            RecruiterCsvParser csvParser = new RecruiterCsvParser();

            Candidate recruiterCandidate =
                    csvParser.parse("input/recruiter.csv");

            System.out.println("✓ Recruiter CSV Loaded");

            System.out.println("Candidate : "
                    + recruiterCandidate.getFullName());

            System.out.println("\nNext Step : Resume Parsing...");
            ResumeParser resumeParser = new ResumeParser();

            Candidate resumeCandidate =
                    resumeParser.parse("input");

            System.out.println("✓ Resume Loaded");

            System.out.println("Resume Name : "
                    + resumeCandidate.getFullName());
            System.out.println("\nStep 3 : Merging Candidate Data...");

            CandidateMerger merger = new CandidateMerger();

            Candidate finalCandidate = merger.merge(recruiterCandidate, resumeCandidate);
            // Load Runtime Configuration
            ConfigLoader loader = new ConfigLoader();

            System.out.println("\nLoading Runtime Configuration...");

            System.out.println(loader.loadFields("config/config.json"));

// Normalize
            CandidateNormalizer normalizer = new CandidateNormalizer();
            finalCandidate = normalizer.normalize(finalCandidate);
            CandidateValidator validator = new CandidateValidator();

            validator.validate(finalCandidate);

// Write JSON AFTER normalization
            ProjectionEngine projection =
                    new ProjectionEngine();

            Map<String, Object> output =
                    projection.project(
                            finalCandidate,
                            loader.loadFields("config/config.json")
                    );

            CandidateJsonWriter writer =
                    new CandidateJsonWriter();

            writer.write(
                    output,
                    "output/candidate.json"
            );



            System.out.println("\n===== FINAL CANDIDATE =====");

            System.out.println("Name   : " + finalCandidate.getFullName());

            System.out.println("Emails : " + finalCandidate.getEmails());

            System.out.println("Phones : " + finalCandidate.getPhones());

            System.out.println();

            System.out.println("Skills :");

            if (finalCandidate.getSkills() != null) {

                for (var skill : finalCandidate.getSkills()) {

                    System.out.println("   - " + skill.getName());

                }

            }

            System.out.println();

            System.out.println("Education :");

            if (finalCandidate.getEducation() != null) {

                for (var education : finalCandidate.getEducation()) {

                    System.out.println("   - " + education.getInstitution());

                }

            }

            System.out.println();

            System.out.println("Experience :");

            if (finalCandidate.getExperience() != null) {

                for (var experience : finalCandidate.getExperience()) {

                    System.out.println("   - " + experience.getTitle()
                            + " @ "
                            + experience.getCompany());

                }

            }

            System.out.println();

            System.out.printf("Overall Confidence : %.2f%n",
                    finalCandidate.getOverallConfidence());

            System.out.println();

            System.out.println("JSON generated successfully in output/candidate.json");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}