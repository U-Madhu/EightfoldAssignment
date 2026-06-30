package com.eightfold.validation;

import com.eightfold.model.Candidate;

public class CandidateValidator {

    public boolean validate(Candidate candidate) {

        if (candidate == null) {
            return false;
        }

        if (candidate.getFullName() == null ||
                candidate.getFullName().trim().isEmpty()) {

            System.out.println("Validation Warning : Missing Candidate Name");

        }

        if (candidate.getEmails() == null ||
                candidate.getEmails().isEmpty()) {

            System.out.println("Validation Warning : Missing Email");

        }

        if (candidate.getPhones() == null ||
                candidate.getPhones().isEmpty()) {

            System.out.println("Validation Warning : Missing Phone");

        }
        System.out.println("Validation Passed");

        return true;
    }
}