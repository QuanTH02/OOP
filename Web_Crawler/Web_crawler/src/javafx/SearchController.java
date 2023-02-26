package javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

	public class SearchController implements Initializable {
		@FXML
		private Button btnSearch;

		@FXML
		private TextArea taKetqua;

		@FXML
		private TextArea taList;

		@FXML
		private TextField txSearch;

		String searchObj;

		@FXML
		private ChoiceBox<String> choiceObject;

		private String[] obj = {"Vua", "Sự kiện", "Lễ Hội", "Triều đại", "Trạng nguyên", "Di tích"};

		@FXML
		void btnSearchPressed(ActionEvent event) {
			taKetqua.clear();
			StringBuilder s = new StringBuilder();

			String find = txSearch.getText().toLowerCase();
			JSONParser parser = new JSONParser();
			Set lst;
			String fileName = "";
			for (int i = 0; i < 6; i++) {
				if (searchObj.equals(obj[i])) {
					fileName = "emps" + i + ".json";
				}
			}
			int cnt = 0;
			try {
				Object obj = parser.parse(new FileReader(fileName));
				JSONArray json = (JSONArray) obj;

				for (JSONObject jsonObj1 : (Iterable<JSONObject>) json) {
					lst = jsonObj1.keySet();
					Iterator<String> key1 = lst.iterator();
					String key2 = key1.next();
					if (key2.toLowerCase().contains(find)) {
						cnt++;
						JSONObject jsonObj = (JSONObject) jsonObj1.get(key2);

						Iterator keysItr = jsonObj.keySet().iterator();
						s.append("----------------------------------------------\n");
						s.append(key2).append("\n");
						s.append("----------------------------------------------\n");
						while (keysItr.hasNext()) {
							String key = (String) keysItr.next();
							String value = jsonObj.get(key).toString();
							s.append(key).append(": ").append(value).append("\n");
						}
					}
				}
				if (cnt == 0)
					s.append("Không tìm thấy thông tin!\n");
				taKetqua.appendText(s.toString());

			} catch (ParseException | FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void initialize(URL url, ResourceBundle resourceBundle) {
			choiceObject.getItems().addAll(obj);
			choiceObject.setOnAction(this::getObj);

		}

		private void getObj(ActionEvent actionEvent) {
			taList.clear();
			searchObj = choiceObject.getValue();
			StringBuilder danhSach = new StringBuilder();
			JSONParser parser = new JSONParser();
			Set lst;
			String fileName = "";
			for (int i = 0; i < 6; i++) {
				if (searchObj.equals(obj[i])) {
					fileName = "emps" + i + ".json";
				}
			}
			try {
				Object obj = parser.parse(new FileReader(fileName));
				JSONArray json = (JSONArray) obj;

				for (JSONObject jsonObj1 : (Iterable<JSONObject>) json) {
					lst = jsonObj1.keySet();
					Iterator<String> key1 = lst.iterator();
					String key2 = key1.next();
					danhSach.append(key2).append("\n");
				}
				taList.appendText(danhSach.toString());
			} catch (IOException | ParseException e) {
				throw new RuntimeException(e);
			}
		}
	}