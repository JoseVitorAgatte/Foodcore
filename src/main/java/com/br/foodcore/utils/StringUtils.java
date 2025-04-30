package com.br.foodcore.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

    public static String removeAcentos(String str) {
        if (str == null) return null;
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("[^\\p{ASCII}]");
        return pattern.matcher(normalized).replaceAll("");
    }
}
