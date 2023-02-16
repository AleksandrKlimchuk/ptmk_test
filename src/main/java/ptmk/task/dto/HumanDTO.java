package ptmk.task.dto;

import lombok.Builder;
import lombok.Data;
import ptmk.task.model.Gender;

import java.util.Date;

@Data
@Builder
public class HumanDTO {

    private String fullname;
    private Date birthday;
    private Gender gender;
    private Integer ageInYears;
}
