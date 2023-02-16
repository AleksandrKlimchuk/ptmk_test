package ptmk.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptmk.task.model.Gender;
import ptmk.task.model.Human;
import ptmk.task.repository.HumanRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class InsertHumanService implements AbstractTask{

    private final HumanRepository repository;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final int firstnamePos = 1;
    private static final int lastnamePos = 2;
    private static final int middlenamePos = 3;
    private static final int birthdayPos = 4;
    private static final int genderPos = 5;

    private static final String fullnameDelimiter = " ";

    @Override
    public void run(String... args) {

       validateArgs(args);
       final String fullname = extractFullname(args);
       final Date birthday = validateAndExtractBirthday(args[birthdayPos]);
       final Gender gender = validateAndExtractGender(args[genderPos]);
       final Human human = new Human(fullname, birthday, gender);
       repository.insertHuman(human);
    }
    private void validateArgs(String... args) {

        if (args.length != 6) {
            throw new IllegalArgumentException("Specify parameters: 2 firstname lastname middlename birthday gender");
        }
    }

    private String extractFullname(String... args) {

        return args[firstnamePos] + fullnameDelimiter +
                args[lastnamePos] + fullnameDelimiter +
                args[middlenamePos];

    }

    private Date validateAndExtractBirthday(String birthday) {

       try {
           return dateFormat.parse(birthday);
       } catch (ParseException e) {
           throw new IllegalArgumentException("Birthday must be presented in format: yyyy-MM-dd", e);
       }
    }

    private Gender validateAndExtractGender(String gender) {

        gender = gender.toLowerCase();
        if (!(gender.equals("male") || gender.equals("female"))) {
            throw new IllegalArgumentException("specify gender parameter as \"male\" or \"female\"");
        }
        return Gender.fromString(gender);
    }
}
