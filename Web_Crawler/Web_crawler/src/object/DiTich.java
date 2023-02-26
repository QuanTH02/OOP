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

public class DiTich {
    private String diaDiem;
    private String gioiThieu;
    private String tenDiTich;
    private String soQuyetDinh;
    private String noiDung;
    Set<String> lstDiTich = new HashSet<>();

    public Set<String> getLstDiTich() {
        return lstDiTich;
    }

    public void setLstDiTich() {
        JSONParser parser = new JSONParser();
        Set lst;
        String fileName = "emps5.json";
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray json = (JSONArray) obj;

            for (JSONObject jsonObj1 : (Iterable<JSONObject>) json) {
                lst = jsonObj1.keySet();
                Iterator<String> key1 = lst.iterator();
                String key2 = key1.next();
                lstDiTich.add(key2);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DiTich diTich = new DiTich();
        diTich.setLstDiTich();
        for (String name : diTich.getLstDiTich()) {
            System.out.println(name);
        }
    }
}
