package uk.awgt.pmsystem.Util.Serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import uk.awgt.pmsystem.domain.Department;
import uk.awgt.pmsystem.repo.DepartmentRepo;

import java.io.IOException;
import java.util.Optional;

public class CustomDepartmentDeserializer extends StdDeserializer<Department> {
    @Autowired
    DepartmentRepo departmentRepo;


    public CustomDepartmentDeserializer() {
        this(null);
    }

    public CustomDepartmentDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Custom deserialize Department - only from id value
     */
    @Override
    public Department deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        Long id = Long.parseLong(String.valueOf(jp.getCodec().readTree(jp)));
        Optional<Department> department = departmentRepo.findById(Long.parseLong(String.valueOf(id)));
        return department.get();
    }
}
