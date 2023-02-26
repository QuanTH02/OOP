package crawl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VuaCrawl extends WebCrawl {
    public String changeStr(String str) {
        int i = 0;
        int n = str.length();
        int cnt = 0;
        StringBuilder sb = new StringBuilder(str);
        while (i < n) {
            if (sb.charAt(i) == '[') {
                int j = i;
                while (sb.charAt(i) != ']') {
                    i++;
                    cnt++;
                }
                sb.delete(j, i + 1);
                n = n - cnt - 1;
                i = i - cnt - 1;
                cnt = 0;
            }
            if (i == (n - 1))
                break;
            i++;
        }

        return sb.substring(0);
    }

    public void crawl() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\LENOVO\\Desktop\\Web_Crawler\\Web_crawler\\src\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam");
        System.out.println(driver.getTitle());

        String[] danhHieu = { "Tiết độ sứ", "Tôn hiệu", "Thụy Hiệu", "Niên Hiệu",
                "Tên Húy", "Thế Thứ",
                " Năm bắt đầu trị vì", "Đến", "Năm kết thúc" };
        JSONArray empList = new JSONArray();
        ArrayList<String> lst = new ArrayList<>();
        FileWriter file = new FileWriter("emps0.json");
        for (int i = 2; i < 41; i++) {

            String name = "";
            if ((i >= 12 && i <= 24 && (i % 2 == 0)) || (i >= 27 && (i % 2 == 1))) {
                continue;
            }
            List<WebElement> row = driver
                    .findElements(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[" + i + "]/tbody/tr"));
            if (i == 9 || i == 10 || i == 11) {
                for (int j = 1; j <= row.size(); j++) {
                    JSONObject emps = new JSONObject();
                    JSONObject empObj = new JSONObject();
                    List<WebElement> column;
                    if (i == 9) {
                        column = driver
                                .findElements(By.xpath(
                                        "//*[@id=\"mw-content-text\"]/div[1]/table[" + i + "]/tbody/tr[" + j + "]/td"));
                    } else {
                        column = driver
                                .findElements(By.xpath(
                                        "//*[@id=\"mw-content-text\"]/div[1]/table[" + i + "]/tbody/tr/td"));
                    }
                    for (int a = 2; a <= column.size(); a++) {
                        if (a == 9) {
                            continue;
                        }
                        WebElement xpath;
                        if (i == 9) {
                            xpath = driver
                                    .findElement(By
                                            .xpath("//*[@id=\"mw-content-text\"]/div[1]/table[" + i + "]/tbody/tr[" + j
                                                    + "]/td["
                                                    + a
                                                    + "]"));
                        } else {
                            xpath = driver
                                    .findElement(By
                                            .xpath("//*[@id=\"mw-content-text\"]/div[1]/table[" + i + "]/tbody/tr/td["
                                                    + a
                                                    + "]"));
                        }
                        String str = changeStr(xpath.getText()).replace('\n', ' ').replace("\u2013", " - ")
                                .replace("\u2014", " - ");

                        emps.put(danhHieu[a - 2], str);
                        System.out.print(str + " ");
                        if (a == 2) {
                            name = str;
                        }
                    }
                    empObj.put(name, emps);
                    empList.add(empObj);
                }
            }

            else {
                List<WebElement> row0 = driver.findElements(By.xpath(
                        "//*[@id=\"mw-content-text\"]/div[1]/table[" + i + "]/tbody/tr[1]/th"));
                for (int a = 2; a <= row0.size(); a++) {
                    WebElement xpath = driver
                            .findElement(By.xpath(
                                    "//*[@id=\"mw-content-text\"]/div[1]/table[" + i + "]/tbody/tr[1]/th[" + a + "]"));
                    String str = changeStr(xpath.getText()).replace('\n', ' ');
                    lst.add(str);
                }
                lst.add("Đến");
                lst.add("Năm kết thúc trị vì");
                for (int j = 2; j <= row.size(); j++) {
                    JSONObject emps = new JSONObject();
                    JSONObject empObj = new JSONObject();
                    List<WebElement> column = driver.findElements(By.xpath(
                            "//*[@id=\"mw-content-text\"]/div[1]/table[" + i + "]/tbody/tr[" + j +
                                    "]/td"));

                    for (int a = 2; a <= column.size(); a++) {
                        if (a == 9) {
                            continue;
                        }
                        WebElement xpath = driver
                                .findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[" + i + "]/tbody/tr["
                                        + j + "]/td[" + a + "]"));
                        String str = changeStr(xpath.getText()).replace('\n', ' ').replace("\u2013", " - ")
                                .replace("\u2014", " - ");

                        emps.put(lst.get(a - 2), str);
                        System.out.print(str + " ");
                        if (a == 2) {
                            name = str;
                        }
                    }
                    System.out.println("\n");
                    empObj.put(name, emps);
                    empList.add(empObj);
                }
                lst.clear();
            }
        }
        try {
            file.write(empList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        file.close();
        driver.quit();

    }
}
