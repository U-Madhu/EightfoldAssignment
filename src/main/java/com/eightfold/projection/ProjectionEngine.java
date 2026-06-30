package com.eightfold.projection;

import com.eightfold.model.Candidate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProjectionEngine {

    private static final Map<String, String> FIELD_MAPPING = new LinkedHashMap<>();

    static {

        FIELD_MAPPING.put("candidateId", "candidate_id");
        FIELD_MAPPING.put("fullName", "full_name");
        FIELD_MAPPING.put("emails", "emails");
        FIELD_MAPPING.put("phones", "phones");
        FIELD_MAPPING.put("location", "location");
        FIELD_MAPPING.put("links", "links");
        FIELD_MAPPING.put("headline", "headline");
        FIELD_MAPPING.put("yearsExperience", "years_experience");
        FIELD_MAPPING.put("skills", "skills");
        FIELD_MAPPING.put("experience", "experience");
        FIELD_MAPPING.put("education", "education");
        FIELD_MAPPING.put("provenance", "provenance");
        FIELD_MAPPING.put("overallConfidence", "overall_confidence");

    }

    public Map<String, Object> project(Candidate candidate,
                                       List<String> fields) {

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> canonical =
                mapper.convertValue(candidate, Map.class);

        Map<String, Object> projected =
                new LinkedHashMap<>();

        for (String field : fields) {

            if (!canonical.containsKey(field)) {
                continue;
            }

            String outputName =
                    FIELD_MAPPING.getOrDefault(field, field);

            projected.put(outputName,
                    canonical.get(field));

        }

        return projected;

    }

}