package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Program;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseJdbcDao implements CourseDao {

    /*package*/ static final String COURSE_TABLE_NAME = "courses";
    /*package*/ static final String COURSE_COLUMN_ID = "course_id";
    /*package*/ static final String COURSE_COLUMN_CODE = "code";
    /*package*/ static final String COURSE_COLUMN_NAME = "name";
    /*package*/ static final String COURSE_COLUMN_SEMESTER = "semester";

    /*package*/ static final String COURSETOPROGRAM_TABE_NAME = "coursesToPrograms";
    /*package*/ static final String COURSETOPROGRAM_COLUMN_COURSE_ID = "course_id";
    /*package*/ static final String COURSETOPROGRAM_COLUMN_PROGRAM_ID = "program_id";
    /*package*/ static final String COURSETOPROGRAM_COLUMN_SEMESTER = "semester";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final SimpleJdbcInsert jdbcRelatedProgramInsert;

    @Autowired
    public CourseJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(COURSE_TABLE_NAME)
                .usingGeneratedKeyColumns(COURSE_COLUMN_ID);

        jdbcRelatedProgramInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(COURSETOPROGRAM_TABE_NAME);
    }


    private final static RowMapper<Course> ROW_MAPPER = new RowMapper<Course>() {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Course(
                    rs.getInt(COURSE_COLUMN_ID),
                    rs.getString(COURSE_COLUMN_CODE),
                    rs.getString(COURSE_COLUMN_NAME));
        }

    };

    private final static RowMapper<Course> ROW_MAPPER_WITH_SEMESTER = new RowMapper<Course>() {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Course(
                    rs.getInt(COURSE_COLUMN_ID),
                    rs.getString(COURSE_COLUMN_CODE),
                    rs.getString(COURSE_COLUMN_NAME),
                    rs.getInt(COURSE_COLUMN_SEMESTER));
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

        //FIXME Los LOWER() son un workaround para hsqldb que no soporta case insensitive like search
        final String query = "SELECT * FROM " + COURSE_TABLE_NAME + " WHERE LOWER(" + COURSE_COLUMN_NAME + ") LIKE LOWER(?)  ESCAPE '!'";

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
                " WHERE programs.program_id = ? ORDER BY coursesToPrograms.semester ASC";

        return jdbcTemplate.query(query, ROW_MAPPER_WITH_SEMESTER, programid);
    }

    @Override
    public Course create(String code, String name) {
        final Map<String, Object> args = new HashMap<>();
        args.put(COURSE_COLUMN_CODE, code);
        args.put(COURSE_COLUMN_NAME, name);

        final Number courseId = jdbcInsert.executeAndReturnKey(args);
        return new Course(courseId.intValue(), code, name);
    }

    @Override
    public void addProgramRelationship(final Course course, final Program program, final int semester) {
        final Map<String, Object> args = new HashMap<>();
        args.put(COURSETOPROGRAM_COLUMN_PROGRAM_ID, program.getProgramid());
        args.put(COURSETOPROGRAM_COLUMN_COURSE_ID, course.getCourseid());
        args.put(COURSETOPROGRAM_COLUMN_SEMESTER, semester);

        jdbcRelatedProgramInsert.execute(args);
    }

    @Override
    public boolean isRelatedTo(Course course, Program program) {
        final String query = "SELECT COUNT(*) FROM " + COURSETOPROGRAM_TABE_NAME + " WHERE " + COURSETOPROGRAM_COLUMN_COURSE_ID + " = ? AND " + COURSETOPROGRAM_COLUMN_PROGRAM_ID + " = ?";

        Integer result = jdbcTemplate.queryForObject(query, Integer.class, course.getCourseid(), program.getProgramid());

        return result > 0;
    }

}
