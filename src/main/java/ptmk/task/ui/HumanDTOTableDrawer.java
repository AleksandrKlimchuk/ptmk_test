package ptmk.task.ui;

import org.springframework.stereotype.Component;
import ptmk.task.dto.HumanDTO;

@Component
public class HumanDTOTableDrawer implements AbstractTableDrawer<HumanDTO> {

    private static final String fullnameHeader = "Full Name";
    private static final String birthdayHeader = "Birthday";
    private static final String genderHeader = "Gender";
    private static final String ageHeader = "Age in years";

    private static final String rowTemplate = "| %-30s| %-15s| %-15s| %-15s|\n";
    private static final String rowDelimiter = "-------------------------------------------------------" +
            "-----------------------------";

    @Override
    public void draw(Iterable<HumanDTO> humans) {
        System.out.println("\n" + rowDelimiter);
        System.out.printf(rowTemplate, fullnameHeader, birthdayHeader, genderHeader, ageHeader);
        System.out.println(rowDelimiter);
        humans.forEach(human -> {
            System.out.printf(
                    rowTemplate,
                    human.getFullname(), human.getBirthday(), human.getGender(), human.getAgeInYears()
            );
            System.out.println(rowDelimiter);
        });
    }
}
