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

public class TrangNguyenCrawl extends WebCrawl {
    public void crawl() throws InterruptedException, IOException {

        // Link file chứa chromeDriver vừa mới giải nén
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\LENOVO\\Desktop\\Web_Crawler\\Web_crawler\\src\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Link Web cần crawl dữ liệu
        driver.get("https://nguoikesu.com/tu-lieu/danh-sach-trang-nguyen-viet-nam");

        // Các thuộc tính của thực thể cần crawl
        String[] attr = { "Thứ tự", "Tên", "Năm sinh năm mất", "Quê", "Năm đỗ trạng nguyên", "Đời vua", "Ghi chú" };
        String name = "";

        JSONArray empsList = new JSONArray();

        // Tên file JSON lưu dữ liệu
        FileWriter file = new FileWriter("emps4.json");

        // Đếm số hàng trong table bằng Xpath
        List<WebElement> rows = driver.findElements(
                By.xpath("//*[@id=\"content\"]/div[2]/div[2]/center/table/tbody/tr"));

        // Truy cập từng hàng, row.size() là số lượng tất cả các hàng trong bảng
        for (int i = 2; i <= rows.size(); i++) {

            List<WebElement> columns = driver.findElements(
                    By.xpath("//*[@id=\"content\"]/div[2]/div[2]/center/table/tbody/tr[" + i + "]/td"));
            // Tạo ra các Object JSON
            JSONObject emps = new JSONObject();
            JSONObject emps1 = new JSONObject();

            // Hàng i cột j, j là cột
            for (int j = 1; j <= columns.size(); j++) {

                // Truy cập vào hàng i cột j để lấy thông tin trong ô đó
                WebElement element = driver.findElement(
                        By.xpath("//*[@id=\"content\"]/div[2]/div[2]/center/table/tbody/tr[" + i + "]/td[" + j + "]"));

                // In ra thông tin từng thuộc tính trong thực thể. getText là thông tin của thẻ
                // element vừa lấy ở trên
                System.out.println(attr[j - 1] + ": " + element.getText());

                // Nạp thông tin hàng i cột j vào thuộc tính attr[j - 1]
                emps.put(attr[j - 1], element.getText());

                // Câu lệnh if này mục đích là lấy tên vị vua làm khóa của cục thông tin đó
                if (j == 2) {
                    name = element.getText();
                }
            }

            // Gán các tên vua làm khóa cho thực thể
            emps1.put(name, emps);

            // Đưa thông tin các vua vào List
            empsList.add(emps1);
            System.out.println("--------------------");
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
