package ptmk.task.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ptmk.task.model.Human;

@Repository
@RequiredArgsConstructor
public class HumanRepository {

    private final JdbcTemplate jdbc;

    public void createTable() {
        final String sql = "CREATE TABLE IF NOT EXISTS human (" +
                "id BIGSERIAL NOT NULL PRIMARY KEY," +
                "fullname VARCHAR(100) NOT NULL," +
                "birthday DATE NOT NULL," +
                "gender INTEGER NOT NULL);";

        jdbc.execute(sql);
    }

    public void insertHuman(Human human) {
        final String sql = "INSERT INTO human (fullname, birthday, gender) VALUES (?, ?, ?);";
        jdbc.update(sql, human.getFullName(), human.getBirthday(), human.getGender().getState());
    }
}
