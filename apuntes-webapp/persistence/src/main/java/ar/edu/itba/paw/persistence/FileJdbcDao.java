package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.FileDao;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.File;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FileJdbcDao implements FileDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public FileJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("files")
                .usingGeneratedKeyColumns("fileid");
    }

    private final static RowMapper<File> ROW_MAPPER = new RowMapper<File>() {

        @Override
        public File mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new File(rs.getInt("fileid"),
                    new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password")),
                    new Course(rs.getInt("courseid"), rs.getString("code"), rs.getString("name")),
                    rs.getString("subject"),
                    rs.getString("filename"),
                    rs.getInt("filesize"),
                    rs.getBinaryStream("uploaded_file")
            );
        }

    };

    @Override
    public File createFile(InputStream data) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<File> findByCourseId(final int courseid) {
        return jdbcTemplate.query("SELECT * FROM files NATURAL JOIN courses NATURAL JOIN users WHERE courseid= ?", ROW_MAPPER, courseid);
    }

    @Override
    public File findById(final int fileid) {
        List<File> list = jdbcTemplate.query("SELECT * FROM files NATURAL JOIN courses NATURAL JOIN users WHERE fileid= ?", ROW_MAPPER, fileid);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    @Override
    public List<File> getAll() {
        return jdbcTemplate.query("SELECT * FROM files NATURAL JOIN courses NATURAL JOIN users ", ROW_MAPPER);
    }

}
