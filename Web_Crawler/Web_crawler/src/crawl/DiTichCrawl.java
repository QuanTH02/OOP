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

public class DiTichCrawl extends WebCrawl{
    public void crawl () throws InterruptedException, IOException {
        // Link file chứa chromeDriver vừa mới giải nén
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\LENOVO\\Desktop\\Web_Crawler\\Web_crawler\\src\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://dsvh.gov.vn/danh-muc-di-tich-quoc-gia-dac-biet-1752");

        List<WebElement> rows = driver.findElements(
                By.xpath("//*[@id=\"main-page\"]/div[2]/table/tbody/tr"));

        // Các thuộc tính của thực thể cần crawl
        String[] attr = { "Tên di tích", "Số quyết định", "Địa điểm", "Giới thiệu", "Nội dung" };
        String name = "";

        JSONArray empsList = new JSONArray();

        // Tên file JSON lưu dữ liệu
        FileWriter file = new FileWriter("emps5.json");

        // Truy cập từng hàng, row.size() là số lượng tất cả các hàng trong bảng
        for (int i = 2; i <= rows.size(); i++) {
            JSONObject emps = new JSONObject();
            JSONObject emps1 = new JSONObject();
            String pr1 = "", pr2 = "", pr3 = "";
            List<WebElement> columns = driver.findElements(
                    By.xpath("//*[@id=\"main-page\"]/div[2]/table/tbody/tr[" + i + "]/td"));

            if (i <= 88 && i != 4 && i != 10 && i != 15 && i != 78 && i != 53 && i != 83 && i != 31) {
                WebElement element1;

                // Tên
                if (i != 11) {
                    element1 = driver.findElement(
                            By.xpath("//*[@id=\"main-page\"]/div[2]/table/tbody/tr[" + i + "]/td[2]/p/a"));
                } else {
                    element1 = driver.findElement(
                            By.xpath("//*[@id=\"main-page\"]/div[2]/table/tbody/tr[" + i + "]/td[2]/p/span/span/a"));
                }

                String link = element1.getAttribute("href");
                driver.get(link);

                WebElement h1 = driver.findElement(
                        By.xpath("//*[@id=\"main-page\"]/div[2]/h1"));

                // Giới thiệu
                if (i == 17 || i == 21 || i == 28 || i == 29) {
                    WebElement des = driver.findElement(
                            By.xpath("//*[@id=\"main-page\"]/div[2]/div[1]/div"));
                    emps.put(attr[3], des.getText());
                    pr2 = des.getText();
                } else {
                    if (i == 36) {
                        WebElement des = driver.findElement(
                                By.xpath("//*[@id=\"main-page\"]/div[2]/div/p/strong"));
                        emps.put(attr[3], des.getText());
                        pr2 = des.getText();
                    } else {
                        if (i == 20) {
                            WebElement des = driver.findElement(
                                    By.xpath("//*[@id=\"main-page\"]/div[2]/div/p"));
                            emps.put(attr[3], des.getText());
                            pr2 = des.getText();
                        } else {
                            WebElement des = driver.findElement(
                                    By.xpath("//*[@id=\"main-page\"]/div[2]/div[1]/p"));
                            emps.put(attr[3], des.getText());
                            pr2 = des.getText();
                        }
                    }
                }

                // Nội dung
                if (i >= 36 || i == 20) {
                    WebElement nd = driver.findElement(
                            By.xpath("//*[@id=\"main-page\"]/div[2]"));
                    emps.put(attr[4], nd.getText());
                    pr3 = nd.getText();
                } else {
                    if (((i >= 12 && i <= 21) && i != 17) || (25 <= i && i <= 30) || i >= 32) {
                        WebElement nd = driver.findElement(
                                By.xpath("//*[@id=\"main-page\"]/div[2]/div[3]"));
                        emps.put(attr[4], nd.getText());
                        pr3 = nd.getText();
                    } else {
                        if (i == 17) {
                            WebElement nd = driver.findElement(
                                    By.xpath("//*[@id=\"main-page\"]/div[2]/div[4]"));
                            emps.put(attr[4], nd.getText());
                            pr3 = nd.getText();
                        } else {

                            WebElement nd = driver.findElement(
                                    By.xpath("//*[@id=\"main-page\"]/div[2]/div[2]"));
                            emps.put(attr[4], nd.getText());
                            pr3 = nd.getText();

                        }

                    }

                }

                emps.put(attr[0], h1.getText());

                pr1 = h1.getText();

            } else {
                WebElement ten = driver.findElement(
                        By.xpath("//*[@id=\"main-page\"]/div[2]/table/tbody/tr[" + i + "]/td[2]/p"));
                pr1 = ten.getText();
                emps.put(attr[0], ten.getText());
            }

            // Quay lại web chính để lấy dữ liệu
            driver.get("http://dsvh.gov.vn/danh-muc-di-tich-quoc-gia-dac-biet-1752");
            System.out.println(attr[0] + ": " + pr1);

            // Lấy Số quyết định và Địa điểm
            for (int j = 2; j <= columns.size(); j++) {
                WebElement element = driver.findElement(
                        By.xpath("//*[@id=\"main-page\"]/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]"));
                if (j == 2) {
                    if (i > 88 && i == 4 && i == 10 && i == 15 && i == 78 && i == 53 && i == 83 && i == 31) {
                        attr[0] = element.getText();
                    } else
                        name = element.getText();
                } else {
                    emps.put(attr[j - 2], element.getText());

                    System.out.println(attr[j - 2] + ": " + element.getText());

                }
            }

            System.out.println(attr[3] + ": " + pr2);
            System.out.println(attr[4] + ": " + pr3);

            emps1.put(name, emps);

            empsList.add(emps1);
            System.out.println(
                    "----------------------------------------------------------------------------------------");

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
