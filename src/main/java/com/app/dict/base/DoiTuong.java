package com.app.dict.base;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class DoiTuong implements Comparable<DoiTuong>{
    private String searching;
    private String info;
    public DoiTuong(){
        searching = "";
        info = "";
    }

    public DoiTuong(String searching, String info) {
        this.searching = searching;
        this.info = info;
    }

    public String getSearching() {
        return searching;
    }

    public void setSearching(String searching) {
        this.searching = searching;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public int compareTo(DoiTuong o) {

        String searchingNormalized = generalizeVietnameseString(getSearching());
        String oSearchingNormalized = generalizeVietnameseString(o.getSearching());
//        System.out.printf("\"%s\" -> \"%s\"%n", getSearching(), searchingNormalized);
//        System.out.printf("\"%s\" -> \"%s\"%n", o.getSearching(), oSearchingNormalized);
        return searchingNormalized.compareTo(oSearchingNormalized);
    }

    public static String generalizeVietnameseString(String vietnameseString) {
        // Remove accents
        String normalizedString = Normalizer.normalize(vietnameseString, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String withoutAccents = pattern.matcher(normalizedString).replaceAll("");

        // Convert to lowercase
        String lowercaseString = withoutAccents.toLowerCase();

        // Remove redundant spaces
        String trimmedString = lowercaseString.trim();

        return trimmedString.replaceAll("\\s+", " ");
    }
}
