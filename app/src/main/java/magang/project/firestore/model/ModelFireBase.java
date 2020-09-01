package magang.project.firestore.model;

import java.util.HashMap;
import java.util.Map;

public class ModelFireBase {

    String id;
    String data_satu;
    String data_dua;

    public ModelFireBase(){

    }

    public ModelFireBase(String data_satu, String data_dua, String id) {
        this.data_satu = data_satu;
        this.data_dua = data_dua;
        this.id=id;
    }

    public ModelFireBase(String data_satu, String data_dua) {
        this.data_satu = data_satu;
        this.data_dua = data_dua;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData_satu() {
        return data_satu;
    }

    public void setData_satu(String data_satu) {
        this.data_satu = data_satu;
    }

    public String getData_dua() {
        return data_dua;
    }

    public void setData_dua(String data_dua) {
        this.data_dua = data_dua;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data_satu", this.data_satu);
        result.put("data_dua", this.data_dua);

        return result;
    }
}
