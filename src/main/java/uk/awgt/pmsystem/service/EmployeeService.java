package uk.awgt.pmsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.awgt.pmsystem.domain.Department;
import uk.awgt.pmsystem.domain.Employee;
import uk.awgt.pmsystem.repo.DepartmentRepo;
import uk.awgt.pmsystem.repo.EmployeeRepo;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

@Service
public class EmployeeService {

    EmployeeRepo employeeRepo;
    DepartmentRepo departmentRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

    /**
     * Gives list of all Employees by standard and in cause mistakes on departments id, gives list of Employee which assigned to department,
     * gives list of Employees which not assign to any departments.
     */
    public List<Employee> getByDepartment(String department) {
        if (isParsable(department)) {
            Optional<Department> fromBase = departmentRepo.findById(Long.parseLong(department));
            if (!fromBase.isEmpty()) {
                return employeeRepo.findAllByDepartment(fromBase);
            }
        }
        if (department != null && department.equals("null")) {
            return employeeRepo.findByDepartmentIsNull();
        }
        return employeeRepo.findAll();
    }
}
