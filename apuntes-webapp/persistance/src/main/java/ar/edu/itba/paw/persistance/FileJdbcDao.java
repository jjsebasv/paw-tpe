package ar.edu.itba.paw.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.FileDao;
import ar.edu.itba.paw.models.File;

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
            // TODO Auto-generated method stub
            // TODO: check this
            return new File(rs.getInt("fileid"),
                    rs.getInt("userid"),
                    rs.getInt("course"),
                    rs.getString("subject"),
                    rs.getString("filename"),
                    rs.getBytes("uploaded_file")
            );
        }

    };

    @Override
    public File createFile(byte[] data) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<File> findByCourseId(int courseid) {
        return jdbcTemplate.query("SELECT * FROM files WHERE course = ?", ROW_MAPPER, courseid);
    }

    @Override
    public File findById(int fileid) {
        List<File> list = jdbcTemplate.query("SELECT * FROM files WHERE fileid= ?", ROW_MAPPER, fileid);
        return list.get(0);
    }

    @Override
    public List<File> getAll() {
        return jdbcTemplate.query("SELECT * FROM files", ROW_MAPPER);
    }


}
