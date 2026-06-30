package com.eightfold.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {

    public List<String> loadFields(String filePath) {

        List<String> fields = new ArrayList<>();

        try {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(new File(filePath));

            JsonNode array = root.get("fields");

            if (array != null) {

                for (JsonNode node : array) {

                    fields.add(node.asText());

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return fields;

    }
}