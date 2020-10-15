package uk.awgt.pmsystem.Util.Serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import uk.awgt.pmsystem.domain.Employee;

import java.io.IOException;

public class CustomEmployeeSerializer extends StdSerializer<Employee> {
    public CustomEmployeeSerializer() {
        this(null);
    }

    protected CustomEmployeeSerializer(Class<Employee> t) {
        super(t);
    }

    /**
     * Custom serialize Employee - only  id value
     */
    @Override
    public void serialize(Employee value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeObject(value.getId());
    }
}
