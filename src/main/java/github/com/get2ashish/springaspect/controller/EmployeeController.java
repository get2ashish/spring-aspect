package github.com.get2ashish.springaspect.controller;

import github.com.get2ashish.springaspect.model.Employee;
import github.com.get2ashish.springaspect.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @PostConstruct
    public void init() {

        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            employeeList.add(Employee.builder()
                    .email("email " + i)
                    .firstName("first name " + i)
                    .lastName("last name " + i)
                    .build());
        }
        employeeRepository.saveAll(employeeList);
    }


    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeRepository.save(employee));
    }
}
