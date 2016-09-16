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

	@Override
	public File createFile(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File findById(long fileid) {
		// TODO Auto-generated method stub
		List<File> list = jdbcTemplate.query("SELECT * FROM files WHERE fileid= ?",
				(ResultSet rs, int rowNum) -> { 
					return new File(rs.getInt("fileid"), rs.getString("username"), 1, null);
				}, fileid);
		return list.get(0);
	}
	
	@Override
	public List<File> getAll() {
		List<File> list = jdbcTemplate.query("SELECT * FROM files",
				(ResultSet rs, int rowNum) -> { 
					return new File(rs.getInt("fileid"), rs.getString("username"), 1, null);
				});
		return list;
	}
	
	
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert jdbcInsert;
	
	private final static RowMapper<File> ROW_MAPPER = new RowMapper<File>(){

		@Override
		public File mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			// TODO: check this
			return new File(rs.getInt("fileid"), rs.getString("username"), 1, null);
		}
		
	};

	@Autowired
	public FileJdbcDao(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
		jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("files")
				.usingGeneratedKeyColumns("fileid");
	};
}
