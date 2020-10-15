package uk.awgt.pmsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.awgt.pmsystem.domain.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
