package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseJdbcDao implements CourseDao {

    public static final String COURSE_TABLE_NAME = "courses";
    public static final String COURSE_COLUMN_ID = "course_id";
    public static final String COURSE_COLUMN_CODE = "code";
    public static final String COURSE_COLUMN_NAME = "name";
    public static final String PROGRAM_TABLE_NAME = "programs";
    public static final String PROGRAM_COLUMN_ID = "program_id";
    public static final String PROGRAM_COLUMN_NAME = "name";

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

        String queryName = name
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");

        final String query = "SELECT * FROM " + COURSE_TABLE_NAME + " WHERE " + COURSE_COLUMN_NAME + " ILIKE ?  ESCAPE '!'";

        //return jdbcTemplate.query(query, ROW_MAPPER, "'%" + name + "%'");
        try {
            PreparedStatement statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(query);
            statement.setString(1, "%" + queryName + "%");

            ResultSet resultSet = statement.executeQuery();

            List<Course> list = new ArrayList<>();

            int i = 0;

            while (resultSet.next()) {
                list.add(ROW_MAPPER.mapRow(resultSet, i++));
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
                " INNER JOIN coursesToPrograms ON " + COURSE_TABLE_NAME + "." + COURSE_COLUMN_ID + " = coursesToPrograms.course_id" +
                " INNER JOIN programs ON coursesToPrograms.program_id = programs.program_id" +
                " WHERE programs.program_id = ?";

        return jdbcTemplate.query(query, ROW_MAPPER, programid);
    }

}
