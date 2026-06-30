package com.eightfold.merger;

import com.eightfold.model.Candidate;
import com.eightfold.model.Education;
import com.eightfold.model.Experience;
import com.eightfold.model.Link;
import com.eightfold.model.Skill;
import com.eightfold.model.Provenance;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CandidateMerger {

    public Candidate merge(Candidate recruiter, Candidate resume) {

        Candidate merged = new Candidate();

        // ---------------- Name ----------------

        if (recruiter.getFullName() != null &&
                !recruiter.getFullName().trim().isEmpty()) {

            merged.setFullName(recruiter.getFullName());

        } else {

            merged.setFullName(resume.getFullName());

        }

        // ---------------- Emails ----------------

        Set<String> emails = new LinkedHashSet<>();

        if (recruiter.getEmails() != null)
            emails.addAll(recruiter.getEmails());

        if (resume.getEmails() != null)
            emails.addAll(resume.getEmails());

        merged.setEmails(new ArrayList<>(emails));

        // ---------------- Phones ----------------

        Set<String> phones = new LinkedHashSet<>();

        if (recruiter.getPhones() != null)
            phones.addAll(recruiter.getPhones());

        if (resume.getPhones() != null)
            phones.addAll(resume.getPhones());

        merged.setPhones(new ArrayList<>(phones));

        // ---------------- Skills ----------------

        Map<String, Skill> skillMap = new LinkedHashMap<>();

        if (recruiter.getSkills() != null) {
            for (Skill skill : recruiter.getSkills()) {
                skillMap.put(skill.getName().toLowerCase(), skill);
            }
        }

        if (resume.getSkills() != null) {
            for (Skill skill : resume.getSkills()) {
                skillMap.putIfAbsent(skill.getName().toLowerCase(), skill);
            }
        }

        merged.setSkills(new ArrayList<>(skillMap.values()));

        // ---------------- Education ----------------

        List<Education> education = new ArrayList<>();

        if (recruiter.getEducation() != null)
            education.addAll(recruiter.getEducation());

        if (resume.getEducation() != null)
            education.addAll(resume.getEducation());

        merged.setEducation(education);

        // ---------------- Experience ----------------

        List<Experience> experience = new ArrayList<>();

        if (recruiter.getExperience() != null)
            experience.addAll(recruiter.getExperience());

        if (resume.getExperience() != null)
            experience.addAll(resume.getExperience());

        merged.setExperience(experience);

        // ---------------- Headline ----------------

        if (recruiter.getHeadline() != null &&
                !recruiter.getHeadline().trim().isEmpty()) {

            merged.setHeadline(recruiter.getHeadline());

        } else {

            merged.setHeadline(resume.getHeadline());

        }

        // ---------------- Years of Experience ----------------

        if (recruiter.getYearsExperience() != null) {

            merged.setYearsExperience(recruiter.getYearsExperience());

        } else {

            merged.setYearsExperience(resume.getYearsExperience());

        }

        // ---------------- Location ----------------

        if (recruiter.getLocation() != null) {

            merged.setLocation(recruiter.getLocation());

        } else {

            merged.setLocation(resume.getLocation());

        }

        // ---------------- Links ----------------

        List<Link> links = new ArrayList<>();

        if (recruiter.getLinks() != null)
            links.addAll(recruiter.getLinks());

        if (resume.getLinks() != null)
            links.addAll(resume.getLinks());

        merged.setLinks(links);

        // ---------------- Candidate Id ----------------

        if (recruiter.getCandidateId() != null) {

            merged.setCandidateId(recruiter.getCandidateId());

        } else {

            merged.setCandidateId(resume.getCandidateId());

        }

        // ---------------- Confidence ----------------
        List<Provenance> provenance = new ArrayList<>();

        provenance.add(new Provenance(
                "fullName",
                "Recruiter CSV",
                "Priority Merge"
        ));

        provenance.add(new Provenance(
                "emails",
                "Recruiter CSV + Resume",
                "Merged"
        ));

        provenance.add(new Provenance(
                "phones",
                "Recruiter CSV + Resume",
                "Normalized & Merged"
        ));

        provenance.add(new Provenance(
                "skills",
                "Resume",
                "Resume Parser"
        ));

        provenance.add(new Provenance(
                "education",
                "Resume",
                "Resume Parser"
        ));

        provenance.add(new Provenance(
                "experience",
                "Resume",
                "Resume Parser"
        ));

        merged.setProvenance(provenance);
        double confidence = 0.0;
        int totalFields = 0;

// Full Name
        totalFields++;
        if (merged.getFullName() != null && !merged.getFullName().trim().isEmpty()) {
            confidence++;
        }

// Emails
        totalFields++;
        if (merged.getEmails() != null && !merged.getEmails().isEmpty()) {
            confidence++;
        }

// Phones
        totalFields++;
        if (merged.getPhones() != null && !merged.getPhones().isEmpty()) {
            confidence++;
        }

// Skills
        totalFields++;
        if (merged.getSkills() != null && !merged.getSkills().isEmpty()) {
            confidence++;
        }

// Education
        totalFields++;
        if (merged.getEducation() != null && !merged.getEducation().isEmpty()) {
            confidence++;
        }

// Experience
        totalFields++;
        if (merged.getExperience() != null && !merged.getExperience().isEmpty()) {
            confidence++;
        }

// Headline
        totalFields++;
        if (merged.getHeadline() != null && !merged.getHeadline().trim().isEmpty()) {
            confidence++;
        }

// Location
        totalFields++;
        if (merged.getLocation() != null) {
            confidence++;
        }

// Years of Experience
        totalFields++;
        if (merged.getYearsExperience() != null) {
            confidence++;
        }

        merged.setOverallConfidence(confidence / totalFields);
        return merged;
    }
}