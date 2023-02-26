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

public class TrangNguyen {
    private String ten;
    private String doiVua;
    private String que;
    private String namDo;
    private String namSinhNamMat;
    Set<String> lstTrangNguyen = new HashSet<>();

    public Set<String> getLstTrangNguyen() {
        return lstTrangNguyen;
    }

    public void setLstTrangNguyen() {
        JSONParser parser = new JSONParser();
        Set lst;
        String fileName = "emps4.json";
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray json = (JSONArray) obj;

            for (JSONObject jsonObj1 : (Iterable<JSONObject>) json) {
                lst = jsonObj1.keySet();
                Iterator<String> key1 = lst.iterator();
                String key2 = key1.next();
                lstTrangNguyen.add(key2);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        TrangNguyen trangNguyen = new TrangNguyen();
        trangNguyen.setLstTrangNguyen();
        for (String name : trangNguyen.getLstTrangNguyen()) {
            System.out.println(name);
        }
    }
}
