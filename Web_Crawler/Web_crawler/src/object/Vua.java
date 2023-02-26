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

public class Vua {
    private String name;
    private String mieuHieu;
    private String thuyHieu;
    private String nienHieu;
    private String tenHuy;
    private String theThu;
    private String triVi;
    private String namKetThucTriVi;
    private String tonHieu;

    private Set<String> lstVua = new HashSet<String>();

    public Set<String> getLstVua() {
        return lstVua;
    }

    public void setLstVua() {
        JSONParser parser = new JSONParser();
        Set lst;
        String fileName = "emps0.json";
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray json = (JSONArray) obj;

            for (JSONObject jsonObj1 : (Iterable<JSONObject>) json) {
                lst = jsonObj1.keySet();
                Iterator<String> key1 = lst.iterator();
                String key2 = key1.next();
                lstVua.add(key2);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Vua vua = new Vua();
        vua.setLstVua();
        for (String name : vua.getLstVua()) {
            System.out.println(name);
        }
    }
}
