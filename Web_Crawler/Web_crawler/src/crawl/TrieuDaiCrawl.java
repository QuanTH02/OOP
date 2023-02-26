package crawl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TrieuDaiCrawl extends WebCrawl {
    public void crawl() throws InterruptedException, IOException {
        // Link file chứa chromeDriver vừa mới giải nén
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\LENOVO\\Desktop\\Web_Crawler\\Web_crawler\\src\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://vansu.vn/viet-nam/viet-nam-su-luoc");

        List<WebElement> div = driver.findElements(
                By.xpath("/html/body/div[3]/div"));

        // Các thuộc tính của thực thể cần crawl
        String[] attr = { "Thời đại", "Thời kỳ", "Năm", "Sự kiện" };
        String name = "";
        String thoidai = "";

        JSONArray empsList = new JSONArray();

        // Tên file JSON lưu dữ liệu
        FileWriter file = new FileWriter("emps3.json");

        for (int i = 4; i <= div.size(); i++) {
            JSONObject emps = new JSONObject();
            JSONObject emps1 = new JSONObject();

            // Dựa vào từng trường hợp để truy cập vào và crawl
            if (i == 4 || i == 9 || i == 16 || i == 45) {
                WebElement td = driver.findElement(
                        By.xpath("/html/body/div[3]/div[" + i + "]"));
                String[] arrx = td.getText().split(": ");
                thoidai = arrx[1];
                continue;
            }

            if (i == 32) {
                WebElement td = driver.findElement(
                        By.xpath("/html/body/div[3]/div[" + i + "]"));
                String[] arrx = td.getText().split(". ");
                thoidai = arrx[1];
                continue;
            }

            if (i == 62)
                break;
            if (i == 7 || i == 15)
                continue;
            if (i > 31 && i != 34 && i != 43)
                continue;

            emps.put(attr[0], thoidai);
            System.out.println("Thời đại: " + thoidai);

            WebElement title = driver.findElement(
                    By.xpath("/html/body/div[3]/div[" + i + "]"));

            String[] arr = title.getText().split(": ");
            String[] b = arr[1].split(" \\(");
            b[1] = "(" + b[1];
            System.out.println("Thời kỳ: " + b[0]);
            System.out.println("Năm: " + b[1]);
            emps.put(attr[1], b[0]);
            emps.put(attr[2], b[1]);
            name = b[0];

            WebElement tag = driver.findElement(
                    By.xpath("/html/body/div[3]/div[" + i + "]/a[1]"));
            String link = tag.getAttribute("href");
            driver.get(link);

            WebElement event = driver.findElement(
                    By.xpath("/html/body/div[3]"));

            emps.put(attr[3], event.getText());
            System.out.println("Sự kiện: " + event.getText());

            emps1.put(name, emps);

            // Đưa thông tin các vua vào List
            empsList.add(emps1);
            System.out.println("------------------------------------------");

            driver.get("https://vansu.vn/viet-nam/viet-nam-su-luoc");
        }
        try {
            // Copy thông tin vào file JSON
            file.write(empsList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.close();
        Thread.sleep(1000);
        driver.quit();

    }
}
