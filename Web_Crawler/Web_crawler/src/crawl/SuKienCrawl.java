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

public class SuKienCrawl extends WebCrawl {

    public void crawl() throws InterruptedException, IOException {
        // Link file chứa chromeDriver vừa mới giải nén
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\LENOVO\\Desktop\\Web_Crawler\\Web_crawler\\src\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(
                "https://citytourdanang.com/bang-tom-tat-cac-moc-su-kien-lich-su-viet-nam.html?fbclid=IwAR1B5LAA4-DMf9iPsD_F_62ZihGl_G3y6JMZn4lq2x3_3KOe101EsURvx7M");

        List<WebElement> rows = driver.findElements(
                By.xpath("//*[@id=\"main-page\"]/div[2]/table/tbody/tr"));

        // Các thuộc tính của thực thể cần crawl
        String[] attr = { "Thời gian", "Nội dung" };
        String name = "";

        JSONArray empsList = new JSONArray();

        // Tên file JSON lưu dữ liệu
        FileWriter file = new FileWriter("emps1.json");

        // Có 4 thẻ p đánh dấu từ 3 -> 6
        for (int i = 3; i <= 6; i++) {

            WebElement p = driver.findElement(
                    By.xpath("//*[@id=\"post-2320\"]/p[" + i + "]"));

            String[] text = p.getText().split("• ");
            int n = text.length;

            for (int j = 1; j < n; j++) {
                JSONObject emps = new JSONObject();
                JSONObject emps1 = new JSONObject();
                char x[] = text[j].toCharArray();
                int lengthx = x.length;

                String nam, nd;
                int cs = 3;
                while (true) {

                    // if(0 <= text[i] <= 9 && (text[i+1] == "," || text[i+1] == "."))
                    if ((48 <= x[cs] && x[cs] <= 57) && (x[cs + 1] == 44 || x[cs + 1] == 46)) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 3, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;
                        break;
                    }

                    // if(text[i] == "N" && text[i+1] == "," && text[i+2] == "space")
                    if (x[cs] == 78 && x[cs + 1] == 44 && x[cs + 2] == 32) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 3, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    // if (0 <= text[i] <= 9 && text[i+2] == "kytukhac" && text[i+2] != "-")
                    if ((x[cs] >= 48 && x[cs] <= 57) && (x[cs + 2] < 48 && x[cs + 2] > 57 && x[cs + 2] != 45)) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 3, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    // if (text[i] == "a" && text[i + 1] == ",")
                    if (x[cs] == 97 && x[cs + 1] == 44) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 3, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if (x[cs] == 53 && x[cs + 1] == 32 && x[cs + 2] == 98) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if (x[cs] == 50 && x[cs + 1] == 32 && x[cs + 2] == 99) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if (x[cs] == 50 && x[cs + 1] == 32 && (x[cs + 2] == 108 || x[cs + 2] == 118)) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if ((x[cs] == 52 || x[cs] == 56 || x[cs] == 48 || x[cs] == 49
                            || x[cs] == 50) && x[cs + 1] == 32 && x[cs + 2] == 78) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if (x[cs] == 56 && x[cs + 1] == 32 && (x[cs + 2] == 80 || x[cs + 2] == 116 || x[cs + 2] == 107)) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if ((x[cs] == 51 || x[cs] == 53) && x[cs + 1] == 32 && x[cs + 2] == 104) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if ((x[cs] == 55 || x[cs] == 53) && x[cs + 1] == 32 && x[cs + 2] == 116) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if (x[cs] == 53 && x[cs + 1] == 32 && x[cs + 2] == 78) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if (x[cs] == 54 && x[cs + 1] == 32 && (x[cs + 2] == 99 || x[cs + 4] == 105 || x[cs + 2] == 116)) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if (x[cs] == 53 && x[cs + 1] == 32 && x[cs + 2] == 78) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if ((x[cs] == 52 || x[cs] == 50) && x[cs + 1] == 32 && x[cs + 2] == 99) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    if (x[cs] == 48 && x[cs + 1] == 32 && x[cs + 4] == 110) {
                        nam = String.copyValueOf(x, 0, cs + 1);
                        nd = String.copyValueOf(x, cs + 2, lengthx - cs - 3);
                        System.out.println("Nam: " + nam);
                        System.out.println("Noi dung: " + nd);
                        name = nam;

                        break;
                    }

                    cs++;
                }
                emps.put(attr[1], nd);
                emps.put(attr[0], nam);

                emps1.put(name, emps);
                empsList.add(emps1);
            }

        }
        try

        {
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
