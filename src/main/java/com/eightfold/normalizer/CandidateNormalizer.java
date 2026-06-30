package com.eightfold.normalizer;

import com.eightfold.model.Candidate;
import com.eightfold.model.Skill;
import com.eightfold.util.EmailUtil;
import com.eightfold.util.PhoneUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CandidateNormalizer {

    public Candidate normalize(Candidate candidate) {

        // ---------------- Normalize Name ----------------

        if (candidate.getFullName() != null) {
            candidate.setFullName(candidate.getFullName().trim());
        }

        // ---------------- Normalize Emails ----------------

        List<String> normalizedEmails = new ArrayList<>();

        if (candidate.getEmails() != null) {

            Set<String> emailSet = new LinkedHashSet<>();

            for (String email : candidate.getEmails()) {

                String normalized = EmailUtil.normalize(email);

                if (normalized != null && !normalized.isEmpty()) {
                    emailSet.add(normalized);
                }
            }

            normalizedEmails.addAll(emailSet);
        }

        candidate.setEmails(normalizedEmails);

        // ---------------- Normalize Phones ----------------

        List<String> normalizedPhones = new ArrayList<>();

        if (candidate.getPhones() != null) {

            Set<String> phoneSet = new LinkedHashSet<>();

            for (String phone : candidate.getPhones()) {

                String normalized = PhoneUtil.normalize(phone);

                if (normalized != null && !normalized.isEmpty()) {
                    phoneSet.add(normalized);
                }
            }

            normalizedPhones.addAll(phoneSet);
        }

        candidate.setPhones(normalizedPhones);

        // ---------------- Normalize Skills ----------------

        if (candidate.getSkills() != null) {

            Map<String, Skill> skillMap = new LinkedHashMap<>();

            for (Skill skill : candidate.getSkills()) {

                if (skill.getName() == null) {
                    continue;
                }

                String key = skill.getName().trim().toLowerCase();

                skill.setName(skill.getName().trim());

                skillMap.putIfAbsent(key, skill);
            }

            candidate.setSkills(new ArrayList<>(skillMap.values()));
        }

        return candidate;
    }
}