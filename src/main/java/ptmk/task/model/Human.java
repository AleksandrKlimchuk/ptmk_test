package ptmk.task.model;

import lombok.*;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Human {

    private final Long id;
    private final String fullName;
    private final Date birthday;
    private final Gender gender;

    public Human(String fullName, Date birthday, Gender gender) {
        id = null;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
    }
}
