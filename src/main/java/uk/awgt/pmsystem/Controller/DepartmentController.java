package uk.awgt.pmsystem.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.awgt.pmsystem.domain.Department;
import uk.awgt.pmsystem.repo.DepartmentRepo;
import uk.awgt.pmsystem.repo.EmployeeRepo;
import uk.awgt.pmsystem.service.DepartmentService;

import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    DepartmentRepo departmentRepo;
    EmployeeRepo employeeRepo;
    DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentRepo departmentRepo, EmployeeRepo employeeRepo, DepartmentService departmentService) {
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
        this.departmentService = departmentService;
    }

    /**
     * Saves new Department into database
     * Requires valuable Department as param
     */
    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentRepo.save(department);
    }

    /**
     * Gives one Department by Department id.
     */
    @GetMapping("{id}")
    public Department readOne(@PathVariable("id") Department department) {
        return department;
    }

    /**
     * Update fields of Department, excludes id.
     * Requires Department which will be changed and Department with new information. To clear field
     * assign it null.
     */
    @PutMapping("{id}")
    public Department update(@PathVariable("id") Department departmentFromDb, @RequestBody Department department) {
        BeanUtils.copyProperties(department, departmentFromDb, "id");
        return departmentRepo.save(departmentFromDb);
    }

    /**
     * Delete Department from database
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Department department) {
        departmentRepo.delete(department);
    }

    /**
     * Remove Employee from Department. Department and userId as params.
     */
    @DeleteMapping("{id}/remove={remove}")
    public ResponseEntity deleteEmployee(@PathVariable("id") Department department, @PathVariable("remove") String empoyeeId) {
        if (isParsable(empoyeeId)) {
            departmentService.removeEmployee(department, empoyeeId);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }


    /**
     * Get List of all Departments.
     */
    @GetMapping
    public List<Department> listAll() {
        return departmentRepo.findAll();
    }

}

