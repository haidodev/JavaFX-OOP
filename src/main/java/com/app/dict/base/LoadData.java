package com.app.dict.base;

import com.app.dict.util.VietnameseUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class LoadData {
    private final String NV_PATH;
    private final String TK_PATH;
    private final String SK_PATH;
    private final String DT_PATH;
    private final String LH_PATH;
    private static final String SPLITTING_PATTERN = "<html>";
    private final ArrayList<DoiTuong> nhanVat = new ArrayList<>();
    private final ArrayList<DoiTuong> thoiKy = new ArrayList<>();
    private final ArrayList<DoiTuong> suKien = new ArrayList<>();
    private final ArrayList<DoiTuong> diTich = new ArrayList<>();
    private final ArrayList<DoiTuong> leHoi = new ArrayList<>();

    public LoadData(String nvPath, String tkPath, String skPath, String dtPath, String lhPath) {
        NV_PATH = nvPath;
        loadDataFromHTMLFile(NV_PATH, nhanVat);
        TK_PATH = tkPath;
        loadDataFromHTMLFile(TK_PATH, thoiKy);
        SK_PATH = skPath;
        loadDataFromHTMLFile(SK_PATH, suKien);
        DT_PATH = dtPath;
        loadDataFromHTMLFile(DT_PATH, diTich);
        LH_PATH = lhPath;
        loadDataFromHTMLFile(LH_PATH, leHoi);
    }

    public String getNV_PATH() {
        return NV_PATH;
    }

    public String getTK_PATH() {
        return TK_PATH;
    }

    public String getSK_PATH() {
        return SK_PATH;
    }

    public String getDT_PATH() {
        return DT_PATH;
    }

    public String getLH_PATH() {
        return LH_PATH;
    }

    public ArrayList<DoiTuong> getNhanVat() {
        return nhanVat;
    }

    public ArrayList<DoiTuong> getThoiKy() {
        return thoiKy;
    }

    public ArrayList<DoiTuong> getSuKien() {
        return suKien;
    }

    public ArrayList<DoiTuong> getDiTich() {
        return diTich;
    }

    public ArrayList<DoiTuong> getLeHoi() {
        return leHoi;
    }

    public void loadDataFromHTMLFile(String path, ArrayList<DoiTuong> temp) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SPLITTING_PATTERN);
                if (parts.length < 2) continue;
                String name = parts[0];
                String info = SPLITTING_PATTERN + parts[1];
                DoiTuong Obj = new DoiTuong(name, info) {
                };
                temp.add(Obj);
            }
            Collections.sort(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int binaryCheck(int start, int end, String dT, ArrayList<DoiTuong> doiTuong) {
        if (end < start) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        int compareNext = dT.compareTo(doiTuong.get(mid).getSearching());
        if (mid == 0) {
            if (compareNext < 0) {
                return 0;
            } else if (compareNext > 0) {
                return binaryCheck(mid + 1, end, dT, doiTuong);
            } else {
                return -1;
            }
        } else {
            int comparePrevious = dT.compareTo(doiTuong.get(mid - 1).getSearching());
            if (comparePrevious > 0 && compareNext < 0) {
                return mid;
            } else if (comparePrevious < 0) {
                return binaryCheck(start, mid - 1, dT, doiTuong);
            } else if (compareNext > 0) {
                if (mid == doiTuong.size() - 1) {
                    return doiTuong.size();
                }
                return binaryCheck(mid + 1, end, dT, doiTuong);
            } else {
                return -1;
            }
        }
    }
    public static int isContain(String str1, String str2) {
        String normalizedStr1 = VietnameseUtil.generalizeVietnameseString(str1);
        String normalizedStr2 = VietnameseUtil.generalizeVietnameseString(str2);
        for (int i = 0; i < Math.min(normalizedStr1.length(), normalizedStr2.length()); i++) {
            if (normalizedStr1.charAt(i) > normalizedStr2.charAt(i)) {
                return 1;
            } else if (normalizedStr1.charAt(i) < normalizedStr2.charAt(i)) {
                return -1;
            }
        }
        if (normalizedStr1.length() > normalizedStr2.length()) {
            return 1;
        }
        return 0;
    }
    public int binaryLookup(int start, int end, String dT, ArrayList<DoiTuong> temp) {
        if (end < start) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        int compare = isContain(dT, temp.get(mid).getSearching());
        if (compare == -1) {
            return binaryLookup(start, mid - 1, dT, temp);
        } else if (compare == 1) {
            return binaryLookup(mid + 1, end, dT, temp);
        } else {
            return mid;
        }
    }
}
