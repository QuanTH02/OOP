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

public class LeHoi {
    private String nhanVatLienqQuan;
    private String ghiChu;
    private String tenLeHoi;
    private String lanDauToChuc;
    private String viTri;
    private String ngayBatDau;
    Set<String> lstLeHoi = new HashSet<>();

    public Set<String> getLstLeHoi() {
        return lstLeHoi;
    }

    public void setLstLeHoi() {
        JSONParser parser = new JSONParser();
        Set lst;
        String fileName = "emps2.json";
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray json = (JSONArray) obj;

            for (JSONObject jsonObj1 : (Iterable<JSONObject>) json) {
                lst = jsonObj1.keySet();
                Iterator<String> key1 = lst.iterator();
                String key2 = key1.next();
                lstLeHoi.add(key2);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        LeHoi leHoi = new LeHoi();
        leHoi.setLstLeHoi();
        for (String name : leHoi.getLstLeHoi()) {
            System.out.println(name);
        }
    }
}
