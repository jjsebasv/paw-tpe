package ar.edu.itba.paw.persistance;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.FileDao;
import ar.edu.itba.paw.models.File;
import ar.edu.itba.paw.models.User;

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
                    new User(rs.getString("username"), rs.getString("password"),rs.getInt("userid")),
                    new Course(rs.getInt("courseid"),rs.getString("name")),
                    rs.getString("subject"),
                    rs.getString("filename"),//FIXME Viene char(300)?
                    rs.getInt("filesize"),
                    rs.getBinaryStream("uploaded_file")
            );
        }

    };
    
    private final static RowMapper<User> USER_ROW_MAPPER = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new User(rs.getString("username"), rs.getString("password"), rs.getLong("userid"));
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
        return list.get(0);
    }


    @Override
    public List<File> getAll() {
        return jdbcTemplate.query("SELECT * FROM files NATURAL JOIN courses NATURAL JOIN users ", ROW_MAPPER);
    }
    
    @Override
    public User getUser(final int userid) {
    	List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE userid = ?", USER_ROW_MAPPER, userid);
    	return list.get(0);
    }


}
