package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DocumentDao;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Client;
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
public class DocumentJdbcDao implements DocumentDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public DocumentJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("files")
                .usingGeneratedKeyColumns("fileid");
    }

    private final static RowMapper<Document> ROW_MAPPER = new RowMapper<Document>() {

        @Override
        public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Document(rs.getInt("fileid"),
                    new Client(rs.getInt("userid"), rs.getString("username"), rs.getString("password")),
                    new Course(rs.getInt("courseid"), rs.getString("code"), rs.getString("name")),
                    rs.getString("subject"),
                    rs.getString("filename"),
                    rs.getInt("filesize"),
                    rs.getBinaryStream("uploaded_file")
            );
        }

    };

    @Override
    public Document createFile(InputStream data) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<Document> findByCourseId(final int courseid) {
        return jdbcTemplate.query("SELECT * FROM files NATURAL JOIN courses NATURAL JOIN users WHERE courseid= ?", ROW_MAPPER, courseid);
    }

    @Override
    public Document findById(final int fileid) {
        List<Document> list = jdbcTemplate.query("SELECT * FROM files NATURAL JOIN courses NATURAL JOIN users WHERE fileid= ?", ROW_MAPPER, fileid);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    @Override
    public List<Document> getAll() {
        return jdbcTemplate.query("SELECT * FROM files NATURAL JOIN courses NATURAL JOIN users ", ROW_MAPPER);
    }

}
