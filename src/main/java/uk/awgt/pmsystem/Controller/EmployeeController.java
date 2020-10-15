package uk.awgt.pmsystem.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.awgt.pmsystem.domain.Employee;
import uk.awgt.pmsystem.repo.DepartmentRepo;
import uk.awgt.pmsystem.repo.EmployeeRepo;
import uk.awgt.pmsystem.service.EmployeeService;

import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    DepartmentRepo departmentRepo;
    EmployeeRepo employeeRepo;
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeRepo employeeRepo, EmployeeService employeeService, DepartmentRepo departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.employeeService = employeeService;
        this.departmentRepo = departmentRepo;
    }

    /**
     * Saves new Employee into database
     * Requires valuable Employee as param
     */
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeRepo.save(employee);
    }

    /**
     * Gives one Employee by Employee id.
     */
    @GetMapping("{id}")
    public Employee readOne(@PathVariable("id") Employee employee) {
        return employee;
    }

    /**
     * Update fields of Employee, excludes id.
     * Requires Employee which will be changed and Employee with new information. To clear field
     * assign it null.
     */
    @PutMapping("{id}")
    public Employee update(@PathVariable("id") Employee employeeFromDb, @RequestBody Employee employee) {
        BeanUtils.copyProperties(employee, employeeFromDb, "id");
        return employeeRepo.save(employeeFromDb);
    }

    @PutMapping("{id}/addTo={addTo}")
    public ResponseEntity assignToDepartment(@PathVariable("id") Employee employee, @PathVariable("addTo") String departmentId) {
        if (isParsable(departmentId)) {
            employee.setDepartment(departmentRepo.findById(Long.parseLong(departmentId)).get());
            employeeRepo.save(employee);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Delete Employee from database
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Employee employee) {
        employeeRepo.delete(employee);
    }

    /**
     * Gives List of all Employees,
     * or employees from department by department id, key "<link>/api/employee?departmentID=${id}"
     * or employees without department,  key "<link>/api/employee?departmentID=null"
     */
    @GetMapping()
    public List<Employee> listAllEmployeesOrByDepartmentOrWithoutDepartment(@RequestParam(name = "departmentID", required = false) String department) {

        return employeeService.getByDepartment(department);

    }


}
