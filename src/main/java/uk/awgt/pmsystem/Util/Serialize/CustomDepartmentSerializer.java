package uk.awgt.pmsystem.Util.Serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import uk.awgt.pmsystem.domain.Department;

import java.io.IOException;

public class CustomDepartmentSerializer extends StdSerializer<Department> {
    public CustomDepartmentSerializer() {
        this(null);
    }

    protected CustomDepartmentSerializer(Class<Department> t) {
        super(t);
    }

    /**
     * Custom serialize Department - only id value
     */
    @Override
    public void serialize(Department value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeObject(value.getId());
    }
}
