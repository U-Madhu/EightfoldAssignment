package com.eightfold.util;

public class SkillUtil {

    public static String normalize(String skill) {

        if (skill == null) {
            return null;
        }

        skill = skill.trim().toLowerCase();

        return Character.toUpperCase(skill.charAt(0))
                + skill.substring(1);

    }

}