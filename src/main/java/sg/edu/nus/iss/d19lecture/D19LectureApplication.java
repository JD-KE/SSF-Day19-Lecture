package sg.edu.nus.iss.d19lecture;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.edu.nus.iss.d19lecture.model.Bag;
import sg.edu.nus.iss.d19lecture.model.Employee;
import sg.edu.nus.iss.d19lecture.repo.EmployeeRepo;

@SpringBootApplication
public class D19LectureApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired @Qualifier("totebag")
	private Bag bag;

	public static void main(String[] args) {
		SpringApplication.run(D19LectureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// for linux
		// ./employees.json
		// for windows
		File file = new File(".\\employees.json");
		InputStream is = new FileInputStream(file);

		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}
		String data = sb.toString();

		// System.out.println(data);

		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(data);

		JSONArray jsonArray = (JSONArray) object;

		// System.out.println("jsonArray size: " + jsonArray.size());
		// System.out.println("json Array list of objects " + jsonArray);

		List<Employee> employees = new ArrayList<>();
		
		jsonArray.forEach(emp -> employees.add(parseEmployeeObject((JSONObject) emp)));

		System.out.println("List of employees " + employees);

		for(Employee employee: employees) {
			employeeRepo.saveRecord(employee);
		}

		// currently not working? employee cannot cast to employee
		// remove serializers can work
		Employee employeeRetrieved = employeeRepo.getRecord(12345);

		System.out.println("Employee:" + employeeRetrieved.getName());

		// using Json p
		File file2 = new File("./employee2.json");

		JsonReader jsonReader = Json.createReader(new FileInputStream(file2));

		JsonArray data2 = jsonReader.readArray();

		List<Employee> employees2 = new ArrayList<>();

		for (JsonValue entry: data2) {
			JsonObject employee = entry.asJsonObject();
			employees2.add(new Employee(employee.getString("employeeName"), Long.valueOf(employee.getInt("employeeId"))));
		}

		System.out.println("Second List " + employees2);

		bag.showBagType();
		

	}

	private Employee parseEmployeeObject (JSONObject jsonObject) {
		JSONObject jsonEmployeeObject = (JSONObject) jsonObject.get("employee");
		
		Employee employee = new Employee();
		employee.setId((Long) jsonEmployeeObject.get("employeeId"));
		employee.setName((String) jsonEmployeeObject.get("employeeName"));
		
		return employee;
	}

}
