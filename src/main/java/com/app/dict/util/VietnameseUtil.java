package com.app.dict.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class VietnameseUtil {
    public static String generalizeVietnameseString(String vietnameseString) {
        // Remove accents
        String normalizedString = Normalizer.normalize(vietnameseString, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String withoutAccents = pattern.matcher(normalizedString).replaceAll("");
        String replacedText = withoutAccents.replace("đ", "d").replace("Đ", "D");

        // Convert to lowercase
        String lowercaseString = replacedText.toLowerCase();

        // Remove redundant spaces
        String trimmedString = lowercaseString.trim();

        return trimmedString.replaceAll("\\s+", " ");
    }
}