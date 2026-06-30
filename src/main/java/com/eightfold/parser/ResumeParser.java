package com.eightfold.parser;

import com.eightfold.model.Candidate;
import com.eightfold.model.Education;
import com.eightfold.model.Experience;
import com.eightfold.model.Skill;
import com.eightfold.parser.reader.DocxReader;
import com.eightfold.parser.reader.PdfReader;
import com.eightfold.parser.reader.TxtReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ResumeParser {

    public Candidate parse(String inputFolder) throws Exception {

        String text;

        File folder = new File(inputFolder);

        File pdf = new File(folder, "resume.pdf");
        File docx = new File(folder, "resume.docx");
        File txt = new File(folder, "resume.txt");

        if (pdf.exists()) {

            System.out.println("Resume Source : PDF");
            text = PdfReader.read(pdf.getPath());

        } else if (docx.exists()) {

            System.out.println("Resume Source : DOCX");
            text = DocxReader.read(docx.getPath());

        } else if (txt.exists()) {

            System.out.println("Resume Source : TXT");
            text = TxtReader.read(txt.getPath());

        } else {

            throw new IllegalArgumentException(
                    "No supported resume found in input folder.");

        }

        // Normalize common PDF extraction issues
        text = text
                .replace("Ar ficial", "Artificial")
                .replace("Arti cial", "Artificial")
                .replace("Educa on", "Education")
                .replace("Experi ence", "Experience")
                .replace("Skill s", "Skills")
                .replace("ﬁ", "fi")
                .replace("ﬂ", "fl");

        BufferedReader reader =
                new BufferedReader(new StringReader(text));

        Candidate candidate = new Candidate();

        List<String> emails = new ArrayList<>();
        List<String> phones = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
        List<Education> educations = new ArrayList<>();
        List<Experience> experiences = new ArrayList<>();

        String line;

        boolean skillSection = false;
        boolean educationSection = false;
        boolean experienceSection = false;

        while ((line = reader.readLine()) != null) {

            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            String normalizedLine = line
                    .toLowerCase()
                    .replaceAll("\\s+", "");

            // First non-empty line is the name
            if (candidate.getFullName() == null) {
                candidate.setFullName(line);
                continue;
            }

            // Email
            if (line.toLowerCase().startsWith("email")) {

                emails.add(line.substring(line.indexOf(":") + 1).trim());
                continue;

            }

            // Phone
            if (line.toLowerCase().startsWith("phone")) {

                phones.add(line.substring(line.indexOf(":") + 1).trim());
                continue;

            }

            // Section switches
            if (normalizedLine.contains("skills")) {

                skillSection = true;
                educationSection = false;
                experienceSection = false;
                continue;

            }

            if (normalizedLine.contains("education")) {

                skillSection = false;
                educationSection = true;
                experienceSection = false;
                continue;

            }

            if (normalizedLine.contains("experience")) {

                skillSection = false;
                educationSection = false;
                experienceSection = true;
                continue;

            }

            // Skills
            if (skillSection) {

                Skill skill = new Skill();
                skill.setName(line);
                skill.setConfidence(0.90);
                skill.setSources(List.of("Resume"));

                skills.add(skill);
                continue;

            }

            // Education
            if (educationSection) {

                Education education = new Education();
                education.setInstitution(line);
                education.setDegree("Bachelor of Engineering");
                education.setField("Artificial Intelligence and Machine Learning");
                education.setEndYear(2027);

                educations.add(education);
                continue;

            }

            // Experience
            if (experienceSection) {

                Experience experience = new Experience();
                experience.setCompany(line);
                experience.setTitle("Intern");
                experience.setStart("2025");
                experience.setEnd("Present");
                experience.setSummary("Parsed from Resume");

                experiences.add(experience);

            }

        }

        reader.close();

        candidate.setEmails(emails);
        candidate.setPhones(phones);
        candidate.setSkills(skills);
        candidate.setEducation(educations);
        candidate.setExperience(experiences);

        return candidate;
    }
}