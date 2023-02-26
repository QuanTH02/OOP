package object;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SuKien {
    private String thoiGian;
    private String noiDung;
    Set<String> lstSuKien = new HashSet<>();

    public Set<String> getLstSuKien() {
        return lstSuKien;
    }

    public void setLstSuKien() {
        JSONParser parser = new JSONParser();
        Set lst;
        String fileName = "emps1.json";
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray json = (JSONArray) obj;

            for (JSONObject jsonObj1 : (Iterable<JSONObject>) json) {
                lst = jsonObj1.keySet();
                Iterator<String> key1 = lst.iterator();
                String key2 = key1.next();
                lstSuKien.add(key2);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SuKien suKien = new SuKien();
        suKien.setLstSuKien();
        for (String name : suKien.getLstSuKien()) {
            System.out.println(name);
        }
    }
}
