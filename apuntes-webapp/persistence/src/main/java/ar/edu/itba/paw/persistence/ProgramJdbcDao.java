package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.interfaces.ProgramDao;
import ar.edu.itba.paw.models.Program;
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
public class ProgramJdbcDao implements ProgramDao {

    /*package*/ static final String PROGRAM_TABLE_NAME = "programs";
    /*package*/ static final String PROGRAM_COLUMN_ID = "program_id";
    /*package*/ static final String PROGRAM_COLUMN_NAME = "name";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Program> ROW_MAPPER = new RowMapper<Program>() {

        @Override
        public Program mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Program(
                    rs.getInt(PROGRAM_COLUMN_ID),
                    rs.getString(PROGRAM_COLUMN_NAME)
            );
        }
    };

    @Autowired
    public ProgramJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(PROGRAM_TABLE_NAME);
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

        final String query = "SELECT * FROM " + PROGRAM_TABLE_NAME + " WHERE " + PROGRAM_COLUMN_NAME + " LIKE \"%?%\"";

        return jdbcTemplate.query(query, ROW_MAPPER, name);
    }
}
