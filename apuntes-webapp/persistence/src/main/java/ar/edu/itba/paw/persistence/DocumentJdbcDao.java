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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DocumentJdbcDao implements DocumentDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public static final String DOCUMENT_TABLE_NAME = "documents";
    public static final String DOCUMENT_COLUMN_DOCUMENT_ID = "document_id";
    public static final String DOCUMENT_COLUMN_CLIENT_ID = "client_id";
    public static final String DOCUMENT_COLUMN_COURSE_ID = "course_id";
    public static final String DOCUMENT_COLUMN_SUBJECT = "subject";
    public static final String DOCUMENT_COLUMN_NAME = "document_name";
    public static final String DOCUMENT_COLUMN_SIZE = "document_size";
    public static final String DOCUMENT_COLUMN_UPLOADED_DOCUMENT = "uploaded_document";
    public static final String CLIENT_TABLE_NAME = "clients";
    public static final String CLIENT_COLUMN_ID = "client_id";
    public static final String CLIENT_COLUMN_USERNAME = "username";
    public static final String CLIENT_COLUMN_PASSWORD = "password";
    public static final String COURSE_TABLE_NAME = "courses";
    public static final String COURSE_COLUMN_ID = "course_id";
    public static final String COURSE_COLUMN_CODE = "code";
    public static final String COURSE_COLUMN_NAME = "name";

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

            return new Document(rs.getInt(DOCUMENT_COLUMN_DOCUMENT_ID),
                    new Client(rs.getInt(CLIENT_COLUMN_ID), rs.getString(CLIENT_COLUMN_USERNAME), rs.getString(CLIENT_COLUMN_PASSWORD)),
                    new Course(rs.getInt(COURSE_COLUMN_ID), rs.getString(COURSE_COLUMN_CODE), rs.getString(COURSE_COLUMN_NAME)),
                    rs.getString(DOCUMENT_COLUMN_SUBJECT),
                    rs.getString(DOCUMENT_COLUMN_NAME),
                    rs.getInt(DOCUMENT_COLUMN_SIZE),
                    rs.getBinaryStream(DOCUMENT_COLUMN_UPLOADED_DOCUMENT)
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
	public Document createDocument(Client user, Course course, String subject, String filename, int filesize,
			byte[] data) {
		// TODO Auto-generated method stub
		final Map<String, Object> args = new HashMap<>();
        args.put("client_id", user.getClientId());
        args.put("course_id", course.getCourseid());
        args.put("subject", subject);
        args.put("document_name", filename);
        args.put("document_size", filesize);
        args.put("uploaded_document", data);

        final Number documentid = jdbcInsert.executeAndReturnKey(args);
        return new Document(documentid.intValue(), user, course, subject, filename, filesize, new ByteArrayInputStream(data));
	}


}
