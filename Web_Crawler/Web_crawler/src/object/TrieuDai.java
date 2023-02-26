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

public class TrieuDai {
    private String thoiDai;
    private String thoiKy;
    private String suKien;
    private String nam;

    private Set<String> lstTrieuDai = new HashSet<String>();

    public Set<String> getLstTrieuDai() {
        return lstTrieuDai;
    }

    public void setLstTrieuDai() {
        JSONParser parser = new JSONParser();
        Set lst;
        String fileName = "emps3.json";
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray json = (JSONArray) obj;

            for (JSONObject jsonObj1 : (Iterable<JSONObject>) json) {
                lst = jsonObj1.keySet();
                Iterator<String> key1 = lst.iterator();
                String key2 = key1.next();
                lstTrieuDai.add(key2);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        TrieuDai trieuDai = new TrieuDai();
        trieuDai.setLstTrieuDai();
        for (String name : trieuDai.getLstTrieuDai()) {
            System.out.println(name);
        }
    }
}
