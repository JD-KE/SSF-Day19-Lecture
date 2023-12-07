package sg.edu.nus.iss.d19lecture.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.d19lecture.model.Employee;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/home")
    public String home(HttpSession session) {

        Employee employee = new Employee("Michael", (long) 12345);
        Employee employee2 = new Employee("Michelle", (long) 12346);
        Employee employee3 = new Employee("Myra", (long) 12347);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee2);
        employees.add(employee3);
        session.setAttribute("employees", employees);

        return "home";
    }

    @GetMapping("/nextpage")
    public String target(HttpSession session, Model model) {
        List<Employee> employees = (List<Employee>) session.getAttribute("employees");

        model.addAttribute("employees", employees);

        return "target";
    }
    
}
