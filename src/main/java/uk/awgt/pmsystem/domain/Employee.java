package uk.awgt.pmsystem.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uk.awgt.pmsystem.Util.Serialize.CustomDepartmentDeserializer;
import uk.awgt.pmsystem.Util.Serialize.CustomDepartmentSerializer;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @NotNull
    private Long phoneNumber;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String position;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate employmentDate;
    @JsonSerialize(using = CustomDepartmentSerializer.class)
    @JsonDeserialize(using = CustomDepartmentDeserializer.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

}
