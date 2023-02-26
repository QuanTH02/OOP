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

public class LeHoiCrawl extends WebCrawl {

    public void crawl() throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\LENOVO\\Desktop\\Web_Crawler\\Web_crawler\\src\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam");

        // Các thuộc tính
        String[] attr = { "Ngày bắt đầu (âm lịch)", "Vị trí", "Lễ hội truyền thống", "Lần đầu tổ chức năm",
                "Nhân vật liên quan", "Ghi chú" };
        List<WebElement> rows = driver.findElements(
                By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[2]/tbody/tr"));

        FileWriter file = new FileWriter("emps2.json");

        JSONArray empsList = new JSONArray();

        String name = "";

        for (int i = 2; i <= rows.size(); i++) {
            JSONObject emps = new JSONObject();
            JSONObject emps1 = new JSONObject();
            List<WebElement> columns = driver.findElements(
                    By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[2]/tbody/tr[" + i + "]/td"));
            for (int j = 1; j <= columns.size(); j++) {
                WebElement element = driver.findElement(
                        By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[2]/tbody/tr[" + i + "]/td[" + j + "]"));
                System.out.println(attr[j - 1] + ": " + element.getText());

                emps.put(attr[j - 1], element.getText());

                if (j == 3) {
                    name = element.getText();
                }
            }
            emps1.put(name, emps);

            empsList.add(emps1);
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
