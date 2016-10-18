package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.interfaces.ProgramDao;
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
public class ProgramJdbcDao implements ProgramDao {

    /*package*/ static final String PROGRAM_TABLE_NAME = "programs";
    /*package*/ static final String PROGRAM_COLUMN_ID = "program_id";
    /*package*/ static final String PROGRAM_COLUMN_NAME = "name";
    /*package*/ static final String PROGRAM_COLUMN_SHORTNAME = "short_name";
    /*package*/ static final String PROGRAM_COLUMN_GROUP = "program_group";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Program> ROW_MAPPER = new RowMapper<Program>() {

        @Override
        public Program mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Program(
                    rs.getInt(PROGRAM_COLUMN_ID),
                    rs.getString(PROGRAM_COLUMN_NAME),
                    rs.getString(PROGRAM_COLUMN_SHORTNAME),
                    rs.getString(PROGRAM_COLUMN_GROUP).charAt(0));
        }
    };

    @Autowired
    public ProgramJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(PROGRAM_TABLE_NAME)
                .usingGeneratedKeyColumns(PROGRAM_COLUMN_ID);
    }

    @Override
    public List<Program> getAll() {

        final String query = "SELECT * FROM " + PROGRAM_TABLE_NAME;

        return jdbcTemplate.query(query, ROW_MAPPER);
    }

    @Override
    public Program findById(final int programid) {

        final String query = "SELECT * FROM " + PROGRAM_TABLE_NAME + " WHERE " + PROGRAM_COLUMN_ID + " = ?";

        List<Program> list = jdbcTemplate.query(query, ROW_MAPPER, programid);

        if (list.isEmpty())
            return null;

        return list.get(0);
    }

    @Override
    public List<Program> findByName(String name) {

        String queryName = name
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");

        final String query = "SELECT * FROM " + PROGRAM_TABLE_NAME + " WHERE LOWER(" + PROGRAM_COLUMN_NAME + ") LIKE LOWER(?)  ESCAPE '!'";

        //return jdbcTemplate.query(query, ROW_MAPPER, "'%" + name + "%'");
        try {
            PreparedStatement statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(query);
            statement.setString(1, "%" + queryName + "%");

            ResultSet resultSet = statement.executeQuery();

            List<Program> list = new ArrayList<>();

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
    public Program create(final String name, final String shortName, final char group) {
        final Map<String, Object> args = new HashMap<>();
        args.put(PROGRAM_COLUMN_NAME, name);
        args.put(PROGRAM_COLUMN_SHORTNAME, shortName);
        args.put(PROGRAM_COLUMN_GROUP, group);

        final Number programId = jdbcInsert.executeAndReturnKey(args);
        return new Program(programId.intValue(), name, shortName, group);
    }
}
