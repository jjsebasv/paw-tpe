package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.models.Course;
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

    public static final String COURSE_TABLE_NAME = "courses";

    public static final String COURSE_COLUMN_ID = "courseid";
    public static final String COURSE_COLUMN_CODE = "code";
    public static final String COURSE_COLUMN_NAME = "name";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public CourseJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(COURSE_TABLE_NAME)
                .usingGeneratedKeyColumns(COURSE_COLUMN_ID);
    }


    private final static RowMapper<Course> ROW_MAPPER = new RowMapper<Course>() {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Course(
                    rs.getInt(COURSE_COLUMN_ID),
                    rs.getString(COURSE_COLUMN_CODE),
                    rs.getString(COURSE_COLUMN_NAME)
            );
        }

    };

    @Override
    public List<Course> getAll() {

        final String query = "SELECT * FROM " + COURSE_TABLE_NAME;

        return jdbcTemplate.query(query, ROW_MAPPER);
    }

    @Override
    public List<Course> findByName(final String name) {
        final String query = "SELECT * FROM " + COURSE_TABLE_NAME + " WHERE " + COURSE_COLUMN_NAME + " LIKE \"%?%\"";

        return jdbcTemplate.query(query, ROW_MAPPER, name);
    }

    @Override
    public Course findById(final int courseid) {

        final String query = "SELECT * FROM " + COURSE_TABLE_NAME + " WHERE " + COURSE_COLUMN_ID + " = ?";

        List<Course> list = jdbcTemplate.query(query, ROW_MAPPER, courseid);

        if (list.isEmpty())
            return null;

        return list.get(0);
    }

    @Override
    public Course findByCode(final String code) {
        final String query = "SELECT * FROM " + COURSE_TABLE_NAME + " WHERE " + COURSE_COLUMN_CODE + " = ?";

        List<Course> list = jdbcTemplate.query(query, ROW_MAPPER, code);

        if (list.isEmpty())
            return null;

        return list.get(0);
    }

    @Override
    public List<Course> findByProgram(int programid) {
    	
        final String query = "SELECT * FROM " + COURSE_TABLE_NAME +
                " INNER JOIN coursesToPrograms ON courses.courseid = coursesToPrograms.courseid" +
                " INNER JOIN programs ON coursesToPrograms.programid = programs.programid" +
                " WHERE programs.programid = ?";

        return jdbcTemplate.query(query, ROW_MAPPER, programid);
    }

}
