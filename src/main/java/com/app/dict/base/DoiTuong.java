package com.app.dict.base;

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
        return getSearching().compareTo(o.getSearching());
    }
}
