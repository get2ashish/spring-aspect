package github.com.get2ashish.springaspect.controller;

import github.com.get2ashish.springaspect.model.Employee;
import github.com.get2ashish.springaspect.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) throws Exception {
        //This condition is placed to throw exception so that we can test AfterThrowing advice
        if (Objects.isNull(employee.getFirstName())) {
            throw new Exception("Name format is invalid!");
        }
        return ResponseEntity.ok(employeeRepository.save(employee));
    }
}
