package uk.awgt.pmsystem.Util.Serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import uk.awgt.pmsystem.domain.Employee;
import uk.awgt.pmsystem.repo.EmployeeRepo;

import java.io.IOException;
import java.util.Optional;

public class CustomEmployeeDeserializer extends StdDeserializer<Employee> {
    @Autowired
    EmployeeRepo employeeRepo;


    public CustomEmployeeDeserializer() {
        this(null);
    }

    public CustomEmployeeDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Custom deserialize Employee - only by id value
     */
    @Override
    public Employee deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        Long id = Long.parseLong(String.valueOf(jp.getCodec().readTree(jp)));
        Optional<Employee> employee = employeeRepo.findById(Long.parseLong(String.valueOf(id)));
        return employee.get();
    }
}
