package uk.awgt.pmsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.awgt.pmsystem.domain.Department;
import uk.awgt.pmsystem.domain.Employee;
import uk.awgt.pmsystem.repo.DepartmentRepo;
import uk.awgt.pmsystem.repo.EmployeeRepo;

@Component
public class DepartmentService {
    EmployeeRepo employeeRepo;
    DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentService(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

    /**
     * Removes employee from department, and removes department from employee
     */
    public void removeEmployee(Department department, String empoyeeId) {
        Employee employee = department.getEmployees().stream().filter(r -> r.getId().equals(Long.valueOf(empoyeeId))).findFirst().get();
        department.getEmployees().remove(employee);
        departmentRepo.save(department);
        Employee one = employeeRepo.getOne(Long.valueOf(empoyeeId));
        one.setDepartment(null);
        employeeRepo.save(one);
    }
}
