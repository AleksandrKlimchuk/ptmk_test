package ptmk.task.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ptmk.task.dto.HumanDTO;
import ptmk.task.model.Gender;
import ptmk.task.model.Human;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

    public List<HumanDTO> selectHumanWithUniqueFullnameAndBirthdayOrderByFullname() {
        final String sql = "select distinct on (fullname, birthday) " +
                "fullname, birthday, gender, date_part('year', age(CURRENT_DATE, birthday)) as age " +
                "from human order by fullname;";

        RowMapper<HumanDTO> humanDTORowMapper = (
                (rs, rowNum) -> HumanDTO.builder()
                        .fullname(rs.getString("fullname"))
                        .birthday(rs.getDate("birthday"))
                        .gender(Gender.of(rs.getInt("gender")))
                        .ageInYears(rs.getInt("age"))
                        .build()
        );

        return jdbc.query(sql, humanDTORowMapper);
    }

    public void insertMultipleHumans(List<Human> humans) {

        final String sql = "INSERT INTO human (fullname, birthday, gender) VALUES(?, ?, ?)";
        jdbc.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                final Human human = humans.get(i);
                ps.setString(1, human.getFullName());
                ps.setDate(2, new Date(human.getBirthday().getTime()));
                ps.setInt(3, human.getGender().getState());
            }

            @Override
            public int getBatchSize() {
                return humans.size();
            }
        });
    }

    public List<HumanDTO> findFMales() {
        final String sql = "select * from human where gender=1 AND starts_with(fullname, 'F');";
        RowMapper<HumanDTO> humanDTORowMapper = (
                (rs, rowNum) -> HumanDTO.builder()
                        .fullname(rs.getString("fullname"))
                        .birthday(rs.getDate("birthday"))
                        .gender(Gender.of(rs.getInt("gender")))
                        .build()
        );
        return jdbc.query(sql, humanDTORowMapper);
    }
}
