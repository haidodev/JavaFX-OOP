package com.app.dict.base;

import com.app.dict.util.VietnameseUtil;

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

        String searchingNormalized = VietnameseUtil.generalizeVietnameseString(getSearching());
        String oSearchingNormalized = VietnameseUtil.generalizeVietnameseString(o.getSearching());
        return searchingNormalized.compareTo(oSearchingNormalized);
    }
}
