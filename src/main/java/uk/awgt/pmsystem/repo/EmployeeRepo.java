package uk.awgt.pmsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.awgt.pmsystem.domain.Department;
import uk.awgt.pmsystem.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findAllByDepartment(Optional<Department> department);

    List<Employee> findByDepartmentIsNull();
}
