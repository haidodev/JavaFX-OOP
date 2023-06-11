package com.app.dict.controllers;

import com.app.dict.base.DoiTuong;
import com.app.dict.base.LoadData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GeneralController extends MainController implements Initializable {
    private static final String nhanVat_Path = "src/main/resources/com/app/dict/data/NhanVat.txt";
    private static final String thoiKy_Path = "src/main/resources/com/app/dict/data/ThoiKy.txt";
    private static final String suKien_Path = "src/main/resources/com/app/dict/data/NhanVat.txt";
    private static final String diTich_Path = "src/main/resources/com/app/dict/data/NhanVat.txt";
    private static final String leHoi_Path = "src/main/resources/com/app/dict/data/NhanVat.txt";
    protected final ObservableList<String> nhanVatList = FXCollections.observableArrayList();
    protected final ObservableList<String> thoiKyList = FXCollections.observableArrayList();
    protected final ObservableList<String> suKienList = FXCollections.observableArrayList();
    protected final ObservableList<String> diTichList = FXCollections.observableArrayList();
    protected final ObservableList<String> leHoiList = FXCollections.observableArrayList();
    protected static LoadData duLieu = new LoadData(nhanVat_Path, thoiKy_Path, suKien_Path, diTich_Path, leHoi_Path);

    @FXML
    protected ListView<String> listView;
    @FXML
    protected TextField searchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void updateWordInListView(String word, int index, ArrayList<DoiTuong> res, ArrayList<DoiTuong> des) {
        if (index < 0) {
            return;
        }
        int j = index;
        while (j >= 0) {
            if (LoadData.isContain(word, res.get(j).getSearching()) == 0) {
                j--;
            } else {
                break;
            }
        }
        for (int i = j + 1; i <= index; i++) {
            DoiTuong temp = new DoiTuong(res.get(i).getSearching(), res.get(i).getInfo());
            des.add(temp);
        }
        for (int i = index + 1; i < res.size(); i++) {
            if (LoadData.isContain(word, res.get(i).getSearching()) == 0) {
                DoiTuong temp = new DoiTuong(res.get(i).getSearching(), res.get(i).getInfo());
                des.add(temp);
            } else {
                break;
            }
        }
    }
}
