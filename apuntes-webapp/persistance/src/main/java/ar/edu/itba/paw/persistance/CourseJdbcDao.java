package ar.edu.itba.paw.persistance;

import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CourseJdbcDao implements CourseDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public CourseJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("courses")
                .usingGeneratedKeyColumns("courseid");
    }


    private final static RowMapper<Course> ROW_MAPPER = new RowMapper<Course>() {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Course(rs.getInt("courseid"), rs.getString("name"));
        }

    };

    @Override
    public List<Course> getAll() {
        return jdbcTemplate.query("SELECT * FROM courses", ROW_MAPPER);
    }

    @Override
    public List<Course> findByName(final String name) {
        return jdbcTemplate.query("SELECT * FROM courses WHERE name LIKE \"%?%\"", ROW_MAPPER, name);
    }

    @Override
    public Course findById(final int courseid) {
        List<Course> list = jdbcTemplate.query("SELECT * FROM courses WHERE courseid = ?", ROW_MAPPER, courseid);

        return list.get(0);
    }

}
