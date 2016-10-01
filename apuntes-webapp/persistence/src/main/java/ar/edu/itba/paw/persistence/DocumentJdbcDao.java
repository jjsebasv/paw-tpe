package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DocumentDao;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ar.edu.itba.paw.persistence.ClientJdbcDao.*;
import static ar.edu.itba.paw.persistence.CourseJdbcDao.*;

@Repository
public class DocumentJdbcDao implements DocumentDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    /*package*/ static final String DOCUMENT_TABLE_NAME = "documents";
    /*package*/ static final String DOCUMENT_COLUMN_DOCUMENT_ID = "document_id";
    /*package*/ static final String DOCUMENT_COLUMN_CLIENT_ID = "client_id";
    /*package*/ static final String DOCUMENT_COLUMN_COURSE_ID = "course_id";
    /*package*/ static final String DOCUMENT_COLUMN_SUBJECT = "subject";
    /*package*/ static final String DOCUMENT_COLUMN_NAME = "document_name";
    /*package*/ static final String DOCUMENT_COLUMN_SIZE = "document_size";
    /*package*/ static final String DOCUMENT_COLUMN_UPLOADED_DOCUMENT = "uploaded_document";

    @Autowired
    public DocumentJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(DOCUMENT_TABLE_NAME)
                .usingGeneratedKeyColumns(DOCUMENT_COLUMN_DOCUMENT_ID);
    }

    private final static RowMapper<Document> ROW_MAPPER = new RowMapper<Document>() {

        @Override
        public Document mapRow(ResultSet rs, int rowNum) throws SQLException {

            return new Document(
                    rs.getInt(DOCUMENT_COLUMN_DOCUMENT_ID),
                    new Client(
                            rs.getInt(CLIENT_COLUMN_ID),
                            rs.getString(CLIENT_COLUMN_USERNAME),
                            rs.getString(CLIENT_COLUMN_PASSWORD)
                    ),
                    new Course(
                            rs.getInt(COURSE_COLUMN_ID),
                            rs.getString(COURSE_COLUMN_CODE),
                            rs.getString(COURSE_COLUMN_NAME)
                    ),
                    rs.getString(DOCUMENT_COLUMN_SUBJECT),
                    rs.getString(DOCUMENT_COLUMN_NAME),
                    rs.getInt(DOCUMENT_COLUMN_SIZE),
                    rs.getBinaryStream(DOCUMENT_COLUMN_UPLOADED_DOCUMENT)
            );
        }

    };

    @Override
    public List<Document> findByCourseId(final int courseid) {
        String query = "SELECT * FROM " + DOCUMENT_TABLE_NAME + " NATURAL JOIN " + COURSE_TABLE_NAME +
                " NATURAL JOIN " + CLIENT_TABLE_NAME + " WHERE " + COURSE_COLUMN_ID +
                "= ?";
        return jdbcTemplate.query(query, ROW_MAPPER, courseid);
    }

    @Override
    public Document findById(final int fileid) {
        List<Document> list = jdbcTemplate.query("SELECT * FROM " + DOCUMENT_TABLE_NAME + " NATURAL JOIN " + COURSE_TABLE_NAME +
                " NATURAL JOIN " + CLIENT_TABLE_NAME + " WHERE " + DOCUMENT_COLUMN_DOCUMENT_ID + "= ?", ROW_MAPPER, fileid);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Document> getAll() {
        return jdbcTemplate.query("SELECT * FROM " + DOCUMENT_TABLE_NAME + " NATURAL JOIN " + COURSE_TABLE_NAME +
                " NATURAL JOIN " + CLIENT_TABLE_NAME, ROW_MAPPER);
    }

    @Override
    public Document createDocument(final Client user, final Course course, final String subject, final String filename, final int filesize, final byte[] data) {
        final Map<String, Object> args = new HashMap<>();
        args.put(DOCUMENT_COLUMN_CLIENT_ID, user.getClientId());
        args.put(DOCUMENT_COLUMN_COURSE_ID, course.getCourseid());
        args.put(DOCUMENT_COLUMN_SUBJECT, subject);
        args.put(DOCUMENT_COLUMN_NAME, filename);
        args.put(DOCUMENT_COLUMN_SIZE, filesize);
        args.put(DOCUMENT_COLUMN_UPLOADED_DOCUMENT, data);

        final Number documentid = jdbcInsert.executeAndReturnKey(args);
        return new Document(documentid.intValue(), user, course, subject, filename, filesize, new ByteArrayInputStream(data));
    }

}
