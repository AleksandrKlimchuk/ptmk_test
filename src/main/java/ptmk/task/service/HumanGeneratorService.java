package ptmk.task.service;

import org.springframework.stereotype.Service;
import ptmk.task.model.Gender;
import ptmk.task.model.Human;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;


@Service
public class HumanGeneratorService implements Supplier<Human> {

    int a = 97;
    int z = 122;

    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    final static ThreadLocalRandom random = ThreadLocalRandom.current();

    public Human generateMales() {
        final String fullName = generateName("F") + " " + generateName() + " " + generateName();
        final Date date = generateDate();
        final Gender gender = Gender.MALE;
        return new Human(fullName, date, gender);
    }

    @Override
    public Human get() {
        final String fullName = generateFullname();
        final Date date = generateDate();
        final Gender gender = generateGender();
        return new Human(fullName, date, gender);
    }

    private Gender generateGender() {
        final int state = random.nextInt(1, 3);
        return Gender.of(state);
    }

    private Date generateDate() {
        final int year = random.nextInt(2000, 2023);
        final int month = random.nextInt(1, 13);
        final int day = random.nextInt(1, 31);
        final String date = year + "-" + month + "-" + day;
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateFullname() {

        final String firstname = generateName();
        final String lastname = generateName();
        final String middlename = generateName();
        return firstname + " " + lastname + " " + middlename;
    }

    private String generateName() {
        return generateName("");
    }

    private String generateName(String prefix) {
        prefix = prefix.toUpperCase();
        final String name = random.ints(a, z + 1)
                .limit(random.nextInt(4, 8))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        if (prefix.length() == 1) {
            return prefix + name;
        } else {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }
}
